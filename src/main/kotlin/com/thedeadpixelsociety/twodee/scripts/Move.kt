package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Moves an entity by a set amount per update. Requires the Transform component.
 */
class Move() : Script() {
    companion object {
        const val INFINITE = -1f
        const val INSTANT = 0f
    }

    constructor(x: Float, y: Float, duration: Float = INFINITE) : this() {
        amount.set(x, y)
        this.duration = duration
    }

    /**
     * The amount to move per second.
     */
    val amount = Vector2()

    /**
     * The total duration to move for. -1 = infinite duration. 0 = move the full amount instantly.
     * @see INFINITE
     */
    var duration = INFINITE

    private val transformMapper by mapper<Transform>()
    private var elapsedTime = 0f

    override fun update(deltaTime: Float, engine: Engine, entity: Entity): Boolean {
        if (duration < INFINITE) return true

        val transform = transformMapper[entity] ?: return true
        if (duration == INSTANT) {
            transform.position.add(amount.x, amount.y)
            return true
        }

        transform.position.add(amount.x * deltaTime, amount.y * deltaTime)
        if (duration == INFINITE) return false
        elapsedTime += deltaTime
        return elapsedTime >= duration
    }

    override fun reset() {
        amount.set(0f, 0f)
        duration = INFINITE
        elapsedTime = 0f
    }
}