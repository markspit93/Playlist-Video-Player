package com.playlist.video.player.ui.main

import com.playlist.video.player.di.scopes.PerActivity
import com.playlist.video.player.ui.common.mvp.MvpPresenter
import com.playlist.video.player.util.debugLog
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject constructor() : MvpPresenter<MainContract.View, MainViewModel>(), MainContract.Presenter {

    override fun loadPlaylist() {
        getViewModel().filterList?.forEach {
            debugLog("LOL " + it)
        }
    }
}
