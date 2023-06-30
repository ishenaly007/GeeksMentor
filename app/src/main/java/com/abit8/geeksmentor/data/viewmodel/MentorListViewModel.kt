package com.abit8.geeksmentor.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abit8.geeksmentor.data.model.MentorList
//import com.abit8.geeksmentor.data.repository.MentorsRepositoryImpl
import com.abit8.geeksmentor.domain.repository.MentorsRepository
import kotlinx.coroutines.launch

/*class MentorListViewModel(private val repository: MentorsRepositoryImpl) : ViewModel() {
    private val _mentors = MutableLiveData<List<MentorList>>()
    val mentors: LiveData<List<MentorList>> get() = _mentors

    fun loadMentors() {
        viewModelScope.launch {
            try {
                val mentorList = repository.getMentors()
                _mentors.value = mentorList
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }

}*/

