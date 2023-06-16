package com.abit8.geeksmentor.presentation.home

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.data.model.Mentors
import com.abit8.geeksmentor.databinding.FragmentHomeBinding
import com.abit8.geeksmentor.presentation.home.adapter.MentorsMonthAdapter
import com.abit8.geeksmentor.presentation.home.adapter.MentorsUxUiAdapter
import com.abit8.geeksmentor.utils.NetworkChangeListener

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private val networkChangeListener: NetworkChangeListener by lazy {
        NetworkChangeListener()
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    //Month Mentors
    private lateinit var mentorsListMonth: ArrayList<Mentors>
    private lateinit var mentorsMonthAdapter: MentorsMonthAdapter
    //Ux/Ui Mentors
    private lateinit var mentorsListUxUi: ArrayList<Mentors>
    private lateinit var mentorsUxUiAdapter: MentorsUxUiAdapter

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
        sliderMonth()
        sliderUxUi()
    }

    private fun sliderUxUi() {
        recyclerView = binding.rv2Main
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        mentorsListUxUi = ArrayList()
        addDataMentorsUxUi()
        mentorsUxUiAdapter = MentorsUxUiAdapter(mentorsListUxUi)
        recyclerView.adapter = mentorsUxUiAdapter
    }

    private fun addDataMentorsUxUi() {
        mentorsListUxUi.add(Mentors(R.drawable.avatar, "Akbar", 13, 9,4))
        mentorsListUxUi.add(Mentors(R.drawable.avatar, "Ishenaly", 17, 13,4))
        mentorsListUxUi.add(Mentors(R.drawable.avatar, "Ahmad", 12, 11,1))
        mentorsListUxUi.add(Mentors(R.drawable.avatar, "Ayana", 5, 4,1))
        mentorsListUxUi.add(Mentors(R.drawable.avatar, "Kairat", 7, 7,0))
    }

    private fun sliderMonth() {
        recyclerView = binding.rv1Main
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        mentorsListMonth = ArrayList()
        addDataMentorsMonth()
        mentorsMonthAdapter = MentorsMonthAdapter(mentorsListMonth)
        recyclerView.adapter = mentorsMonthAdapter
    }

    private fun addDataMentorsMonth(){
        mentorsListMonth.add(Mentors(R.drawable.avatar, "Akbar", 13, 9,4))
        mentorsListMonth.add(Mentors(R.drawable.avatar, "Ishenaly", 17, 13,4))
        mentorsListMonth.add(Mentors(R.drawable.avatar, "Ahmad", 12, 11,1))
        mentorsListMonth.add(Mentors(R.drawable.avatar, "Ayana", 5, 4,1))
        mentorsListMonth.add(Mentors(R.drawable.avatar, "Kairat", 7, 7,0))
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