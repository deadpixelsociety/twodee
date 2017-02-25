package com.thedeadpixelsociety.twodee

import com.badlogic.gdx.utils.Disposable

typealias Action<T> = (T) -> Unit
typealias Func<T> = () -> T
typealias Predicate<T> = (T) -> Boolean

/**
 * A helper function to create a libGDX array. This helps work around the name conflict between libGDX's Array<T> and
 * Kotlin's Array<T> implementations.
 *
 * @param T The array type.
 * @return A libGDX array of the specified type.
 * @see com.badlogic.gdx.utils.Array
 */
fun <T> gdxArray() = com.badlogic.gdx.utils.Array<T>()

/**
 * A helper function to return the current update delta time, in seconds.
 */
fun deltaTime() = TimeController.deltaTime

/**
 * A helper function to return the total accumulated time, in seconds.
 */
fun totalTime() = TimeController.totalTime

/**
 * A helper function to create a tick event.
 * @param interval The interval between ticks. Defaults to zero.
 * @param repeat The number of times to repeat the event after the first invocation. Defaults to zero.
 * @param func The function to perform at each tick.
 * @see TimeController
 * @see TimeController.TickEvent
 */
fun tickEvent(interval: Float = 0f, repeat: Int = 0, func: Func<Unit>) {
    TimeController.register(TimeController.TickEvent(interval, repeat, func))
}

/**
 * A helper function to safely dispose a Disposable object after performing an action.
 * @param T The disposable type.
 * @param action The action to perform.
 */
fun <T : Disposable> T.using(action: Action<T>) {
    try {
        action(this)
    } finally {
        dispose()
    }
}