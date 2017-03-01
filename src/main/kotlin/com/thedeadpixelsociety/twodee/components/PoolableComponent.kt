package com.thedeadpixelsociety.twodee.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

/**
 * A component that implements the Poolable interface allowing it to be reset.
 * @see Pool.Poolable
 */
abstract class PoolableComponent : Component, Pool.Poolable {
    /**
     * Resets the component to default values to be reused.
     */
    override fun reset() {
    }
}