package com.abit8.geeksmentor.domain.usecase

import com.abit8.geeksmentor.domain.model.Mentor
import com.abit8.geeksmentor.domain.repository.MentorRepository
import com.abit8.geeksmentor.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAndroidMentorsUseCase(private val mentorRepository: MentorRepository) {
    fun getAndroidMentors() = mentorRepository.getAndroidMentors()
}