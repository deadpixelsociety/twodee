package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.utils.Pool

/**
 * A script that performs some actions on an entity.
 */
abstract class Script : Pool.Poolable {
    var started = false

    /**
     * Called when the script is started.
     * @param engine The entity engine.
     * @param entity The entity being scripted.
     */
    open fun start(engine: Engine, entity: Entity) {
    }

    /**
     * Called when the script is finished.
     * @param engine The entity engine.
     * @param entity The entity being scripted.
     */
    open fun finish(engine: Engine, entity: Entity) {
    }

    /**
     * Updates the script.
     * @param deltaTime The current delta time, in seconds.
     * @param engine The entity engine.
     * @param entity The entity being scripted.
     * @return true if the script has finished.
     */
    abstract fun update(deltaTime: Float, engine: Engine, entity: Entity): Boolean

    override fun reset() {
        started = false
    }
}