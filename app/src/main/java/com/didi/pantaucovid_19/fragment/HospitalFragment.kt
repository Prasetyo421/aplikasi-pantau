package com.didi.pantaucovid_19.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.didi.pantaucovid_19.databinding.FragmentHospitalBinding
import timber.log.Timber
import com.didi.pantaucovid_19.R
import com.didi.pantaucovid_19.adapter.CovidHospitalAdapter
import com.didi.pantaucovid_19.adapter.NonCovidHospitalAdapter
import com.didi.pantaucovid_19.model.CitiesItem
import com.didi.pantaucovid_19.model.HospitalsCovidItem
import com.didi.pantaucovid_19.model.HospitalsNonCovidItem
import com.didi.pantaucovid_19.viewmodel.HospitalViewModel
import com.didi.pantaucovid_19.viewmodel.SharedViewModel
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.didi.pantaucovid_19.helper.Helper.Companion.mapProvinces
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HospitalFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHospitalBinding? = null
    private val binding get() = _binding
    private lateinit var hospitalViewModel: HospitalViewModel
    private var listCities: List<CitiesItem>? = null
    private var provId: String? = null
    private var cityId: String? = null
    private var type: String? = null
    private lateinit var covidHospitalAdapter: CovidHospitalAdapter
    private lateinit var nonCovidHospitalAdapter: NonCovidHospitalAdapter
    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHospitalBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("Hospital Fragment")

        val tvCityName = binding?.cityName
        hospitalViewModel = HospitalViewModel()
        covidHospitalAdapter = CovidHospitalAdapter()
        nonCovidHospitalAdapter = NonCovidHospitalAdapter()

        hospitalViewModel.isLoading.observe(viewLifecycleOwner, { state ->
            showLoading(state)
        })

        val tvProvinceName = binding?.provinceNamed
        val adapter = ArrayAdapter(context as Context, R.layout.support_simple_spinner_dropdown_item,
            mapProvinces.keys.toList())
        binding?.provinceNamed?.setAdapter(adapter)
        binding?.provinceNamed?.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                lifecycleScope.launch(Dispatchers.Default) {
                    Timber.d("Current thread: ${Thread.currentThread()}")
                    val name = tvProvinceName?.text.toString().trim()
                    Timber.d("provName: {$name}")
                    provId = mapProvinces[name]
                    Timber.d("provID: $provId")
                    if (provId != null){
                        hospitalViewModel.setCities(provId as String)
                        withContext(Dispatchers.Main) {
                            Timber.d("start")
                            hospitalViewModel.listCities.observe(viewLifecycleOwner, { list ->
                                Timber.d("sizes cities1: ${list.size}")
                                listCities = list
                                val mArray = ArrayList<String>()
                                lifecycleScope.launch(Dispatchers.Default) {
                                    list.forEach { mArray.add(it.name) }
                                    val listCitiesName: List<String>
                                    listCitiesName = mArray

                                    Timber.d("sizes cities2: ${listCitiesName.size}")
                                    withContext(Dispatchers.Main) {
                                        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(
                                            context as Context,
                                            R.layout.support_simple_spinner_dropdown_item,
                                            listCitiesName
                                        )
                                        tvCityName?.setAdapter(adapter2)
                                        Timber.d("end setAdapter")
                                    }
                                }
                            })
                        }
                    }
                }
            }

        tvCityName?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            lifecycleScope.launch(Dispatchers.Default){
                val cityName = tvCityName?.text.toString().trim()
                listCities?.forEach {
                    if (cityName == it.name){
                        cityId = it.id
                    }
                }
            }
        }

        val detailHospitalFragment = DetailHospitalFragment()

        covidHospitalAdapter.setOnItemClickCallback(object : CovidHospitalAdapter.OnItemClickCallback {
            override fun onItemClicked(item: HospitalsCovidItem) {
                lifecycleScope.launch(Dispatchers.Default){
                    model.setIdHospital(item.id)
                    model.setParamDetailHospital(item.id, item.name, TYPE_COVID)
                    activity?.supportFragmentManager?.commit {
                        addToBackStack("...")
                        replace(R.id.frame_container, detailHospitalFragment)
                    }
                    withContext(Dispatchers.Main){
                        Toast.makeText(context, "name: ${item.name}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        nonCovidHospitalAdapter.setOnItemClickCallback(object : NonCovidHospitalAdapter.OnItemClickCallback{
            override fun onItemClicked(item: HospitalsNonCovidItem) {
                lifecycleScope.launch(Dispatchers.Default){
                    model.setIdHospital(item.id)
                    model.setParamDetailHospital(item.id, item.name, TYPE_NON_COVID)
                    activity?.supportFragmentManager?.commit {
                        addToBackStack("...")
                        replace(R.id.frame_container, detailHospitalFragment)
                    }
                    withContext(Dispatchers.Main){
                        Toast.makeText(context, "name: ${item.name}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        covidHospitalAdapter.setOnPhoneClickCallback(object : CovidHospitalAdapter.OnPhoneClickCallback{
            override fun onPhoneClicked(item: HospitalsCovidItem) {
                lifecycleScope.launch(Dispatchers.Default){
                    val move = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${item.phone}"))
                    startActivity(move)
                }
            }
        })

        nonCovidHospitalAdapter.setPhoneClickCallback(object : NonCovidHospitalAdapter.OnPhoneClickCallback{
            override fun onPhoneClicked(item: HospitalsNonCovidItem) {
                lifecycleScope.launch(Dispatchers.Default){
                    val move = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${item.phone}"))
                    startActivity(move)
                }
            }

        })

        binding?.chipCovid?.setOnClickListener(this)
        binding?.chipNonCovid?.setOnClickListener(this)
        binding?.btnSearch?.setOnClickListener(this)

        Timber.d("Hospital Fragment end")
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding?.shimmerHospital?.visibility = View.VISIBLE
            binding?.rvHospital?.visibility = View.GONE
        }else {
            binding?.shimmerHospital?.visibility = View.GONE
            binding?.rvHospital?.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        lifecycleScope.launch(Dispatchers.Default){
            when(v?.id){
                R.id.chip_covid -> {
                    type = TYPE_COVID
                    withContext(Dispatchers.Main){
                        binding?.rvHospital?.layoutManager = LinearLayoutManager(context)
                        binding?.rvHospital?.adapter = covidHospitalAdapter
                    }
                }
                R.id.chip_non_covid -> {
                    type = TYPE_NON_COVID
                    withContext(Dispatchers.Main){
                        binding?.rvHospital?.layoutManager = LinearLayoutManager(context)
                        binding?.rvHospital?.adapter = nonCovidHospitalAdapter
                    }
                }
                R.id.btn_search -> {
                    if (provId != null && cityId != null && type != null){
                        withContext(Dispatchers.Default){
                            hospitalViewModel.setHospitalsData(provId as String, cityId as String, type as String)

                        }
                        if (type == TYPE_COVID){
                            withContext(Dispatchers.Main){
                                hospitalViewModel.listHospitalsCovid.observe(viewLifecycleOwner, { listHospital ->
                                    lifecycleScope.launch(Dispatchers.Default){
                                        covidHospitalAdapter.setData(listHospital)
                                        withContext(Dispatchers.Main){
                                            binding?.rvHospital?.layoutManager = LinearLayoutManager(context)
                                            binding?.rvHospital?.adapter = covidHospitalAdapter
                                        }
                                    }
                                })
                            }
                        }else if (type == TYPE_NON_COVID){
                            withContext(Dispatchers.Main){
                                hospitalViewModel.listHospitalsNonCovid.observe(viewLifecycleOwner, { listHospital ->
                                    lifecycleScope.launch(Dispatchers.Default){
                                        nonCovidHospitalAdapter.setData(listHospital)
                                        withContext(Dispatchers.Main){
                                            binding?.rvHospital?.layoutManager = LinearLayoutManager(context)
                                            binding?.rvHospital?.adapter = nonCovidHospitalAdapter
                                        }
                                    }
                                })
                            }
                        }
                    }
                    Timber.d("btnSearch: Clicked")
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HospitalFragment()
        val TYPE_COVID = "1"
        val TYPE_NON_COVID = "2"
    }
}