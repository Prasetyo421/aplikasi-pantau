package com.didi.pantaucovid_19.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.didi.pantaucovid_19.fragment.ListHospitalFragment

class SectionPagerAdapterDetailHospital(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return ListHospitalFragment.newInstance(position)
    }
}