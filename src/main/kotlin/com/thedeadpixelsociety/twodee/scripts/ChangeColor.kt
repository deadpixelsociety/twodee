package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.thedeadpixelsociety.twodee.components.Tint
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Script to change the color of an entity over time. Requires the Tint component.
 */
class ChangeColor() : Script() {
    constructor(color: Color, time: Float = 1f) : this() {
        target.set(color)
        this.time = time
    }

    /**
     * The target color to transition into.
     */
    val target = Color(Color.WHITE)

    /**
     * The amount of time in seconds it takes to fully transition.
     */
    var time = 1f

    private val tintMapper by mapper<Tint>()
    private var elapsedTime = 0f

    override fun update(deltaTime: Float, engine: Engine, entity: Entity): Boolean {
        if (time <= 0f) return true
        val tint = tintMapper[entity] ?: return true
        tint.color.lerp(target, Math.min(time, elapsedTime) / time)
        println("color ${tint.color}")
        elapsedTime += deltaTime
        val finished = elapsedTime >= time
        if (finished) tint.color.set(target)
        return finished
    }

    override fun reset() {
        target.set(Color.WHITE)
        time = 1f
        elapsedTime = 0f
    }
}