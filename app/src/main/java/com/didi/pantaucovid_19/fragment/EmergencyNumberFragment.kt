package com.didi.pantaucovid_19.fragment

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.didi.pantaucovid_19.R
import com.didi.pantaucovid_19.adapter.EmergencyNumberAdapter
import com.didi.pantaucovid_19.database.PhoneNumber
import com.didi.pantaucovid_19.databinding.FragmentEmergencyNumberBinding
import com.didi.pantaucovid_19.viewmodel.EmergencyNumberViewModel
import com.didi.pantaucovid_19.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class EmergencyNumberFragment : Fragment() {
    private var _binding: FragmentEmergencyNumberBinding? = null
    private val binding get() = _binding
    private lateinit var emergencyNumberViewModel: EmergencyNumberViewModel
    private lateinit var adapter: EmergencyNumberAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmergencyNumberBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emergencyNumberViewModel = obtainViewModel(activity)
        adapter = EmergencyNumberAdapter()

        binding?.rvEmergencyNumber?.adapter = adapter
        subscribeUI(adapter)

        emergencyNumberViewModel.loading.observe(viewLifecycleOwner) { state ->
            showLoading(state)
        }

        emergencyNumberViewModel.snackbar.observe(viewLifecycleOwner) { message ->
            message?.let {
                Snackbar.make(
                    requireActivity().findViewById(R.id.root_layout),
                    message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            emergencyNumberViewModel.onSnackbarShown()
        }
    }

    private fun subscribeUI(adapter: EmergencyNumberAdapter) {
        binding?.rvEmergencyNumber?.layoutManager = LinearLayoutManager(context)
        emergencyNumberViewModel.getAllNumbers().observe(viewLifecycleOwner) { numbers ->
            adapter.setData(numbers)
        }
        adapter.setOnPhoneClickCallback(object : EmergencyNumberAdapter.OnPhoneClickCallback {
            override fun onClicked(number: PhoneNumber) {
                lifecycleScope.launch(Dispatchers.Default) {
                    Timber.d("phone click: ${Thread.currentThread()}")
                    val move = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${number.number}"))
                    startActivity(move)
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.shimmerNumber?.visibility = View.VISIBLE
            binding?.rvEmergencyNumber?.visibility = View.GONE
        } else {
            binding?.shimmerNumber?.visibility = View.GONE
            binding?.rvEmergencyNumber?.visibility = View.VISIBLE
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): EmergencyNumberViewModel {
        val factory = ViewModelFactory.getInstance(activity?.application as Application)
        return ViewModelProvider(activity, factory)[EmergencyNumberViewModel::class.java]
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        val listNumbers = arrayListOf<PhoneNumber>(
            PhoneNumber(name = "Darurat", number = "112"),
            PhoneNumber(name = "Hotline Covid-19", number = "119"),
            PhoneNumber(name = "Ambulance", number = "118"),
            PhoneNumber(name = "Pemadam kebakaran", number = "113"),
            PhoneNumber(name = "Polisi", number = "110"),
            PhoneNumber(name = "SAR/BASARNAS", number = "115"),
            PhoneNumber(name = "Posko Kewaspadaan Nasional", number = "122"),
            PhoneNumber(name = "PLN", number = "123"),
            PhoneNumber(name = "Posko bencana alam ", number = "129"),
            PhoneNumber(name = "PMI", number = "(021) 7992325"),
            PhoneNumber(name = "KOMNAS HAM", number = "021-3925230"),
            PhoneNumber(name = "KOMNAS Perempuan", number = "021-3903963"),

            )

        @JvmStatic
        fun newInstance() = EmergencyNumberFragment()
    }
}