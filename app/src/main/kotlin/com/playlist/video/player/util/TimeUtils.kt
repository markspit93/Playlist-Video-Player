package com.playlist.video.player.util

import java.util.concurrent.TimeUnit

fun formatTime(millis: Long): String {
    return String.format("%d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(millis),
        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
    )
}
