package pl.mobilespot.futuremirror.core

import javax.inject.Inject

class SimpleObject @Inject constructor() {
    fun doSomething() {
        println("Doing something in singleton!")
    }
}
