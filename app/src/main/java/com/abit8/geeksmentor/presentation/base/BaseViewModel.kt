package com.abit8.geeksmentor.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abit8.geeksmentor.domain.utils.Resource
import com.abit8.geeksmentor.domain.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> Flow<Resource<T>>.collectFlow(_state: MutableStateFlow<UIState<T>>) {
        viewModelScope.launch {
            collect { res ->
                when (res) {
                    is Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }

                    is Resource.Error -> {
                        _state.value = UIState.Error(res.message!!)
                    }

                    is Resource.Success -> {
                        if (res.data != null) {
                            _state.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }
}
