package com.thedeadpixelsociety.twodee.components

/**
 * An entity component that denotes an entity as belonging to one or more group(s) of entities.
 */
class GroupMask : PoolableComponent() {
    companion object {
        const val INVALID = 0
    }

    /**
     * The group mask for the entity.
     */
    var mask = INVALID

    override fun reset() {
        mask = INVALID
    }
}