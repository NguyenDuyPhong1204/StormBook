package com.phongbaoto.stormbookv2.ui.profile.data

import com.phongbaoto.stormbookv2.utils.history
import com.phongbaoto.stormbookv2.utils.logout
import com.phongbaoto.stormbookv2.utils.love
import com.phongbaoto.stormbookv2.utils.question
import com.phongbaoto.stormbookv2.utils.report
import com.phongbaoto.stormbookv2.utils.setting
import com.phongbaoto.stormbookv2.utils.story

object ListMenu {
    val listUMenuUser = listOf(
        Item(
            icon = love,
            title = "Truyện yêu thích",
            onClick = {}
        ),
        Item(
            icon = history,
            title = "Lịch sử đọc truyện",
            onClick = {}
        ),
        Item(
            icon = setting,
            title = "Cài đặt thông tin",
            onClick = {}
        ),
        Item(
            icon = question,
            title = "Phản hồi ý kiến",
            onClick = {}
        ),
        Item(
            icon = question,
            title = "Giới thiệu về chúng tôi",
            onClick = {}
        ),
        Item(
            icon = logout,
            title = "Đăng xuất",
            onClick = {}
        )
    )

    val listMenuAdmin = listOf(
        Item(
            icon = story,
            title = "Danh sách truyện đã đăng",
            onClick = {}
        ),
        Item(
            icon = report,
            title = "Danh sách báo cáo",
            onClick = {}
        ),
        Item(
            icon = history,
            title = "Lịch sử đọc truyện",
            onClick = {}
        ),
        Item(
            icon = setting,
            title = "Cài đặt thông tin",
            onClick = {}
        ),
        Item(
            icon = question,
            title = "Phản hồi ý kiến",
            onClick = {}
        ),
        Item(
            icon = question,
            title = "Giới thiệu về chúng tôi",
            onClick = {}
        ),
        Item(
            icon = logout,
            title = "Đăng xuất",
            onClick = {}
        )
    )
}