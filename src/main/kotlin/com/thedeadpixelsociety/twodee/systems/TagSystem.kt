package com.thedeadpixelsociety.twodee.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.gdx.utils.IntMap
import com.thedeadpixelsociety.twodee.components.Tag
import java.lang.IllegalArgumentException

/**
 * A container system used to store and track unique entities.
 */
class TagSystem : ContainerSystem(Family.all(Tag::class.java).get()) {
    private val entityMap = IntMap<Entity>()
    private val tagMapper = ComponentMapper.getFor(Tag::class.java)

    /**
     * Gets an entity with the specified tag ID.
     * @return The specified entity if it's in the system; otherwise, null.
     */
    fun getSafe(id: Int): Entity? = entityMap[id, null]

    /**
     * Gets an entity with the specified tag ID.
     * @return The specified entity if it's in the system; otherwise, an exception is thrown.
     * @throws IllegalArgumentException if the specified ID does not exist.
     */
    operator fun get(id: Int) = entityMap[id, null] ?: throw IllegalArgumentException("Invalid entity tag ID.")

    override fun onEntityAdded(entity: Entity) {
        val tag = tagMapper[entity] ?: return
        if (tag.id == Tag.INVALID) return
        entityMap.put(tag.id, entity)
    }

    override fun onEntityRemoved(entity: Entity) {
        val tag = tagMapper[entity] ?: return
        if (tag.id == Tag.INVALID) return
        entityMap.remove(tag.id)
    }
}