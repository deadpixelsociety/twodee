package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper
import com.thedeadpixelsociety.twodee.scripts.Duration.INFINITE

/**
 * Rotates the entity by a fixed amount per second. Requires the Transform component.
 */
class Rotate() : DurationScript() {
    constructor(amount: Float, duration: Float = INFINITE) : this() {
        this.amount = amount
        this.duration = duration
    }

    /**
     * The amount to rotate the dolly per second.
     */
    var amount = 0f

    private val transformMapper by mapper<Transform>()

    override fun step(deltaTime: Float, engine: Engine, entity: Entity) {
        val transform = transformMapper[entity] ?: return
        transform.angle += amount * deltaTime
    }

    override fun reset() {
        super.reset()
        amount = 0f
    }
}