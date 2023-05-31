package com.abit8.geeksmentor.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.abit8.geeksmentor.R

class BestMentorListFragment : Fragment() {

    companion object {
        fun newInstance() = BestMentorListFragment()
    }

    private lateinit var viewModel: BestMentorListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_best_mentor_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BestMentorListViewModel::class.java)
        // TODO: Use the ViewModel
    }
}