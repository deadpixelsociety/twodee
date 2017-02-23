package com.thedeadpixelsociety.twodee.service

/**
 * An empty implementation of a service provider.
 */
abstract class EmptyProvider<out T> : ServiceProvider<T> {
    override fun dispose() {
    }
}