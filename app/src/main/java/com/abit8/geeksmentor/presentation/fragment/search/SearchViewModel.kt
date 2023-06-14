package com.abit8.geeksmentor.presentation.fragment.search

import com.abit8.geeksmentor.domain.model.Mentor
import com.abit8.geeksmentor.domain.usecase.GetMentorsOfMonthUseCase
import com.abit8.geeksmentor.domain.utils.UIState
import com.abit8.geeksmentor.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel(
    private val getMentorsOfMonthUseCase: GetMentorsOfMonthUseCase,
) : BaseViewModel() {

    private val _getMentorsOfMonth = MutableStateFlow<UIState<List<Mentor>>>(UIState.Empty())
    val getMentorsOfMonth = _getMentorsOfMonth.asStateFlow()


    fun getMentorsOfMonth() =
        getMentorsOfMonthUseCase.getMentorsOfMonth().collectFlow(_getMentorsOfMonth)
}