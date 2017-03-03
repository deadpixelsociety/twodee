package com.thedeadpixelsociety.twodee.components.dolly.actions

import com.badlogic.gdx.utils.Pool
import com.thedeadpixelsociety.twodee.components.Transform

/**
 * A dolly action.
 */
abstract class DollyAction : Pool.Poolable {
    /**
     * Updates the action.
     * @param deltaTime The amount of time since the last update in seconds.
     * @param transform The camera's transform.
     * @return true if the action has finished; otherwise, false.
     */
    abstract fun update(deltaTime: Float, transform: Transform): Boolean

    override fun reset() {
    }
}