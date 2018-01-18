package com.playlist.video.player.flow.licenses

import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.playlist.video.player.R
import com.github.feed.sample.ui.main.MainActivity
import com.github.feed.sample.util.clickViewWithText
import com.github.feed.sample.util.nextOpenActivityIs
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class LicensesScreenTest : com.playlist.video.player.BaseInstrumentationTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun showLicenses() {
        // Arrange

        // Act
        openActionBarOverflowOrOptionsMenu(getTargetContext())
        clickViewWithText(com.playlist.video.player.R.string.menu_licenses)

        // Assert
        nextOpenActivityIs<OssLicensesMenuActivity>()
    }
}