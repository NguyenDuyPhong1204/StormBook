package com.phongbaoto.stormbookv2.utils

import com.phongbaoto.stormbookv2.exception.AppException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

suspend fun <T> apiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        val result = apiCall()
        Result.success(result)
    } catch (e: HttpException) {
        val errorBody = e.response()?.errorBody()?.string()
        val message = errorBody?.let {
            try {
                JSONObject(it).getString("message")
            } catch (e: JSONException) {
                "Lỗi không xác định"
            }
        } ?: "Lỗi không xác định"
        Result.failure(AppException.ApiException(message))
    } catch (e: Exception) {
        Result.failure(AppException.NetworkException("Không thể kết nối đến máy chủ, vui lòng thử lại sau"))
    }
}
