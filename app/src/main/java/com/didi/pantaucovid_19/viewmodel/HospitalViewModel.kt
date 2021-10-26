package com.didi.pantaucovid_19.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didi.pantaucovid_19.fragment.HospitalFragment.Companion.TYPE_COVID
import com.didi.pantaucovid_19.fragment.HospitalFragment.Companion.TYPE_NON_COVID
import com.didi.pantaucovid_19.model.*
import com.didi.pantaucovid_19.networking.ApiCitiesConfig
import com.didi.pantaucovid_19.networking.ApiCitiesService
import com.didi.pantaucovid_19.networking.ApiHospitalConfig
import com.didi.pantaucovid_19.networking.ApiHospitalService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HospitalViewModel: ViewModel() {
    private var client: ApiHospitalService = ApiHospitalConfig.getInstance()

    private val _listCities: MutableLiveData<List<CitiesItem>> = MutableLiveData<List<CitiesItem>>()
    val listCities: LiveData<List<CitiesItem>> = _listCities

    private val _listHospitalsCovid: MutableLiveData<List<HospitalsCovidItem>> = MutableLiveData<List<HospitalsCovidItem>>()
    val listHospitalsCovid: LiveData<List<HospitalsCovidItem>> = _listHospitalsCovid

    private val _listHospitalsNonCovid: MutableLiveData<List<HospitalsNonCovidItem>> = MutableLiveData<List<HospitalsNonCovidItem>>()
    val listHospitalsNonCovid: LiveData<List<HospitalsNonCovidItem>> = _listHospitalsNonCovid

    private val _bedDetailHospital: MutableLiveData<List<BedDetailItem>> = MutableLiveData<List<BedDetailItem>>()
    val bedDetailHospital: LiveData<List<BedDetailItem>> = _bedDetailHospital

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setCities(provId: String){
        viewModelScope.launch(Dispatchers.Default) {
            val innerClient: ApiCitiesService = ApiCitiesConfig.getInstance()
            Timber.d("current thread1: ${Thread.currentThread()}")
            innerClient.getCities(provId).enqueue(object : Callback<ResponseCities>{
                override fun onResponse(
                    call: Call<ResponseCities>,
                    response: Response<ResponseCities>
                ) {
                    Timber.d("current thread success1: ${Thread.currentThread()}")
                    viewModelScope.launch(Dispatchers.Default) {
                        val responseBody = response.body()
                        if (response.isSuccessful && responseBody != null){
                            _listCities.postValue(responseBody.cities)
                            Timber.d("current thread success2: ${Thread.currentThread()}")
                        }else {
                            Timber.d("onFailure: ${response.message()}")
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseCities>, t: Throwable) {
                    Timber.d("onFailure: ${t.message}")
                }
            })

        }
    }

    fun setHospitalsData(provId: String, cityId: String, type: String){
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main){
                _isLoading.value = true
            }
            Timber.d("current thread:  ${Thread.currentThread().name}")
            if (type == TYPE_COVID){
                client.getCovidHospitals(provId, cityId, TYPE_COVID).enqueue(object : Callback<ResponseCovidHospitals>{
                    override fun onResponse(
                        call: Call<ResponseCovidHospitals>,
                        response: Response<ResponseCovidHospitals>
                    ) {
                        val responseBody = response.body()
                        if (response.isSuccessful && responseBody != null){
                            _listHospitalsCovid.value = responseBody.hospitalsCovid
                        }else {
                            Timber.d("onFailure: ${response.message()}")
                        }
                        _isLoading.value = false
                    }

                    override fun onFailure(call: Call<ResponseCovidHospitals>, t: Throwable) {
                        Timber.d("onFailure: ${t.message}")
                        _isLoading.value = false
                    }

                })
            }else if (type == TYPE_NON_COVID){
                client.getNonCovidHospitals(provId, cityId, type).enqueue(object : Callback<ResponseNonCovidHospitals>{
                    override fun onResponse(
                        call: Call<ResponseNonCovidHospitals>,
                        response: Response<ResponseNonCovidHospitals>
                    ) {
                        val responseBody = response.body()
                        if (response.isSuccessful && responseBody != null){
                            _listHospitalsNonCovid.value = responseBody.hospitalsCovid
                        }else {
                            Timber.d("onFailure: ${response.message()}")
                        }
                        _isLoading.value = false
                    }

                    override fun onFailure(call: Call<ResponseNonCovidHospitals>, t: Throwable) {
                        Timber.d("onFailure: ${t.message}")
                        _isLoading.value = false
                    }

                })
            }
        }
    }

    fun setBedDetailHospital(hospitalId: String, type: String){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                _isLoading.value = true
            }
            Timber.d("current thread:  ${Thread.currentThread().name}")
            client.getDetailHospital(hospitalId, type).enqueue(object : Callback<ResponseDetailHospital>{
                override fun onResponse(
                    call: Call<ResponseDetailHospital>,
                    response: Response<ResponseDetailHospital>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null){
                        _bedDetailHospital.value = responseBody.data.bedDetail
                    }else {
                        Timber.d("onFailure: ${response.message()}")
                    }
                    _isLoading.value = false
                }

                override fun onFailure(call: Call<ResponseDetailHospital>, t: Throwable) {
                    Timber.d("onFailure: ${t.message}")
                    _isLoading.value = false
                }
            })
        }
    }

}