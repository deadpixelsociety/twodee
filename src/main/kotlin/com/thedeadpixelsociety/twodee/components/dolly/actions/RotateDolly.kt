package com.thedeadpixelsociety.twodee.components.dolly.actions

import com.badlogic.gdx.graphics.OrthographicCamera
import com.thedeadpixelsociety.twodee.components.Transform

/**
 * Rotates the dolly by a fixed amount per second.
 */
class RotateDolly : DollyAction() {
    /**
     * The amount to rotate the dolly per second.
     */
    var amount = 0f

    /**
     * The amount of time to rotate in seconds. 0 = forever.
     */
    var duration = 0f

    private var elapsedTime = 0f

    override fun update(deltaTime: Float, camera: OrthographicCamera, transform: Transform): Boolean {
        transform.rotation = transform.rotation + amount * deltaTime
        if (duration > 0f) {
            elapsedTime = Math.min(duration, elapsedTime + deltaTime)
        }

        return duration != 0f && elapsedTime >= duration
    }

    override fun reset() {
        amount = 0f
        duration = 0f
        elapsedTime = 0f
    }
}