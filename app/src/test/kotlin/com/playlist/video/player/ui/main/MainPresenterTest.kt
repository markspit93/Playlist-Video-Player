package com.playlist.video.player.ui.main

import com.github.feed.sample.ui.BasePresenterTest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.mockito.Mockito.verify

class MainPresenterTest : BasePresenterTest<MainContract.View, MainPresenter, MainViewModel>() {

    override var view: MainContract.View = mock()

    override fun createPresenter() = MainPresenter()

    @Test
    fun loadFilterSelections_Successful() {
        // Arrange
        setViewModel(MainViewModel())

        // Act
        presenter.loadFilterSelections()

        // Assert
        verify(view).selectFilter(any(), any())
    }
}
