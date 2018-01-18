package com.playlist.video.player.ui.main

import com.playlist.video.player.data.model.Video
import com.playlist.video.player.ui.common.mvp.MvpView

interface MainContract {

    interface View : MvpView {

        fun showVideoList(videoList: List<Video>)

        fun showGenericError()
    }

    interface Presenter
}
