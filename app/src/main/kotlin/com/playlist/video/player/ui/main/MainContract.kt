package com.playlist.video.player.ui.main

import com.playlist.video.player.ui.common.mvp.MvpView

interface MainContract {

    interface View : MvpView {

    }

    interface Presenter {
        fun loadPlaylist()
    }
}
