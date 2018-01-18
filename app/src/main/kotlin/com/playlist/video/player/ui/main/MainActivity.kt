package com.playlist.video.player.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.playlist.video.player.data.model.Video
import com.playlist.video.player.di.viewmodel.ViewModelFactory
import com.playlist.video.player.ext.get
import com.playlist.video.player.ui.common.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpActivity<MainContract.View, MainPresenter, MainViewModel>(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.playlist.video.player.R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun getViewModel(viewModelFactory: ViewModelFactory): MainViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get()
    }

    override fun showVideoList(videoList: List<Video>) {

    }

    override fun showGenericError() {

    }
}
