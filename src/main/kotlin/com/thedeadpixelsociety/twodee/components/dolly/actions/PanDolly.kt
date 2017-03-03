package com.thedeadpixelsociety.twodee.components.dolly.actions

import com.badlogic.gdx.math.Vector2
import com.thedeadpixelsociety.twodee.components.Transform

/**
 * Translates the dolly by a fixed amount per second.
 */
class PanDolly : DollyAction() {
    companion object {
        const val FOREVER = 0f
    }

    /**
     * The amount to translate the dolly per second.
     */
    var amount = Vector2()

    /**
     * The amount of time to translate in seconds. 0 = forever.
     */
    var duration = FOREVER

    private var elapsedTime = 0f

    override fun update(deltaTime: Float, transform: Transform): Boolean {
        if (duration < 0f) return true
        transform.position.add(amount.x * deltaTime, amount.y * deltaTime)
        if (duration == FOREVER) return false
        elapsedTime += deltaTime
        return elapsedTime >= duration
    }

    override fun reset() {
        amount.set(0f, 0f)
        duration = FOREVER
        elapsedTime = 0f
    }
}