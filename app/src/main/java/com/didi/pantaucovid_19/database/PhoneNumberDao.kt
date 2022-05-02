package com.didi.pantaucovid_19.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PhoneNumberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(number: PhoneNumber)

    @Update
    fun update(number: PhoneNumber)

    @Delete
    fun delete(number: PhoneNumber)

    @Query("SELECT * FROM phonenumber ORDER BY id ASC")
    fun getAllPhoneNumbers(): LiveData<List<PhoneNumber>>
}