package com.tamaracapstone.tamara_android.ui.dashboard.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tamaracapstone.tamara_android.Locator
import com.tamaracapstone.tamara_android.databinding.FragmentHomeBinding
import com.tamaracapstone.tamara_android.domain.entity.WeatherEntity
import com.tamaracapstone.tamara_android.ui.analisisproduktifitas.noanalisis.NoAnalisisiActivity
import com.tamaracapstone.tamara_android.ui.dashboard.profile.ProfileFragment
import com.tamaracapstone.tamara_android.ui.scan.ScanActivity
import com.tamaracapstone.tamara_android.ui.weather.WeatherActivity
import com.tamaracapstone.tamara_android.utils.ResultState
import com.tamaracapstone.tamara_android.utils.launchAndCollectIn

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>(factoryProducer = { Locator.homeViewModelFactory })
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (hasLocationPermissions()) {
            fetchWeatherData()
        }

        binding.itemCuacaHome.cardItemCuaca.setOnClickListener {
            Intent(requireActivity(), WeatherActivity::class.java).apply {
                requireActivity().startActivity(this)
            }
        }

        binding.itemScanTanamanHome.btnTakepicture.setOnClickListener {
            Intent(requireActivity(), ScanActivity::class.java).apply {
                requireActivity().startActivity(this)
            }
        }

        binding.itemAnalisisProduktivitas.btnAnalisis.setOnClickListener {
            Intent(requireActivity(), NoAnalisisiActivity::class.java).apply {
                requireActivity().startActivity(this)
            }
        }

        viewModel.weatherDashboardViewState.launchAndCollectIn(this) { state ->
            when (state.resultWeather) {
                is ResultState.Success<WeatherEntity> -> {
                    binding.itemCuacaHome.loadingCuacaDashboard.visibility = android.view.View.GONE
                    state.resultWeather.data?.let {
                        binding.itemCuacaHome.alamatCardCuaca.text = it.kecamatan
                        binding.itemCuacaHome.tvTanggal.text = it.jamCuaca
                        binding.itemCuacaHome.temperatureValue.text = it.tempC
                        binding.itemCuacaHome.tvCuaca.text = it.cuaca
                        binding.itemCuacaHome.tvSranaKegiatan.text = it.recommendation
                    }
                }

                is ResultState.Loading -> binding.itemCuacaHome.loadingCuacaDashboard.visibility = android.view.View.VISIBLE
                is ResultState.Error -> {
                    binding.itemCuacaHome.loadingCuacaDashboard.visibility = View.GONE
                    val message = state.resultWeather.message ?: "Unknown error occurred"
                    Toast.makeText(
                        requireContext(), message, Toast.LENGTH_SHORT
                    ).show()
                }

                else -> Unit
            }
        }
    }

    private fun hasLocationPermissions(): Boolean {
        val fineLocationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        val coarseLocationPermission = Manifest.permission.ACCESS_COARSE_LOCATION
        val fineLocationPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(), fineLocationPermission
        ) == PackageManager.PERMISSION_GRANTED
        val coarseLocationPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(), coarseLocationPermission
        ) == PackageManager.PERMISSION_GRANTED

        return fineLocationPermissionGranted && coarseLocationPermissionGranted
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchWeatherData()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Location permission denied. Weather data cannot be fetched.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun checkLocationPermissionAndFetchWeather() {
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasFineLocationPermission && hasCoarseLocationPermission) {
            fetchWeatherData()
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun fetchWeatherData() {
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val lat = location.latitude.toFloat()
                    val lon = location.longitude.toFloat()
                    viewModel.getWeatherDashboard(lat, lon)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Failed to retrieve location. Weather data cannot be fetched.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener { exception ->
                // Handle any errors that occurred during location retrieval
                Toast.makeText(
                    requireContext(),
                    "Failed to retrieve location. Weather data cannot be fetched.",
                    Toast.LENGTH_SHORT
                ).show()
                exception.printStackTrace()
            }
        } catch (e: SecurityException) {
            // Handle the case where location permission is not granted
            Toast.makeText(
                requireContext(),
                "Location permission not granted. Weather data cannot be fetched.",
                Toast.LENGTH_SHORT
            ).show()
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

    }

    companion object {
        private val TAG = HomeFragment::class.java.simpleName
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }
}
