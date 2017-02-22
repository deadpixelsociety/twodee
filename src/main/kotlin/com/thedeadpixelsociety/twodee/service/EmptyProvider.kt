package com.thedeadpixelsociety.twodee.service

abstract class EmptyProvider<out T> : ServiceProvider<T> {
    override fun dispose() {
    }
}