package com.thedeadpixelsociety.twodee.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.thedeadpixelsociety.twodee.components.Dolly
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.mapper
import com.thedeadpixelsociety.twodee.reset

/**
 * A dolly system used to move a camera based on it's transform component.
 */
class DollySystem : IteratingSystem(Family.all(Transform::class.java, Dolly::class.java).get()) {
    private val transformMapper by mapper<Transform>()
    private val dollyMapper by mapper<Dolly>()

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        if (entity == null) return
        val transform = transformMapper[entity] ?: return
        val dolly = dollyMapper[entity] ?: return

        dolly.camera.reset(false)
        dolly.camera.position.set(-(dolly.camera.viewportWidth * .5f), -(dolly.camera.viewportHeight * .5f), 0f)
        dolly.camera.rotate(transform.angle)
        dolly.camera.position.set(transform.position, 0f)
        dolly.camera.update()
    }
}