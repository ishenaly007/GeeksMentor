package com.abit8.geeksmentor.data.remote

import com.abit8.geeksmentor.data.model.MentorDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("users")
    suspend fun getAndroidMentors(): List<MentorDto>

    @GET("users")
    suspend fun getBackEndMentors(): List<MentorDto>

    @GET("users")
    suspend fun getFrontEndMentors(): List<MentorDto>

    @GET("users")
    suspend fun getUXUIMentors(): List<MentorDto>

    @GET("users")
    suspend fun getMentorsOfMonth(): List<MentorDto>

    @GET("users")
    suspend fun getMentorsBySearch(@Query("search") search: String): List<MentorDto>
}