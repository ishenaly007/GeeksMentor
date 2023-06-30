package com.abit8.geeksmentor.data.repository

import com.abit8.geeksmentor.data.model.ApiResponse
import com.abit8.geeksmentor.data.model.MentorList
import com.abit8.geeksmentor.data.remote.retrofit.ApiService
import com.abit8.geeksmentor.data.remote.retrofit.RetrofitClient.apiService
import com.abit8.geeksmentor.domain.repository.MentorsRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

/*
class MentorsRepositoryImpl(private var apiService: ApiService) : MentorsRepository {
    override suspend fun getMentors(): List<MentorList> {
        return apiService.getAllMentors()
    }
}*/
