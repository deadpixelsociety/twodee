package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.thedeadpixelsociety.twodee.Tween
import com.thedeadpixelsociety.twodee.components.Tint
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * Script to change the color of an entity over duration. Requires the Tint component.
 */
class ColorTween() : TweenScript<Color>() {
    companion object {
        val DEFAULT_TWEEN: Tween<Color> = { start, end, t -> c0.set(start).lerp(end, t) }
        private val c0 = Color(Color.WHITE)
    }

    init {
        tween = DEFAULT_TWEEN
    }

    constructor(start: Color, target: Color, time: Float = Duration.INSTANT) : this() {
        this.start.set(start)
        this.end.set(target)
        this.duration = time
    }

    override var start = Color(Color.WHITE)

    override var end = Color(Color.WHITE)

    private val tintMapper by mapper<Tint>()

    override fun updateValue(engine: Engine, entity: Entity, value: Color) {
        val tint = tintMapper[entity] ?: return
        tint.color.set(value)
    }

    override fun reset() {
        super.reset()
        start.set(Color.WHITE)
        end.set(Color.WHITE)
        c0.set(Color.WHITE)
        tween = DEFAULT_TWEEN
    }
}