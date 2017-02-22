package com.thedeadpixelsociety.twodee.service

import com.thedeadpixelsociety.twodee.Func

class DelegateProvider<out T>(private val delegate: Func<T>) : EmptyProvider<T>() {
    override fun invoke() = delegate()
}