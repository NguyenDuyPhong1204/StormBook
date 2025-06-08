package com.phongbaoto.stormbookv2.viewmodel.authViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.phongbaoto.stormbookv2.data.model.auth.AuthResponse
import com.phongbaoto.stormbookv2.data.repository.authRepository.AuthRepository
import com.phongbaoto.stormbookv2.exception.AppException
import com.phongbaoto.stormbookv2.utils.FunUtils
import com.phongbaoto.stormbookv2.utils.errorException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private var _loginResult = MutableStateFlow<Result<AuthResponse>?>(null)
    val loginResult = _loginResult.asStateFlow()

    //
    private val _isLoginSuccess = MutableStateFlow(false)
    val isLoginSuccess = _isLoginSuccess.asStateFlow()

    //
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    //
    private val _errorEmail = MutableStateFlow<String?>(null)
    val errorEmail = _errorEmail.asStateFlow()

    //
    private val _errorPassword = MutableStateFlow<String?>(null)
    val errorPassword = _errorPassword.asStateFlow()

    //
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val gson = Gson()

    private val prefs = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

    init {
        // Đọc giá trị khi khởi tạo ViewModel
        _isLoginSuccess.value = prefs.getBoolean("isLoginSuccess", false)
    }

    fun login(email: String, password: String) {
        _errorEmail.value = ""
        _errorPassword.value = ""
//        _errorMessage.value = ""
        when {
            email.isEmpty() -> {
                _errorMessage.value = "Vui lòng nhập email!"
                return
            }

            !FunUtils.isValidEmail(email) -> {
                _errorMessage.value = "Email không hợp lệ!"
                return
            }

            password.isEmpty() -> {
                _errorMessage.value = "Vui lòng nhập mật khẩu!"
                return
            }
        }
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.login(email, password)
            _loginResult.value = result
            result.fold(
                onSuccess = {
                    _isLoginSuccess.value = true

                    // Lưu vào SharedPreferences
                    prefs.edit().putBoolean("isLoginSuccess", true).apply()
                    //luu thong tin nguoi dung
                    val userJson = gson.toJson(it)
                    prefs.edit().putString("userInfo", userJson).apply()
                },
                onFailure = {
                    _isLoading.value = false
                    _isLoginSuccess.value = false
                    _errorMessage.value = when (it) {
                        is AppException.ApiException -> it.message
                        is AppException.NetworkException -> errorException
                        else -> errorException
                    }
                }
            )
        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.loginWithGoogle(idToken)
            _loginResult.value = result
            result.fold(
                onSuccess = {
                    _isLoginSuccess.value = true
                    // Lưu vào SharedPreferences
                    prefs.edit().putBoolean("isLoginSuccess", true).apply()
                    val userJson = gson.toJson(it)
                    prefs.edit().putString("userInfo", userJson).apply()
                },
                onFailure = {
                    _isLoading.value = false
                    _errorMessage.value = when (it) {
                        is AppException.ApiException -> it.message
                        is AppException.NetworkException -> errorException
                        else -> errorException
                    }
                }
            )
        }
    }


    fun clearLoginSuccess() {
        _isLoginSuccess.value = false
        prefs.edit().putBoolean("isLoginSuccess", false).apply()
    }

    //lay thong tin nguoi dung
    fun getUserInfo(): AuthResponse? {
        val userJson = prefs.getString("userInfo", null)
        return if (userJson != null) {
            gson.fromJson(userJson, AuthResponse::class.java)
        } else {
            null
        }
    }


}