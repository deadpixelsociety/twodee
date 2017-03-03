package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import com.thedeadpixelsociety.twodee.Tween
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Smoothly moves an entity to the target location. Requires the Transform component.
 */
class MoveTo() : Script() {
    companion object {
        // Simple linear tween
        val DEFAULT_TWEEN: Tween<Vector2> = { start, end, t -> v0.set(end).sub(start).scl(t).add(start) }

        private val v0 = Vector2()
    }

    constructor(x: Float, y: Float, time: Float = 1f, tween: Tween<Vector2> = DEFAULT_TWEEN) : this() {
        target.set(x, y)
        this.time = time
        this.tween = tween
    }

    /**
     * The amount of time in seconds it takes to traverse the distance to the target.
     */
    var time = 1f

    /**
     * The target position.
     */
    val target = Vector2(0f, 0f)

    /**
     * An optional tweening function used to control how the transition happens.
     */
    var tween = DEFAULT_TWEEN

    private val transformMapper by mapper<Transform>()
    private var elapsedTime = 0f

    override fun update(deltaTime: Float, engine: Engine, entity: Entity): Boolean {
        if (time <= 0f) return true
        val transform = transformMapper[entity] ?: return true
        transform.position.set(tween.invoke(transform.position, target, Math.min(elapsedTime, time) / time))
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