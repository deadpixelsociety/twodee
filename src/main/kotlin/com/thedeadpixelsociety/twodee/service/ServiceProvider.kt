package com.thedeadpixelsociety.twodee.service

import com.badlogic.gdx.utils.Disposable

interface ServiceProvider<out T> : Disposable {
    operator fun invoke(): T
}