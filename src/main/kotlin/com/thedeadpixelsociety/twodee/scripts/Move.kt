package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Moves an entity by a set amount per update.
 */
class Move() : Script() {
    companion object {
        const val FOREVER = 0f
    }

    constructor(x: Float, y: Float, duration: Float = FOREVER) : this() {
        amount.set(x, y)
        this.duration = duration
    }

    /**
     * The amount to move per second.
     */
    val amount = Vector2()

    /**
     * The total duration to move for. 0 = forever.
     * @see FOREVER
     */
    var duration = FOREVER

    private val transformMapper by mapper<Transform>()
    private var elapsedTime = 0f

    override fun update(deltaTime: Float, entity: Entity): Boolean {
        if (duration < 0f) return true
        val transform = transformMapper[entity] ?: return true
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