package com.abit8.geeksmentor.presentation.fragment.home

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.databinding.FragmentHomeBinding
import com.abit8.geeksmentor.utils.NetworkChangeListener

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private val networkChangeListener: NetworkChangeListener by lazy {
        NetworkChangeListener()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        binding.apply {
            tvMentorsOfMonth.setOnClickListener {
                findNavController().navigate(R.id.bestMentorListFragment)
            }
            tvAllMentors.setOnClickListener {
                findNavController().navigate(R.id.mentorListFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireActivity().registerReceiver(networkChangeListener, filter)
        super.onStart()
    }

    override fun onStop() {
        requireActivity().unregisterReceiver(networkChangeListener)
        super.onStop()
    }
}