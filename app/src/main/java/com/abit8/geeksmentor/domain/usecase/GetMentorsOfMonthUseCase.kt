package com.abit8.geeksmentor.domain.usecase

import com.abit8.geeksmentor.domain.repository.MentorRepository

class GetMentorsOfMonthUseCase(private val mentorRepository: MentorRepository) {
    fun getMentorsOfMonth() = mentorRepository.getMentorsOfMonth()

}