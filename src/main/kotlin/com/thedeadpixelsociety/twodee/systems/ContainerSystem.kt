package com.thedeadpixelsociety.twodee.systems

import com.badlogic.ashley.core.*

/**
 * A container system collects entities of a specific family but does not actually do any processing on them.
 * @param family The entity family.
 * @param priority The system priority.
 */
open class ContainerSystem(val family: Family, priority: Int) : EntitySystem(priority), EntityListener {
    private val entities = hashSetOf<Entity>()

    /**
     * A container system collects entities of a specific family but does not actually do any processing on them.
     * @param family The entity family.
     */
    constructor(family: Family) : this(family, 0)

    /**
     * Gets the entities currently in the system.
     * @return An immutable list of entities.
     */
    fun entities() = entities.toList()

    override fun addedToEngine(engine: Engine?) {
        super.addedToEngine(engine)
        engine?.addEntityListener(family, this)
    }

    override fun removedFromEngine(engine: Engine?) {
        super.removedFromEngine(engine)
        engine?.removeEntityListener(this)
    }

    override fun entityAdded(entity: Entity?) {
        if (entity == null) return
        if (entities.add(entity)) onEntityAdded(entity)
    }

    override fun entityRemoved(entity: Entity?) {
        if (entity == null) return
        if (entities.remove(entity)) onEntityRemoved(entity)
    }

    /**
     * Called when a new entity has been added to the system.
     */
    protected open fun onEntityAdded(entity: Entity) {
    }

    /**
     * Called when an entity has been removed from the system.
     */
    protected open fun onEntityRemoved(entity: Entity) {
    }
}