Github Feed Sample
======

A sample Android app which retrieves realtime Github events and categorizes them into different lists. Clicking an item in these lists will take you to a screen with more details on the event.

The app showcases advanced usage of Kotlin, Dagger2, Retrofit2, RxJava2, and some Architecture Components. The diagram below demonstrates how the MVP architecture was used to achieve this:

![App Architecture Diagram](mvp_diagram.png)

As seen above, the architecture used is not pure MVP since it includes a ViewModel between the Presenter and Model (the repository).
This is the ViewModel provided by Android's Architecture Components. It's used to hold data from a remote repository in memory which is then shown in a list fragment. This can persist device configuration changes such as rotation. The main activity takes a simpler approach. Its presenter skips using ViewModel and fetches data directly from a local data source which handles a database. I chose to use the ObjectBox library for my database since it is extremely easy to implement, efficient and fast. 

I am aware that the fragment was completely unecessary for this simple app. It is there to demonstrate how fragments would work in more complex apps. Although many developers are against fragments, I believe that when used correctly they can be advantageous.

<p align="center">
    <img width="300" height="500" src="https://dl2.pushbulletusercontent.com/A6nbpb1d1F0u7V8318Y5e9FbwDVDAeNv/20171113_153357.gif">
</p>

Usage
======

When running the app, ensure that the latest Play Services are available on the device/emulator due to downloadable fonts.
More information can be found here: https://developer.android.com/guide/topics/ui/look-and-feel/downloadable-fonts.html

Tests
======

The app contains both UI tests (Espresso) and unit tests (AndroidJUnit).
To run them use the following Gradle tasks:

 * runAllTests
 * runUnitTests
 * runInstrumentationTests
 
 **Important:** The unit tests involving ObjectBox (MainPresenterTest and LocalFilterDataSourceTest) will fail on MacOS due to a           temporary library limitation.
 
 Libraries
 ======
 
 * Anko - https://github.com/Kotlin/anko
 * Espresso - https://google.github.io/android-testing-support-library/
 * Dagger - https://google.github.io/dagger/
 * Glide - https://github.com/bumptech/glide
 * ObjectBox - https://github.com/objectbox/objectbox-java
 * Mockito - https://github.com/mockito/mockito
 * MockWebServer - https://github.com/square/okhttp/tree/master/mockwebserver
 * Retrofit - https://github.com/square/retrofit/
 * RxKotlin - https://github.com/ReactiveX/RxKotlin
 * SmoothProgressBar - https://github.com/castorflex/SmoothProgressBar
 * Stetho - https://github.com/facebook/stetho
 
 
