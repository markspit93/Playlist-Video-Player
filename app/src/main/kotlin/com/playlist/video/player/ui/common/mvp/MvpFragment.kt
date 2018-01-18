package com.playlist.video.player.ui.common.mvp

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.playlist.video.player.di.viewmodel.ViewModelFactory
import com.playlist.video.player.ui.common.BaseFragment
import javax.inject.Inject

abstract class MvpFragment<VIEW : MvpView, PRESENTER : MvpPresenter<VIEW, VIEWMODEL>, VIEWMODEL: ViewModel> : BaseFragment(), MvpView {

    @Inject lateinit var presenter: PRESENTER
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.setViewModel(getViewModel(viewModelFactory))
    }

    override fun onStart() {
        super.onStart()
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as VIEW)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    abstract fun getViewModel(viewModelFactory: ViewModelFactory): VIEWMODEL
}
