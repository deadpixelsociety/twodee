package com.thedeadpixelsociety.twodee

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Disposable

typealias Action<T> = (T) -> Unit
typealias Func<T> = () -> T
typealias Predicate<T> = (T) -> Boolean
typealias Transform<T, R> = (T) -> R
typealias Tween<T> = (T, T, Float) -> T

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
 * A helper function to return the current update delta duration, in seconds.
 */
fun deltaTime() = TimeController.deltaTime

/**
 * A helper function to return the total accumulated duration, in seconds.
 */
fun totalTime() = TimeController.totalTime

/**
 * A helper function to create a tick event.
 * @param delay The initial delay before the event happens.
 * @param interval The interval between ticks. Defaults to zero.
 * @param repeat The number of times to repeat the event after the first invocation. Defaults to zero.
 * @param func The function to perform at each tick.
 * @see TimeController
 * @see TimeController.TickEvent
 * @return The created tick event.
 */
fun tickEvent(delay: Float = 0f, interval: Float = 0f, repeat: Int = 0, func: Func<Unit>): TimeController.TickEvent {
    val event = TimeController.TickEvent(delay, interval, repeat, func)
    TimeController.register(event)
    return event
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
fun Vector2.toVector3(z: Float = 0f) = Vector3(x, y, z)

/**
 * Converts a Vector3 into a Vector.
 */
fun Vector3.toVector2() = Vector2(x, y)

/**
 * Returns a new vector containing only the largest component.
 */
fun Vector2.withLargestComponent(): Vector2 {
    val xl = Math.abs(x) >= Math.abs(y)
    return Vector2(if (xl) x else 0f, if (xl) 0f else y)
}

/**
 * Reset's an orthographic camera's up and direction vectors.
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