package com.playlist.video.player.ui.common.mvp

import android.arch.lifecycle.ViewModel

open class MvpPresenter<VIEW : MvpView, VIEWMODEL : ViewModel> {

    protected var view: VIEW? = null

    private lateinit var viewModel: VIEWMODEL

    fun getViewModel(): VIEWMODEL {
        if (!this::viewModel.isInitialized) {
            throw UninitializedPropertyAccessException("The presenter's ViewModel was not initialized. Make sure to call presenter.setViewModel() from the View first.")
        }

        return viewModel
    }

    fun setViewModel(viewModel: VIEWMODEL) {
        this.viewModel = viewModel
    }

    fun attachView(view: VIEW) {
        this.view = view
        onViewActive()
    }

    fun detachView() {
        view = null
        onViewInactive()
    }

    open fun onViewActive() {
        // override if necessary
    }

    open fun onViewInactive() {
        // override if necessary
    }
}
