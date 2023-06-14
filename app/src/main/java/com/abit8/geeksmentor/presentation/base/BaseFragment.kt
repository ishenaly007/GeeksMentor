package com.abit8.geeksmentor.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.abit8.geeksmentor.domain.utils.NetworkError
import com.abit8.geeksmentor.domain.utils.UIState
import com.abit8.geeksmentor.presentation.showToastLong
import com.abit8.geeksmentor.presentation.showToastShort
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseFragment<
        ViewModel : BaseViewModel,
        Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val viewModel: ViewModel
    protected abstract val binding: Binding

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupSubscribers()
        setupRequests()
        initListeners()
    }

    protected open fun initialize() {}

    protected open fun initListeners() {}

    protected open fun setupRequests() {}

    protected open fun setupSubscribers() {}

    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        uiState: ((UIState<T>) -> Unit)? = null,
        // потом доработать  onLoading: () -> Unit,
        onSuccess: (data: T) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectUIState.collect { state ->
                    uiState?.invoke(state)
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            showToastShort(state.message)
                        }
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            onSuccess(state.data)
                        }
                    }
                }
            }
        }
    }
}

//    protected fun <T : Any> Flow<PagingData<T>>.collectPaging(
//        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
//        action: suspend (value: PagingData<T>) -> Unit
//    ) {
//        launchRepeatOnLifecycle(lifecycleState) { this@collectPaging.collectLatest { action(it) } }
//    }
//
//    protected fun <T> UIState<T>.setupViewVisibility(
//        group: Group, loader: CircularProgressIndicator, willShowViewIfSuccess: Boolean = true
//    ) {
//        fun showLoader(isVisible: Boolean) {
//            group.isVisible = !isVisible
//            loader.isVisible = isVisible
//        }
//
//        when (this) {
//            is UIState.Empty -> {}
//            is UIState.Loading -> showLoader(true)
//            is UIState.Error -> showLoader(false)
//            is UIState.Success -> showLoader(!willShowViewIfSuccess)
//        }
//    }
//
//    protected fun NetworkError.setupApiErrors(vararg inputs: TextInputLayout) = when (this) {
//        is NetworkError.Unexpected -> {
//            showToastLong(this.error)
//        }
//
//        is NetworkError.Api -> {
//            this.error?.let { showToastLong(it) }
//        }
//
//        is NetworkError.ApiInputs -> {
//            for (input in inputs) {
//                error?.get(input.tag).also { error ->
//                    if (error == null) {
//                        input.isErrorEnabled = false
//                    } else {
//                        input.error = error.joinToString()
//                        this.error?.remove(input.tag)
//                    }
//                }
//            }
//        }
//
//        is NetworkError.Timeout -> {
//            showToastLong("Timeout")
//        }
//    }
//
//    protected fun <T> Flow<T>.collectSafely(
//        state: Lifecycle.State = Lifecycle.State.STARTED,
//        collector: (T) -> Unit
//    ) {
//        launchRepeatOnLifecycle(state) {
//            this@collectSafely.collect {
//                collector(it)
//            }
//        }
//    }
//
//    private fun launchRepeatOnLifecycle(
//        state: Lifecycle.State,
//        block: suspend CoroutineScope.() -> Unit
//    ) {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(state) {
//                block()
//            }
//        }
//    }
