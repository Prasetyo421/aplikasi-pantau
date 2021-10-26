package com.didi.pantaucovid_19.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.didi.pantaucovid_19.database.PhoneNumber
import com.didi.pantaucovid_19.database.PhoneNumberDao
import com.didi.pantaucovid_19.database.PhoneNumberDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.math.E

class PhoneNumberRepository(application: Application) {
    private val mPhoneNumberDao: PhoneNumberDao
    private val mExecutorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PhoneNumberDatabase.getInstance(application)
        mPhoneNumberDao = db.phoneNumberDao()
    }

    fun getAllNumbers(): LiveData<List<PhoneNumber>> = mPhoneNumberDao.getAllPhoneNumbers()

    fun insert(phoneNumber: PhoneNumber){
        mExecutorService.execute { mPhoneNumberDao.insert(phoneNumber) }
    }

    fun update(phoneNumber: PhoneNumber){
        mExecutorService.execute { mPhoneNumberDao.update(phoneNumber) }
    }

    fun delete(phoneNumber: PhoneNumber){
        mExecutorService.execute { mPhoneNumberDao.delete(phoneNumber) }
    }

}