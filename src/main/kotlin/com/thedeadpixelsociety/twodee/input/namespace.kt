package com.thedeadpixelsociety.twodee.input

import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

private val v2 = Vector2()
private val v3 = Vector3()

/**
 * Helper function to get the current mouse position in a 2D vector.
 */
fun <T : Input> T.mousePosition2() = v2.set(x.toFloat(), y.toFloat())

/**
 * Helper function to get the current mouse position in a 3D vector.
 * @param z The z value to use. Defaults to zero.
 */
fun <T : Input> T.mousePosition3(z: Float = 0f) = v3.set(x.toFloat(), y.toFloat(), z)