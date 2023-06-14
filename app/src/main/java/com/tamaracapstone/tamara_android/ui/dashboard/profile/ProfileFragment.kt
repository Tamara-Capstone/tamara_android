package com.tamaracapstone.tamara_android.ui.dashboard.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.tamaracapstone.tamara_android.Locator
import com.tamaracapstone.tamara_android.databinding.FragmentProfileBinding
import com.tamaracapstone.tamara_android.ui.login.LoginActivity
import com.tamaracapstone.tamara_android.ui.splash.SplashActivity
import com.tamaracapstone.tamara_android.utils.launchAndCollectIn

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel>(factoryProducer = { Locator.profileViewModelFactory })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profileState.launchAndCollectIn(this) {
            binding.tvUsername.text = it.name
        }

        binding.btnKeluar.setOnClickListener {
            viewModel.logout()
            Intent(requireActivity(), SplashActivity::class.java).apply {
                requireActivity().startActivity(this)
                requireActivity().finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUser()
    }

    companion object {
        private val TAG = ProfileFragment::class.java.simpleName
    }
}