# Future Mirror
#### Smart Mirror App 

## How to build
` ./gradlew clean assembleDebug`

## Done
1. [x] Install dependency injection(dagger)
2. [x] Splash screen with icon fade out effect
3. [x] Name days in Poland
4. [x] Monthly agenda of name days
5. [x] Find name days
6. [ ] Find news/weather api
7. [ ] Check how PagingData is working
8. [ ] Add junit tests for use cases, viewmodel tests
9. [ ] Add draggable element
10. [ ] Dashboard personalization
11. [x] Use datastore for user settings
12. [ ] Import name days when user first time open the app


## Technology stack
- [x] [Kotlin](https://developer.android.com/kotlin) - Kotlin is a programming language that can run on JVM. Google has announced Kotlin as one of its officially supported programming languages in Android Studio; and the Android community is migrating at a pace from Java to Kotlin
- [x] [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project
- [x] [Jetpack Compose](https://developer.android.com/develop/ui/compose) - Jetpack Compose is Android’s recommended modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.
- [x] [Kotlin Flow](https://developer.android.com/kotlin/flow) - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.
- [x] [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API which provides utility on top of Android's normal Log class.
- [ ] [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [x] [Material Design 3](https://m3.material.io/develop/android/mdc-android) - Modular and customizable Material Design UI components for Android
- [ ] [Retrofit](https://square.github.io/retrofit/) - Retrofit is a REST Client for Java and Android by Square inc under Apache 2.0 license. Its a simple network library that used for network transactions. By using this library we can seamlessly capture JSON response from web service/web API.
- [ ] [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - The Paging library helps you load and display pages of data from a larger dataset from local storage or over network. This approach allows your app to use both network bandwidth and system resources more efficiently.
- [x] [Navigation Components](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that let users navigate across, into, and back out from the different pieces of content within your app.
- [x] [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way
- [x] [JUnit](https://junit.org/junit4/) - JUnit is a simple framework to write repeatable tests.
- [x] [Mockk](https://mockk.io/) This is a mocking library for Kotlin. 
- [ ] [Robolectric](http://robolectric.org/) - Robolectric is a framework that brings fast and reliable unit tests to Android.
- [ ] [Espresso](https://developer.android.com/training/testing/espresso/) - Android UI tests
- [x] [Room](https://developer.android.com/training/data-storage/room) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.