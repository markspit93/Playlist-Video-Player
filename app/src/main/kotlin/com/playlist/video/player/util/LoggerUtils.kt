package com.playlist.video.player.util

import android.util.Log
import com.playlist.video.player.BuildConfig

fun debugLog(text: String) {
    if (BuildConfig.DEBUG_MODE) {
        Log.d("Playlist Video Player", text)
    }
}
