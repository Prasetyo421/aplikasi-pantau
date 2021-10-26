package com.didi.pantaucovid_19.fragment

import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.didi.pantaucovid_19.R
import com.didi.pantaucovid_19.databinding.FragmentMapsBinding
import com.didi.pantaucovid_19.viewmodel.SharedViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber

class MapsFragment : Fragment() {
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding
    var hospitalName: String? = null
    val sharedViewModel: SharedViewModel by activityViewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        Timber.d("hospital name: $hospitalName")
        val gcd = Geocoder(context)
        if (hospitalName != null){
            val result = gcd.getFromLocationName(this.hospitalName, 1)

            val latitude = result[0].latitude
            val longitude = result[0].longitude
            val hospital = LatLng(latitude, longitude)
            googleMap.addMarker(MarkerOptions().position(hospital).title("${this.hospitalName}"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(hospital))
        }else {
            Timber.d("hospitalname null")
        }}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)

        Timber.d("onCreatedView")

        sharedViewModel.paramDetailHospital.observe(viewLifecycleOwner, { param ->
            this.hospitalName = param.name
            Timber.d("onCreatedView set: ${param.name}")
        })

        Timber.d("onCreatedView: $hospitalName")

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated: $hospitalName")
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}