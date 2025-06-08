package com.phongbaoto.stormbookv2.exception

sealed class AppException(message: String): Exception(message) {
    class ApiException(message: String): AppException(message)
    class NetworkException(message: String): AppException(message)
}