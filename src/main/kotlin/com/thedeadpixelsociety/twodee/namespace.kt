package com.thedeadpixelsociety.twodee

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

