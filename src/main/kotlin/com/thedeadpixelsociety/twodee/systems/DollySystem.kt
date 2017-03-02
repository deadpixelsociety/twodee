package com.thedeadpixelsociety.twodee.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Quaternion
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Pools
import com.thedeadpixelsociety.twodee.components.Dolly
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.toVector3

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

        val transformMatrix = Matrix4(
                transform.position.toVector3(),
                Quaternion(Vector3(0f, 0f, 1f), transform.deltaAngle),
                Vector3(1f, 1f, 1f)
        )

        dolly.camera.rotate(transform.deltaAngle)
        dolly.camera.position.set(transform.position, 0f)
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