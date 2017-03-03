package com.thedeadpixelsociety.twodee.components.dolly.actions

import com.thedeadpixelsociety.twodee.components.Transform

/**
 * Rotates the dolly by a fixed amount per second.
 */
class RotateDolly : DollyAction() {
    companion object {
        const val FOREVER = 0f
    }

    /**
     * The amount to rotate the dolly per second.
     */
    var amount = 0f

    /**
     * The amount of time to rotate in seconds. 0 = forever.
     */
    var duration = FOREVER

    private var elapsedTime = 0f

    override fun update(deltaTime: Float, transform: Transform): Boolean {
        if (duration < 0f) return true
        transform.angle += amount * deltaTime
        if (duration == FOREVER) return false
        elapsedTime += deltaTime
        return elapsedTime >= duration
    }

    override fun reset() {
        amount = 0f
        duration = FOREVER
        elapsedTime = 0f
    }
}