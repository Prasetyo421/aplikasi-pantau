package com.didi.pantaucovid_19.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.didi.pantaucovid_19.model.ListDataItem
import com.didi.pantaucovid_19.model.ResponseDataCovid
import com.didi.pantaucovid_19.networking.ApiCoronaConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class CovidViewModel : ViewModel() {
    private var _listCovid =  MutableLiveData<List<ListDataItem>>()
    val listCovid: LiveData<List<ListDataItem>> = _listCovid

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setDataCovid(){
        _isLoading.value = true
        val client = ApiCoronaConfig.getApiCoronaService().getAllProvince()
        client.enqueue(object : Callback<ResponseDataCovid>{
            override fun onResponse(
                call: Call<ResponseDataCovid>,
                response: Response<ResponseDataCovid>
            ) {
                Timber.d("isVisible: $isLoading")
                if (response.isSuccessful && response.body() != null){
                    val responseBody: ResponseDataCovid = response.body() as ResponseDataCovid
                    _listCovid.value = responseBody.listData
                }else {
                    Timber.d("onFailure: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<ResponseDataCovid>, t: Throwable) {
                Timber.d("onFailure: ${t.message}")
                _isLoading.value = false
            }

        })
    }

}