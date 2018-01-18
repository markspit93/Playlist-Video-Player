package com.playlist.video.player.ui.main

import android.arch.lifecycle.ViewModel
import com.playlist.video.player.data.repository.PlaylistRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val filterRepository: PlaylistRepository) : ViewModel() {

//    val filterList: MutableList<Video> by lazyAndroid { filterRepository.getFilters().toMutableList() }
//
//    fun saveFilter(tag: String, checked: Boolean) {
//        var filter: Video? = filterList.find { it.tag == tag }
//
//        if (filter == null) {
//            filter = Video(tag = tag, checked = checked)
//            filterList.add(filter)
//            filterRepository.saveFilter(filter)
//        } else {
//            filter.checked = checked
//            filterRepository.saveFilter(filter)
//        }
//    }
}
