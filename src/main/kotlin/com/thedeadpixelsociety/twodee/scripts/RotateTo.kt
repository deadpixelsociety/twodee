package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.MathUtils
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Smoothly rotates the entity to a target rotation over a period of time. Requires the Transform component.
 */
class RotateTo() : Script() {
    constructor(target: Float, time: Float) : this() {
        this.target = target
        this.time = time
    }

    /**
     * The amount of time it takes to rotate to the target in seconds.
     */
    var time = 1f

    /**
     * The camera target rotation.
     */
    var target = 0f

    private val transformMapper by mapper<Transform>()
    private var elapsedTime = 0f

    override fun update(deltaTime: Float, engine: Engine, entity: Entity): Boolean {
        if (time <= 0) return true
        val transform = transformMapper[entity] ?: return true
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

    override fun reset() {
        time = 1f
        target = 0f
        elapsedTime = 0f
    }
}