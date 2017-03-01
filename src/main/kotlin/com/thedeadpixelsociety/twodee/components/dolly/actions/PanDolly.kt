package com.thedeadpixelsociety.twodee.components.dolly.actions

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.thedeadpixelsociety.twodee.components.Transform

/**
 * Translates the dolly by a fixed amount per second.
 */
class PanDolly : DollyAction() {
    /**
     * The amount to translate the dolly per second.
     */
    var amount = Vector2()

    /**
     * The amount of time to translate in seconds. 0 = forever.
     */
    var duration = 0f

    private var elapsedTime = 0f

    override fun update(deltaTime: Float, camera: OrthographicCamera, transform: Transform): Boolean {
        transform.position.add(amount.x * deltaTime, amount.y * deltaTime)
        if (duration > 0f) {
            elapsedTime = Math.min(duration, elapsedTime + deltaTime)
        }

        return duration != 0f && elapsedTime >= duration
    }

    override fun reset() {
        amount.set(0f, 0f)
        duration = 0f
        elapsedTime = 0f
    }
}