package com.abit8.geeksmentor.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abit8.geeksmentor.databinding.FragmentSearchBinding

@Suppress("UNREACHABLE_CODE")
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)
        val textView: TextView = binding.textDashboard
        searchViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root

        binding.btnDrawer.setOnClickListener {
           //Logic for navigation drawer
            binding.navViewSearch.visibility = View.VISIBLE
            binding.navViewSearch.fitsSystemWindows = false

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}