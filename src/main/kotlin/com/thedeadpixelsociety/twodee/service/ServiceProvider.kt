package com.thedeadpixelsociety.twodee.service

import com.badlogic.gdx.utils.Disposable

/**
 * Defines an invokable service provider.
 */
interface ServiceProvider<out T> : Disposable {
    operator fun invoke(): T
}