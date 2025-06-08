package com.phongbaoto.stormbookv2.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.phongbaoto.stormbookv2.utils.FunUtils

class NetworkReceiver(private val onNetworkChanged: (Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val isConnected = FunUtils.isNetworkAvailable(context)
        onNetworkChanged(isConnected)
    }
}
