package com.abit8.geeksmentor.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abit8.geeksmentor.data.model.LoginResponse
import com.abit8.geeksmentor.domain.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> get() = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> get() = _lastName

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> get() = _image

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> get() = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                _email.value = response.email
                _firstName.value = response.firstName
                _lastName.value = response.lastName
                _image.value = response.image
                _loginResult.value = Result.success(response)

            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}
