package com.thedeadpixelsociety.twodee.service

import com.thedeadpixelsociety.twodee.Func

/**
 * A service provider that produces services from a factory. This can be used to generate a new instance of the service
 * each time it is requested.
 */
class FactoryProvider<out T>(private val factory: Func<T>) : EmptyProvider<T>() {
    override fun invoke() = factory()
}