package com.thedeadpixelsociety.twodee.components.dolly.actions

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.thedeadpixelsociety.twodee.components.Transform

/**
 * Smoothly translates the dolly to a target position over a period of time.
 */
class PanDollyTo : DollyAction() {
    /**
     * The amount of time in seconds it takes to traverse the distance to the target.
     */
    var time = 1f

    /**
     * The camera target position.
     */
    val target = Vector2(0f, 0f)

    private var elapsedTime = 0f

    override fun update(deltaTime: Float, transform: Transform): Boolean {
        if (time <= 0f) return true
        transform.position.lerp(target, MathUtils.sin((Math.min(time, elapsedTime) / time) * MathUtils.PI * .5f))
        elapsedTime += deltaTime
        val finished = elapsedTime >= time
        if (finished) transform.position.set(target) // ensure we're exactly at the target when we are done.
        return finished
    }

    override fun reset() {
        time = 1f
        elapsedTime = 0f
        target.set(0f, 0f)
    }
}