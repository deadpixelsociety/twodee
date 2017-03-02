package com.thedeadpixelsociety.twodee.components.dolly.actions

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.MathUtils
import com.thedeadpixelsociety.twodee.components.Transform

/**
 * Smoothly rotates the dolly to a target rotation over a period of time.
 */
class RotateDollyTo : DollyAction() {
    /**
     * The amount of time it takes to rotate to the target in seconds.
     */
    var time = 1f

    /**
     * The camera target rotation.
     */
    val target = 0f

    private var elapsedTime = 0f

    override fun update(deltaTime: Float, camera: OrthographicCamera, transform: Transform): Boolean {
        if (time <= 0 || elapsedTime >= time) return true

        val a = elapsedTime / time
        val f = MathUtils.sin(a * MathUtils.PI * .5f)
        transform.rotate(Interpolation.linear.apply(transform.angle, target, f))
        elapsedTime = Math.min(time, elapsedTime + deltaTime)

        return elapsedTime >= time
    }
}