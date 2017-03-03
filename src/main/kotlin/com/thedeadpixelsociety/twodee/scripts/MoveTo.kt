package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Smoothly moves an entity to the target location.
 */
class MoveTo() : Script() {
    constructor(x: Float, y: Float, time: Float = 1f) : this() {
        target.set(x, y)
        this.time = time
    }

    /**
     * The amount of time in seconds it takes to traverse the distance to the target.
     */
    var time = 1f

    /**
     * The target position.
     */
    val target = Vector2(0f, 0f)

    private val transformMapper by mapper<Transform>()
    private var elapsedTime = 0f

    override fun update(deltaTime: Float, entity: Entity): Boolean {
        if (time <= 0f) return true
        val transform = transformMapper[entity] ?: return true
        transform.position.lerp(target, MathUtils.sin((Math.min(time, elapsedTime) / time) * MathUtils.PI * .5f))
        elapsedTime += deltaTime
        val finished = elapsedTime >= time
        if (finished) transform.position.set(target) // ensure we're exactly at the target when we are done.
        return finished
    }

    override fun reset() {
        time = 1f
        target.set(0f, 0f)
        elapsedTime = 0f
    }
}