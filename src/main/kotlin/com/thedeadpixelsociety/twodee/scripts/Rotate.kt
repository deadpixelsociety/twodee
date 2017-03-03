package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Entity
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Rotates the entity by a fixed amount per second.
 */
class Rotate() : Script() {
    companion object {
        const val FOREVER = 0f
    }

    constructor(amount: Float, duration: Float = FOREVER) : this() {
        this.amount = amount;
        this.duration = duration
    }

    /**
     * The amount to rotate the dolly per second.
     */
    var amount = 0f

    /**
     * The amount of time to rotate in seconds. 0 = forever.
     */
    var duration = FOREVER

    private val transformMapper by mapper<Transform>()
    private var elapsedTime = 0f

    override fun update(deltaTime: Float, entity: Entity): Boolean {
        if (duration < 0f) return true
        val transform = transformMapper[entity] ?: return true
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