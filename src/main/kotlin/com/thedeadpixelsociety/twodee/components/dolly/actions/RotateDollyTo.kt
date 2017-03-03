package com.thedeadpixelsociety.twodee.components.dolly.actions

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
    var target = 0f

    private var elapsedTime = 0f

    override fun update(deltaTime: Float, transform: Transform): Boolean {
        if (time <= 0) return true
        transform.angle = Interpolation.linear.apply(
                transform.angle,
                target,
                MathUtils.sin((Math.min(time, elapsedTime) / time) * MathUtils.PI * .5f)
        )

        elapsedTime += deltaTime
        val finished = elapsedTime >= time
        if (finished) transform.angle = target // ensure we're exactly at the target when we are done.
        return finished
    }
}