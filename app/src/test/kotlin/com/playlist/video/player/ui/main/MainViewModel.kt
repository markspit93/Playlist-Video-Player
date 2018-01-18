package com.playlist.video.player.ui.main

import android.arch.lifecycle.ViewModel
import com.github.feed.sample.data.model.Filter

class MainViewModel : ViewModel() {

    val filterList: List<Filter> = listOf(Filter(0, "test", true))
}
