package com.didi.pantaucovid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.didi.pantaucovid_19.databinding.ActivityMainBinding
import com.didi.pantaucovid_19.fragment.AboutFragment
import com.didi.pantaucovid_19.fragment.CovidFragment
import com.didi.pantaucovid_19.fragment.EmergencyNumberFragment
import com.didi.pantaucovid_19.fragment.HospitalFragment
import kotlinx.coroutines.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var fragment: Fragment? = null
    private var tags = arrayListOf<String?>()
    private var tag: String? = null
    private var doubleBackToExitPressedOnce: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        val numberTag = EmergencyNumberFragment::class.simpleName
        val covidTag = CovidFragment::class.simpleName
        val hospitalTag = HospitalFragment::class.simpleName
        val aboutTag = AboutFragment::class.simpleName

        tags.add(numberTag)
        tags.add(covidTag)
        tags.add(hospitalTag)
        tags.add(aboutTag)

        supportActionBar?.title = "username"

        supportActionBar?.title = resources.getString(R.string.emergency_number)
        fragment = EmergencyNumberFragment.newInstance()
        tag = numberTag
        changeFragment(fragment, tag)
        binding.bottomNavigation.setOnItemSelectedListener { menu ->
            when(menu.itemId){
                R.id.action_emergency_number -> {
                    Timber.d("current thread number: ${Thread.currentThread()}")
                    supportActionBar?.title = resources.getString(R.string.emergency_number)
                    fragment = EmergencyNumberFragment.newInstance()
                    tag = numberTag
                    Timber.d("number tag = $tag")
                }
                R.id.action_covid -> {
                    supportActionBar?.title =
                        resources.getString(R.string.covid)
                    fragment = CovidFragment.newInstance()
                    tag = covidTag
                }
                R.id.action_hospital -> {
                    supportActionBar?.title =
                        resources.getString(R.string.hospital)
                    fragment = HospitalFragment.newInstance()
                    tag = hospitalTag
                }
                R.id.action_about -> {
                    supportActionBar?.title =
                        resources.getString(R.string.about)
                    fragment = AboutFragment.newInstance()
                    tag = aboutTag
                }
            }

            return@setOnItemSelectedListener changeFragment(fragment, tag)
        }
    }

    private fun changeFragment(f: Fragment?, tag: String?): Boolean {

        if (f != null){
            lifecycleScope.launch {
                supportFragmentManager.commit {
                    Timber.d("current thread changeFragment(): ${Thread.currentThread()}")
                    replace(R.id.frame_container, f, tag)
                    Timber.d("create tag: $tag")
                }
            }
            return true
        }
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }

        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

}