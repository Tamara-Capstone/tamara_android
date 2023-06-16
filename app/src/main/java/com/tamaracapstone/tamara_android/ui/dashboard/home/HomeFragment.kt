package com.tamaracapstone.tamara_android.ui.dashboard.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tamaracapstone.tamara_android.databinding.FragmentHomeBinding
import com.tamaracapstone.tamara_android.ui.weather.WeatherActivity
import com.tamaracapstone.tamara_android.ui.analisisproduktifitas.noanalisis.NoAnalisisiActivity
import com.tamaracapstone.tamara_android.ui.scan.ScanActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
    }

    companion object {
        private val TAG = HomeFragment::class.java.simpleName
    }
}