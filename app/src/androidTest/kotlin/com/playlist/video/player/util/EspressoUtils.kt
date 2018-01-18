package com.playlist.video.player.util

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers

inline fun <reified T : Activity> nextOpenActivityIs() {
    intended(hasComponent(T::class.java.name))
}

fun clickView(@IdRes viewId: Int) {
    onView(withId(viewId)).perform(ViewActions.click())
}

fun clickViewWithText(@StringRes stringRes: Int) {
    onView(withText(stringRes)).perform(ViewActions.click())
}

fun viewIsVisible(@IdRes viewId: Int) {
    onView(withId(viewId)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
}

fun doesNotExist(@IdRes viewId: Int) {
    onView(withId(viewId)).check(ViewAssertions.doesNotExist())
}

fun toastIsShown(activity: Activity, @StringRes stringResource: Int) {
    onView(withText(stringResource)).inRoot(RootMatchers.withDecorView(Matchers.not(Matchers.`is`(activity.window.decorView)))).check(matches(isDisplayed()))
}
