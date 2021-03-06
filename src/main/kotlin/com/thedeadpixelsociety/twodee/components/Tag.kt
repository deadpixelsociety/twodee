package com.thedeadpixelsociety.twodee.components

/**
 * An entity component containing a unique string tag. Used to identify specific entities.
 */
class Tag : PoolableComponent() {
    companion object {
        const val INVALID = ""
    }

    /**
     * The unique ID for the entity.
     */
    var id = INVALID

    override fun reset() {
        id = INVALID
    }
}
