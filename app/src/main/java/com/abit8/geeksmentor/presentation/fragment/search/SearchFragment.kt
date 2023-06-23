package com.abit8.geeksmentor.presentation.fragment.search

import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.abit8.geeksmentor.R
import com.abit8.geeksmentor.databinding.FragmentSearchBinding
import com.abit8.geeksmentor.domain.model.Mentor
import com.abit8.geeksmentor.presentation.adapter.MentorsOfMonthAdapter
import com.abit8.geeksmentor.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<SearchViewModel, FragmentSearchBinding>(R.layout.fragment_search) {

    override val viewModel by viewModels<SearchViewModel>()

    override val binding by viewBinding(FragmentSearchBinding::bind)
    val adapter: MentorsOfMonthAdapter by lazy { MentorsOfMonthAdapter(this::onClick) }


    override fun initialize() {
        initSomeThins()
    }

    override fun setupSubscribers() {
        subscribeToSearch()
    }

    override fun setupRequests() {
        getMentorsOfMonth()
    }

    override fun initListeners() {
        clickListeners()
    }

    private fun initSomeThins() {
        binding.rv1Search.adapter = adapter

    }

    private fun subscribeToSearch() = with(binding) {
        viewModel.getMentorsOfMonth.collectUIState(
            uiState = {
                // it.setupViewVisibility()
            },
            onSuccess = {

            }
        )
    }

    private fun getMentorsOfMonth() {
        viewModel.getMentorsOfMonth()
    }

    private fun clickListeners() {
        binding.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        binding.btnDrawer.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.END)
        }
    }

    private fun onClick(mentor: Mentor) {
        findNavController().navigate(
            R.id.action_profileFragment_to_mentorsDataFragment, bundleOf()
        )
    }
}
