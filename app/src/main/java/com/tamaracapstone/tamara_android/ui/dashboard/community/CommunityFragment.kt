package com.tamaracapstone.tamara_android.ui.dashboard.community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tamaracapstone.tamara_android.databinding.FragmentCommunityBinding
import com.tamaracapstone.tamara_android.ui.dashboard.community.askcommunity.AskCommunityActivity
import com.tamaracapstone.tamara_android.ui.scan.ScanActivity

class CommunityFragment : Fragment() {
    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddNewDiscussion.setOnClickListener {
            Intent(requireActivity(), AskCommunityActivity::class.java).apply {
                requireActivity().startActivity(this)
            }
        }
    }

    companion object {
        private val TAG = CommunityFragment::class.java.simpleName
    }
}