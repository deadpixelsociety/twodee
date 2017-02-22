package com.thedeadpixelsociety.twodee.service

class SingletonProvider<out T>(private val instance: T) : EmptyProvider<T>() {
    override fun invoke() = instance
}