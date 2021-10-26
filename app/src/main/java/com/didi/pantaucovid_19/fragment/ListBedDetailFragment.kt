package com.didi.pantaucovid_19.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.didi.pantaucovid_19.adapter.BedDetailAdapter
import com.didi.pantaucovid_19.databinding.FragmentListHospitalBinding
import com.didi.pantaucovid_19.fragment.HospitalFragment.Companion.TYPE_COVID
import com.didi.pantaucovid_19.fragment.HospitalFragment.Companion.TYPE_NON_COVID
import com.didi.pantaucovid_19.viewmodel.HospitalViewModel
import com.didi.pantaucovid_19.viewmodel.SharedViewModel
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_SECTION_NUMBER = "section_number"

/**
 * A simple [Fragment] subclass.
 * Use the [ListHospitalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListHospitalFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var index: Int = 0
    private var _binding: FragmentListHospitalBinding? = null
    private val binding get() = _binding
    private lateinit var hospitalViewMode: HospitalViewModel
    private var adapter: BedDetailAdapter? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        index = arguments?.getInt(ARG_SECTION_NUMBER) as Int
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListHospitalBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hospitalViewMode = HospitalViewModel()
        adapter = BedDetailAdapter()

        Timber.d("onViewCreated")

        binding?.rvDetailBed?.layoutManager = LinearLayoutManager(context)

        hospitalViewMode.isLoading.observe(viewLifecycleOwner, { state ->
            showLoading(state)
        })

        sharedViewModel.paramDetailHospital.observe(viewLifecycleOwner, { param ->
            Timber.d("param: ${param.name}")
            Timber.d("index: ${index}")
            if (index == 0){
                hospitalViewMode.setBedDetailHospital(param.id, TYPE_COVID)
                hospitalViewMode.bedDetailHospital.observe(viewLifecycleOwner, { listData ->
                    Timber.d("listData: Size ${listData.size}")
                    adapter?.setData(listData)
                    binding?.rvDetailBed?.adapter = adapter
                })

            }else {
                hospitalViewMode.setBedDetailHospital(param.id, TYPE_NON_COVID)
                hospitalViewMode.bedDetailHospital.observe(viewLifecycleOwner, { listData ->
                    Timber.d("listData: Size ${listData.size}")
                    adapter?.setData(listData)
                    binding?.rvDetailBed?.adapter = adapter
                })
            }
        })
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding?.shimmerBedDetail?.visibility = View.VISIBLE
            binding?.rvDetailBed?.visibility = View.GONE
        }else {
            binding?.shimmerBedDetail?.visibility = View.GONE
            binding?.rvDetailBed?.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListHospitalFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(index: Int) =
            ListHospitalFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }
}