package com.abit8.geeksmentor.data.remote

import com.abit8.geeksmentor.data.model.MentorEntity
import com.abit8.geeksmentor.domain.model.Mentor
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("users")
    suspend fun getAndroidMentors(): List<MentorEntity>

    @GET("users")
    suspend fun getBackEndMentors(): List<MentorEntity>

    @GET("users")
    suspend fun getFrontEndMentors(): List<MentorEntity>

    @GET("users")
    suspend fun getUXUIMentors(): List<MentorEntity>

    @GET("users")
    suspend fun getMentorsOfMonth(): List<MentorEntity>

    @GET("users")
    suspend fun getMentorsBySearch(@Query("search") search: String): List<MentorEntity>
}