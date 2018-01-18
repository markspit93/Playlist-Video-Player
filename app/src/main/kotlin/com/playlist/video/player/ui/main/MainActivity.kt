package com.playlist.video.player.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.playlist.video.player.R
import com.playlist.video.player.data.model.Video
import com.playlist.video.player.di.viewmodel.ViewModelFactory
import com.playlist.video.player.ext.get
import com.playlist.video.player.ext.lazyAndroid
import com.playlist.video.player.ui.common.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : MvpActivity<MainContract.View, MainPresenter, MainViewModel>(), MainContract.View {

    private val mainAdapter by lazyAndroid { MainAdapter(onVideoClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.playlist.video.player.R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mainAdapter
    }

    override fun getViewModel(viewModelFactory: ViewModelFactory): MainViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get()
    }

    override fun showVideoList(videoList: List<Video>) {
        mainAdapter.addItems(videoList)
    }

    override fun showGenericError() {
        toast(R.string.error_loading)
    }

    private val onVideoClicked: (Int) -> Unit = { position ->
        // TODO
    }
}
