package com.playlist.video.player.ui.main

import com.playlist.video.player.di.scopes.PerActivity
import com.playlist.video.player.ui.common.mvp.MvpPresenter
import javax.inject.Inject

@PerActivity
class MainPresenter @Inject constructor() : MvpPresenter<MainContract.View, MainViewModel>(), MainContract.Presenter {

    override fun loadPlaylist() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun loadFilterSelections() {
//        getViewModel().filterList.forEach {
//            view?.selectFilter(it.tag, it.checked)
//        }
//    }

//    override fun saveFilterSelection(tag: String, checked: Boolean) {
//        getViewModel().saveFilter(tag, checked)
//    }
}
