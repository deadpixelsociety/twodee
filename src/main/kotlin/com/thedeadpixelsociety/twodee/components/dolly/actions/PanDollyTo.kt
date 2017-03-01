package com.thedeadpixelsociety.twodee.components.dolly.actions

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.thedeadpixelsociety.twodee.components.Transform

/**
 * Smoothly translates the dolly to a target position over a period of time.
 */
class PanDollyTo : DollyAction() {
    /**
     * The amount of time it takes to traverse the distance to the target in seconds.
     */
    var time = 1f

    /**
     * The camera target position.
     */
    val target = Vector2(0f, 0f)

    private var elapsedTime = 0f

    override fun update(deltaTime: Float, camera: OrthographicCamera, transform: Transform): Boolean {
        if (time <= 0f || elapsedTime >= time) return true

        val a = elapsedTime / time
        val f = MathUtils.sin(a * MathUtils.PI * .5f)
        transform.position.lerp(target, f)
        elapsedTime = Math.min(time, elapsedTime + deltaTime)

        return elapsedTime >= time
    }

    override fun reset() {
        time = 1f
        elapsedTime = 0f
        target.set(0f, 0f)
    }
}