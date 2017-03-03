package com.thedeadpixelsociety.twodee.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.utils.Pools
import com.thedeadpixelsociety.twodee.components.Dolly
import com.thedeadpixelsociety.twodee.components.Transform

/**
 * A dolly system used to run transformation actions against a camera.
 */
class DollySystem : IteratingSystem(Family.all(Transform::class.java, Dolly::class.java).get()) {
    private val transformMapper = ComponentMapper.getFor(Transform::class.java)
    private val dollyMapper = ComponentMapper.getFor(Dolly::class.java)

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        if (entity == null) return
        val transform = transformMapper[entity] ?: return
        val dolly = dollyMapper[entity] ?: return

        runActions(deltaTime, dolly, transform)

        dolly.camera.translate(-transform.position.x, -transform.position.y)
        dolly.camera.rotate(transform.deltaAngle)
        dolly.camera.translate(transform.position.x, transform.position.y)
        dolly.camera.update()
    }

    private fun runActions(deltaTime: Float, dolly: Dolly, transform: Transform) {
        dolly.actions.toList().forEach {
            if (it.update(deltaTime, dolly.camera, transform)) {
                dolly.actions.removeValue(it, true)
                Pools.free(it)
            }
        }
    }
}