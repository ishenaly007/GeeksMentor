package com.abit8.geeksmentor.data.repository

import com.abit8.geeksmentor.data.model.LoginRequest
import com.abit8.geeksmentor.data.model.LoginResponse
import com.abit8.geeksmentor.data.remote.retrofit.ApiService
import com.abit8.geeksmentor.data.remote.retrofit.RetrofitClient
import com.abit8.geeksmentor.domain.repository.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    private val apiService: ApiService = RetrofitClient.apiService

    override suspend fun login(username: String, password: String): LoginResponse {
        val request = LoginRequest(username, password)
        return apiService.login(request)
    }
}