package com.thedeadpixelsociety.twodee

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
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

/**
 * Converts a Vector2 into a Vector3 with zero as it's z value by default.
 * @param z The z value to use.
 */
fun <T : Vector2> T.toVector3(z: Float = 0f) = Vector3(x, y, z)

/**
 * Converts a Vector3 into a Vector.
 */
fun <T : Vector3> T.toVector2() = Vector2(x, y)

/**
 * Reset's an orthographi camera's up and direction vectors.
 * @param yDown true if the y axis points down.
 */
fun <T : OrthographicCamera> T.reset(yDown: Boolean) {
    if (yDown) {
        up.set(0f, -1f, 0f)
        direction.set(0f, 0f, 1f)
    } else {
        up.set(0f, 1f, 0f)
        direction.set(0f, 0f, -1f)
    }
}