package com.thedeadpixelsociety.twodee.service

/**
 * A service provider that captures and returns a single instance of the service.
 */
class SingletonProvider<out T>(private val instance: T) : EmptyProvider<T>() {
    override fun invoke() = instance
}