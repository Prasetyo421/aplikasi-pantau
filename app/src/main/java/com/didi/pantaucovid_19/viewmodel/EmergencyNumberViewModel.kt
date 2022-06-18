package com.didi.pantaucovid_19.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didi.pantaucovid_19.database.PhoneNumber
import com.didi.pantaucovid_19.repository.PhoneNumberRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class EmergencyNumberViewModel(application: Application) : ViewModel() {
    private val mPhoneNumberRepository = PhoneNumberRepository(application)

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var _snackBar = MutableLiveData<String?>()
    val snackbar: LiveData<String?> = _snackBar

    fun getAllNumbers() = mPhoneNumberRepository.getAllNumbers()

    fun onSnackbarShown() {
        _snackBar.value = null
    }

    fun insert(phoneNumber: PhoneNumber) {
        launchDataLoad {
            mPhoneNumberRepository.insert(phoneNumber)
        }
    }

    fun update(phoneNumber: PhoneNumber) {
        mPhoneNumberRepository.update(phoneNumber)
    }

    fun delete(phoneNumber: PhoneNumber) {
        mPhoneNumberRepository.delete(phoneNumber)
    }

    private fun launchDataLoad(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _loading.value = true
                block
            } catch (e: java.lang.Exception){
                _snackBar.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}