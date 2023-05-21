package com.abit8.geeksmentor.presentation.home

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.registerReceiver
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.abit8.geeksmentor.databinding.FragmentHomeBinding
import com.abit8.geeksmentor.utils.NetworkChangeListener

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private val networkChangeListener: NetworkChangeListener by lazy {
        NetworkChangeListener()
    }

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /* val homeViewModel =
             ViewModelProvider(this).get(HomeViewModel::class.java)
         val textView: TextView = binding.textHome
         homeViewModel.text.observe(viewLifecycleOwner) {
             textView.text = it
         }*/
        return root
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