package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Rotates the entity by a fixed amount per second. Requires the Transform component.
 */
class Rotate() : Script() {
    companion object {
        const val INFINITE = -1f
        const val INSTANT = 0f
    }

    constructor(amount: Float, duration: Float = INFINITE) : this() {
        this.amount = amount
        this.duration = duration
    }

    /**
     * The amount to rotate the dolly per second.
     */
    var amount = 0f

    /**
     * The amount of time to rotate in seconds. 0 = forever.
     */
    var duration = INFINITE

    private val transformMapper by mapper<Transform>()
    private var elapsedTime = 0f

    override fun update(deltaTime: Float, engine: Engine, entity: Entity): Boolean {
        if (duration < 0f) return true

        val transform = transformMapper[entity] ?: return true
        if (duration == INSTANT) {
            transform.angle += amount
            return true
        }

        transform.angle += amount * deltaTime
        if (duration == INFINITE) return false
        elapsedTime += deltaTime
        return elapsedTime >= duration
    }

    override fun reset() {
        amount = 0f
        duration = INFINITE
        elapsedTime = 0f
    }
}