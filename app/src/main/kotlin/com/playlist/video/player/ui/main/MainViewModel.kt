package com.playlist.video.player.ui.main

import android.arch.lifecycle.ViewModel
import com.playlist.video.player.data.model.Video
import com.playlist.video.player.data.repository.PlaylistRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val playlistRepository: PlaylistRepository) : ViewModel() {

    private val playList: MutableList<Video> = mutableListOf()

    fun getPlaylist(): Observable<List<Video>> {
        return if (playList.isEmpty()) {
            playlistRepository
                    .getPlaylist()
                    .subscribeOn(Schedulers.io())
                    .map { response -> response.body()!!
                            .apply { playList.addAll(this) } }
                    .observeOn(AndroidSchedulers.mainThread())
        } else {
            Observable.fromArray(playList)
        }
    }
}
