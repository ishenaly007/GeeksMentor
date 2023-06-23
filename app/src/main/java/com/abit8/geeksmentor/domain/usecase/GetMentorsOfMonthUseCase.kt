package com.abit8.geeksmentor.domain.usecase

import com.abit8.geeksmentor.domain.repository.MentorRepository
import javax.inject.Inject

class GetMentorsOfMonthUseCase @Inject constructor(private val mentorRepository: MentorRepository) {
    fun getMentorsOfMonth() = mentorRepository.getMentorsOfMonth()

}