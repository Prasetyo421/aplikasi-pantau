package com.didi.pantaucovid_19.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhoneNumber::class], version = 1)
abstract class PhoneNumberDatabase: RoomDatabase() {
    abstract fun phoneNumberDao(): PhoneNumberDao

    companion object {
        private const val EXTRA_TABLE_NAME = "phone_number_database"
        @Volatile
        private var INSTANCE: PhoneNumberDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): PhoneNumberDatabase{
            if (INSTANCE == null){
                synchronized(PhoneNumberDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PhoneNumberDatabase::class.java,
                        EXTRA_TABLE_NAME).build()
                }

            }
            return INSTANCE as PhoneNumberDatabase
        }
    }
}