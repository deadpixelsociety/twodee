package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.thedeadpixelsociety.twodee.Tween
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Smoothly moves an entity to the end location. Requires the Transform component.
 */
class MoveTween() : TweenScript<Vector2>() {
    companion object {
        // Simple linear tween
        val DEFAULT_TWEEN: Tween<Vector2> = { start, end, t ->
            v0.set(start).nor().setLength(Interpolation.linear.apply(start.len(), end.len(), t))
        }

        private val v0 = Vector2()
    }

    init {
        tween = DEFAULT_TWEEN
    }

    constructor(x: Float, y: Float, duration: Float = Duration.INSTANT, tween: Tween<Vector2> = DEFAULT_TWEEN) : this() {
        this.end.set(x, y)
        this.duration = duration
        this.tween = tween
    }

    override var start = Vector2()

    override var end = Vector2()

    private val transformMapper by mapper<Transform>()

    override fun start(engine: Engine, entity: Entity) {
        val transform = transformMapper[entity] ?: return
        start.set(transform.position)
    }

    override fun updateValue(engine: Engine, entity: Entity, value: Vector2) {
        val transform = transformMapper[entity] ?: return
        transform.position.set(value)
    }

    override fun reset() {
        super.reset()
        start.set(0f, 0f)
        end.set(0f, 0f)
        tween = DEFAULT_TWEEN
    }
}