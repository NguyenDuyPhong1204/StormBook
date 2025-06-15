package com.phongbaoto.stormbookv2.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object FunUtils {
    fun createImageFileAndUri(context: Context): Uri {
        val timestamp = SimpleDateFormat("HH:mm - dd-MM-yyyy", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(
            "JPEG_${timestamp}_",
            ".jpg",
            storageDir
        )
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    fun convertDateTime(dateTimeString: String): String {
        val odt = OffsetDateTime.parse(dateTimeString) // Tự động hiểu ISO 8601 + Z
        val localDateTime = odt.toLocalDateTime()      // Bỏ thông tin múi giờ nếu cần

        return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }


    fun formatRelativeTime(timeStr: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS SSS")
        val parsedTime = LocalDateTime.parse(timeStr, formatter)

        val now = LocalDateTime.now(ZoneId.systemDefault())

        val duration = Duration.between(parsedTime, now)
        val minutes = duration.toMinutes()
        val hours = duration.toHours()
        val days = duration.toDays()

        return when {
            minutes < 1 -> "Vừa xong"
            minutes < 60 -> "$minutes phút trước"
            hours < 24 -> "$hours giờ trước"
            days < 7 -> "$days ngày trước"
            else -> parsedTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        }
    }

    //kiem tra ket noi mang
    fun isNetworkAvailable(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    //kiem tra email
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return email.matches(emailRegex.toRegex())
    }

}