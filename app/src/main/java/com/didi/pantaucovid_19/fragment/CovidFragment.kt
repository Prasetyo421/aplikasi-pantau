package com.didi.pantaucovid_19.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.didi.pantaucovid_19.R
import com.didi.pantaucovid_19.adapter.CovidAdapter
import com.didi.pantaucovid_19.databinding.FragmentCovidBinding
import com.didi.pantaucovid_19.helper.Helper.Companion.mapProvinces
import com.didi.pantaucovid_19.model.DataItem
import com.didi.pantaucovid_19.viewmodel.CovidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class CovidFragment : Fragment() {
    private var _binding: FragmentCovidBinding? = null
    private val binding get() = _binding
    private lateinit var covidViewModel: CovidViewModel
    private lateinit var covidAdapter: CovidAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCovidBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        covidViewModel = CovidViewModel()
        covidAdapter = CovidAdapter()

        covidViewModel.setDataCovid()
        val visible = binding?.shimmerCovid?.visibility
        Timber.d("isloading: $visible")
        covidViewModel.covid.observe(viewLifecycleOwner, { listCovid ->
            lifecycleScope.launch(Dispatchers.Default){
                covidAdapter.setData(listCovid)
            }
        })

        covidViewModel.isLoading.observe(viewLifecycleOwner, { state ->
            Timber.d("isloading2: $state")
            showLoading(state)
        })

        binding?.provinceName?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val provName = binding?.provinceName?.text.toString().trim()

            covidViewModel.covid.observe(viewLifecycleOwner, { listCovid ->
                Timber.d("Thread ${Thread.currentThread()}")
                lifecycleScope.launch(Dispatchers.Default){
                    Timber.d("Thread ${Thread.currentThread()}")
                    val itemProv = ArrayList<DataItem>()
                    val chars: List<Char> = provName.map { it.uppercaseChar() }
                    val upperProvName = String(chars.toCharArray())
                    Timber.d("convert: $upperProvName")
                    listCovid.forEach{ item ->
                        Timber.d("{${item.key}}")
                        if (item.key == upperProvName){
                            itemProv.add(item)
                            return@forEach
                        }
                    }
                    if (itemProv.isNotEmpty()){
                        covidAdapter.setData(itemProv)
                        withContext(Dispatchers.Main){
                            binding?.rvCovid?.adapter = covidAdapter
                        }
                    }else {
                        withContext(Dispatchers.Main){
                            Toast.makeText(context, "Cannot find province name \"$provName\"", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }

        binding?.rvCovid?.layoutManager = LinearLayoutManager(context)
        binding?.rvCovid?.adapter = covidAdapter

        val adapter = ArrayAdapter<String>(context as Context, R.layout.support_simple_spinner_dropdown_item, mapProvinces.keys.toList())
        binding?.provinceName?.setAdapter(adapter)
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding?.shimmerCovid?.visibility = View.VISIBLE
            binding?.rvCovid?.visibility = View.GONE
        }else {
            binding?.shimmerCovid?.visibility = View.GONE
            binding?.rvCovid?.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CovidFragment()
    }
}