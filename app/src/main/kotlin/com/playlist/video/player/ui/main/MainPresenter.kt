package com.playlist.video.player.ui.main

import com.playlist.video.player.di.scopes.PerActivity
import com.playlist.video.player.ui.common.mvp.MvpPresenter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject constructor() : MvpPresenter<MainContract.View, MainViewModel>(), MainContract.Presenter {

    override fun onViewActive() {
       getViewModel().getPlaylist().subscribeBy(
                onNext = {
                    view?.showVideoList(it)
                },
                onError = {
                    view?.showGenericError()
                }
        )
    }
}
