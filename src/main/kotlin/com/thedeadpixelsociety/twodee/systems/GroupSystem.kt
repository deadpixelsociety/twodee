package com.thedeadpixelsociety.twodee.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.IntMap
import com.thedeadpixelsociety.twodee.components.GroupMask
import com.thedeadpixelsociety.twodee.gdxArray

/**
 * A container system used to store and track grouped entities.
 */
class GroupSystem : ContainerSystem(Family.all(GroupMask::class.java).get()) {
    private val entityMap = IntMap<Array<Entity>>()
    private val groupMapper = ComponentMapper.getFor(GroupMask::class.java)

    /**
     * Gets all entities with the specified group mask.
     * @param mask The group mask to retrieve.
     * @return A list of entities in the specified group(s).
     */
    operator fun get(mask: Int): List<Entity> {
        val list = arrayListOf<Entity>()
        entityMap.forEach {
            val id = it.key
            if (mask and id == id) list.addAll(getInner(id))
        }

        return list
    }

    private fun getInner(id: Int): Array<Entity> {
        var array = entityMap.get(id)
        if (array == null) {
            array = gdxArray()
            entityMap.put(id, array)
        }

        return array
    }

    override fun onEntityAdded(entity: Entity) {
        val group = groupMapper[entity] ?: return
        if (group.mask == GroupMask.INVALID) return
        for (i in 0..31) {
            val id = 1 shl i
            if (group.mask and id == id) getInner(id).add(entity)
        }
    }

    override fun onEntityRemoved(entity: Entity) {
        val group = groupMapper[entity] ?: return
        if (group.mask == GroupMask.INVALID) return
        for (i in 0..31) {
            val id = 1 shl i
            if (group.mask and id == id) getInner(id).removeValue(entity, true)
        }
    }
}