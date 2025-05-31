package com.phongbaoto.vnstormbook.utils

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
        Result.failure(Exception(message))
    } catch (e: Exception) {
        Result.failure(e)
    }
}
