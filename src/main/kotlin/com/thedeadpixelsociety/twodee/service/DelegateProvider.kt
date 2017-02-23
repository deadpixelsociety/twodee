package com.thedeadpixelsociety.twodee.service

import com.thedeadpixelsociety.twodee.Func

/**
 * A service provider the produces services from a delegate. This can be used to generate a new instance of the service
 * each time it is requested.
 */
class DelegateProvider<out T>(private val delegate: Func<T>) : EmptyProvider<T>() {
    override fun invoke() = delegate()
}