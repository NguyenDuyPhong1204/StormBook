package com.phongbaoto.stormbookv2.viewmodel.authViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phongbaoto.stormbookv2.data.model.ApiResponse
import com.phongbaoto.stormbookv2.data.model.auth.AuthResponse
import com.phongbaoto.stormbookv2.data.repository.authRepository.AuthRepository
import com.phongbaoto.stormbookv2.utils.FunUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository,
    @ApplicationContext private val context: Context
): ViewModel(){
    private var _registerResult = MutableStateFlow<Result<ApiResponse<AuthResponse>>?>(null)
    val registerResult = _registerResult.asStateFlow()

    //
    private val _isRegisterSuccess = MutableStateFlow(false)
    val isRegisterSuccess = _isRegisterSuccess.asStateFlow()
    //
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()
    //
    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage = _successMessage.asStateFlow()
    //
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    //

    fun register(email: String, name: String, password: String, confirmPass: String){
        when{
            email.isEmpty() -> {
                _errorMessage.value = "Vui lòng nhập email!"
                return
            }
            !FunUtils.isValidEmail(email) -> {
                _errorMessage.value = "Email không hợp lệ!"
                return
            }
            name.isEmpty() -> {
                _errorMessage.value = "Vui lòng nhập tên hoặc biệt danh của bạn!"
                return
            }
            password.isEmpty() -> {
                _errorMessage.value = "Vui lòng nhập mật khẩu!"
                return
            }

            confirmPass.isEmpty() -> {
                _errorMessage.value = "Vui lòng nhập lại mật khẩu!"
                return
            }
            password != confirmPass -> {
                _errorMessage.value = "Mật khẩu không khớp!"
                return
            }
        }

        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.register(email, password, name)
            _registerResult.value = result
            result.fold(
                onSuccess = {
                    _isLoading.value = false
                    _successMessage.value = it.message
                    _isRegisterSuccess.value = true
                },
                onFailure = {
                    _isLoading.value = false
                    _isRegisterSuccess.value = false
                    _errorMessage.value = it.message
                }
            )
        }

    }


}