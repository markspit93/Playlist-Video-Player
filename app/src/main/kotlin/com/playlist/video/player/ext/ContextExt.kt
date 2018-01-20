package com.playlist.video.player.ext

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager

fun Context.hasInternetConnection(): Boolean {
    val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}
