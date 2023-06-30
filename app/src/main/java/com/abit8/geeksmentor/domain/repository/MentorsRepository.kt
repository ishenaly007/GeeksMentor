package com.abit8.geeksmentor.domain.repository

import com.abit8.geeksmentor.data.model.MentorList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

interface MentorsRepository {
    suspend fun getMentors(): List<MentorList>
}

