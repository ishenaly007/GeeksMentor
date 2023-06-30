package com.abit8.geeksmentor.data.remote.retrofit

import com.abit8.geeksmentor.data.model.ApiResponse
import com.abit8.geeksmentor.data.model.LoginRequest
import com.abit8.geeksmentor.data.model.LoginResponse
import com.abit8.geeksmentor.data.model.MentorList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("products")
    suspend fun getAllMentors(): Response<ApiResponse>

    //@GET("products")
    //suspend fun getAllMentors(): List<MentorList>

    // Другие методы API здесь
}
