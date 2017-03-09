package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity

/**
 * A script that uses a tween function to move towards a specified end value over a specified duration.
 * @param T The type being tweened.
 */
abstract class TweenScript<T> : Script() {
    /**
     * The starting value.
     */
    abstract var start: T

    /**
     * The ending value.
     */
    abstract var end: T

    /**
     * The duration to complete the tween.
     */
    var duration = Duration.INSTANT

    /**
     * The tween function.
     */
    var tween = { _: T, end: T, _: Float -> end }

    private var elapsed = 0f

    override fun update(deltaTime: Float, engine: Engine, entity: Entity): Boolean {
        if (duration < Duration.INFINITE) return true
        if (duration == Duration.INSTANT) {
            updateValue(engine, entity, end)
            return true
        }

        val alpha = Math.min(duration, elapsed) / duration
        updateValue(engine, entity, tween(start, end, alpha))
        elapsed = Math.min(duration, elapsed + deltaTime)
        val finished = elapsed >= duration
        if (finished) updateValue(engine, entity, end)

        return finished
    }

    /**
     * Updates the entity based on the specified value.
     * @param engine The entity engine.
     * @param entity The entity being scripted.
     * @param value The tween value.
     */
    abstract fun updateValue(engine: Engine, entity: Entity, value: T)

    override fun reset() {
        elapsed = 0f
        duration = Duration.INSTANT
    }
}