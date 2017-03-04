package com.thedeadpixelsociety.twodee.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.thedeadpixelsociety.twodee.components.Scripted
import com.thedeadpixelsociety.twodee.components.mapper

/**
 * A script system to run a collection of scripts against an entity.
 */
class ScriptSystem : IteratingSystem(Family.all(Scripted::class.java).get()) {
    private val scriptedMapper by mapper<Scripted>()

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        if (entity == null) return
        val scripted = scriptedMapper[entity] ?: return
        scripted.scripts.toList().forEach {
            if (!it.started) {
                it.start(, )
                it.started = true
            }

            if (it.update(deltaTime, engine, entity)) {
                scripted.scripts.removeValue(it, true)
                it.finish(, )
            }
        }
    }
}