package com.thedeadpixelsociety.twodee.components

/**
 * An entity component that stores dimensional information.
 */
class Size : PoolableComponent() {
    /**
     * The width of the entity.
     */
    var width = 0f

    /**
     * The height of the entity.
     */
    var height = 0f

    /**
     * The radius of the entity.
     */
    var radius = 0f

    override fun reset() {
        width = 0f
        height = 0f
        radius = 0f
    }
}