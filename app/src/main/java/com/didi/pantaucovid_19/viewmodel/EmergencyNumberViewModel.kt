package com.didi.pantaucovid_19.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.didi.pantaucovid_19.database.PhoneNumber
import com.didi.pantaucovid_19.repository.PhoneNumberRepository

class EmergencyNumberViewModel(application: Application) : ViewModel() {
    private val mPhoneNumberRepository = PhoneNumberRepository(application)

    fun getAllNumbers() = mPhoneNumberRepository.getAllNumbers()

    fun insert(phoneNumber: PhoneNumber) {
        mPhoneNumberRepository.insert(phoneNumber)
    }

    fun update(phoneNumber: PhoneNumber) {
        mPhoneNumberRepository.update(phoneNumber)
    }

    fun delete(phoneNumber: PhoneNumber) {
        mPhoneNumberRepository.delete(phoneNumber)
    }
}