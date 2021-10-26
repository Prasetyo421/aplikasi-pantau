package com.didi.pantaucovid_19.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.didi.pantaucovid_19.model.ParamDetailHospital
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedViewModel : ViewModel() {
    private val _idHospital: MutableLiveData<String> = MutableLiveData<String>()
    val idHospital: LiveData<String> = _idHospital

    private val _paramDetailHospital: MutableLiveData<ParamDetailHospital> = MutableLiveData<ParamDetailHospital>()
    val paramDetailHospital: LiveData<ParamDetailHospital> = _paramDetailHospital

    suspend fun setIdHospital(idHospital: String){
        withContext(Dispatchers.Main){
            _idHospital.value = idHospital
        }
    }

    suspend fun setParamDetailHospital(idHospital: String, name: String, type: String){
        val param = ParamDetailHospital(idHospital, name, type)
        withContext(Dispatchers.Main){
            _paramDetailHospital.value = param
        }
    }
}