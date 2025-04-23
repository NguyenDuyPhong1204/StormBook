package com.phongbaoto.stormbook.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
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
//         Parse chuỗi thành LocalDateTime
        val dateTime = LocalDateTime.parse(
            dateTimeString,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
        )

        // Format LocalDateTime thành chuỗi mới
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    fun formatRelativeTime(timeStr: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
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

}