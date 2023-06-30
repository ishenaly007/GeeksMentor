package com.abit8.geeksmentor.domain.repository

import com.abit8.geeksmentor.data.model.LoginResponse

interface LoginRepository {
    suspend fun login(username: String, password: String): LoginResponse
}