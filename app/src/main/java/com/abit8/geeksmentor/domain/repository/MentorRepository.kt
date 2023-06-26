package com.abit8.geeksmentor.domain.repository

import com.abit8.geeksmentor.data.model.MentorModel
import com.abit8.geeksmentor.domain.model.Mentor
import com.abit8.geeksmentor.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MentorRepository {
    fun getAndroidMentors(): Flow<Resource<List<Mentor>>>
    fun getBackEndMentors(): Flow<Resource<List<Mentor>>>
    fun getFrontEndMentors(): Flow<Resource<List<Mentor>>>
    fun getUXUIMentors(): Flow<Resource<List<Mentor>>>
    fun getMentorsOfMonth(): Flow<Resource<List<Mentor>>>
    fun getMentorsBySearch(search: String): Flow<Resource<List<Mentor>>>
}