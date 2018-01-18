package com.playlist.video.player.ui

import android.arch.lifecycle.ViewModel
import com.github.feed.sample.ui.common.mvp.MvpPresenter
import com.github.feed.sample.ui.common.mvp.MvpView
import io.objectbox.BoxStore
import org.junit.After
import org.junit.Before
import java.io.File

abstract class BasePresenterTest<VIEW : MvpView, PRESENTER : MvpPresenter<VIEW, VIEWMODEL>, VIEWMODEL : ViewModel> {

    lateinit var presenter: PRESENTER
    abstract var view: VIEW
    open lateinit var boxStore: BoxStore

    @Before
    fun setup() {
        val tempFile = File.createTempFile("object-store-test", "").apply { delete() }
        boxStore = com.playlist.video.player.data.model.MyObjectBox.builder().directory(tempFile).build()

        presenter = createPresenter()
    }

    @After
    fun tearDown() {
        boxStore.close()
        boxStore.deleteAllFiles()
    }

    protected fun setViewModel(viewModel: VIEWMODEL) {
        presenter.setViewModel(viewModel)

        @Suppress("UNCHECKED_CAST")
        presenter.attachView(view)
    }

    abstract fun createPresenter(): PRESENTER
}
