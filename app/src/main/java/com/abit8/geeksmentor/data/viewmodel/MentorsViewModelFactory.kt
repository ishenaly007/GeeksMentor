package com.abit8.geeksmentor.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abit8.geeksmentor.domain.repository.MentorsRepository

/*class MentorsViewModelFactory(private val repository: MentorsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MentorListViewModel::class.java)) {
            return MentorListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
    }
}*/
