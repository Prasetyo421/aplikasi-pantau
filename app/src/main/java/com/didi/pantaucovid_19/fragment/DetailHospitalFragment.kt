package com.didi.pantaucovid_19.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.viewpager2.widget.ViewPager2
import com.didi.pantaucovid_19.R
import com.didi.pantaucovid_19.adapter.SectionPagerAdapterDetailHospital
import com.didi.pantaucovid_19.databinding.FragmentDetailHospitalBinding
import com.didi.pantaucovid_19.viewmodel.SharedViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailHospitalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailHospitalFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentDetailHospitalBinding? = null
    private val binding get() = _binding
    private val model: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Timber.d("")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailHospitalBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.paramDetailHospital.observe(viewLifecycleOwner, {
            activity?.title = it.name
        })

        val mapsFragment = MapsFragment()
        childFragmentManager.commit {
            addToBackStack("...")
            replace(R.id.frame_maps, mapsFragment)
        }

        val sectionPagerAdapter = SectionPagerAdapterDetailHospital(this)
        val viewPager: ViewPager2 = binding?.viewPager as ViewPager2
        viewPager.adapter = sectionPagerAdapter
        val tabs = binding?.tabs as TabLayout
        TabLayoutMediator(tabs, viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }

    companion object {
        val TAB_TITLES = intArrayOf(
            R.string.covid,
            R.string.non_covid
        )
        @JvmStatic
        fun newInstance() = DetailHospitalFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}