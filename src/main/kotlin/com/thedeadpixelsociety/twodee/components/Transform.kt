package com.thedeadpixelsociety.twodee.components

import com.badlogic.gdx.math.Matrix3
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

/**
 * An entity component that stores basic transformation info.
 */
class Transform : PoolableComponent() {
    /**
     * The entity position in world space.
     */
    val position = Vector2()
    /**
     * The entity origin relative to the position.
     */
    val origin = Vector2()
    /**
     * The entity scale.
     */
    val scale = Vector2(1f, 1f)
    /**
     * The entity rotation in degrees.
     */
    var rotation = 0f
        get() {
            return field
        }

        set(value) {
            lastRotation = field
            field = value
        }

    /**
     * The last change in the rotation in degrees.
     */
    val rotationDelta: Float
        get() {
            return rotation - lastRotation
        }

    private var lastRotation = 0f

    /**
     * The center of the entity as a sum of position and origin.
     */
    fun center() = Vector2(position.x + origin.x, position.y + origin.y)

    /**
     * Creates a Matrix4 transformation matrix.
     * @return The transformation matrix.
     * @see Matrix4
     */
    fun mat4() = Matrix4()
            .scale(scale.x, scale.y, 0f)
            .translate(-origin.x, -origin.y, 0f)
            .rotate(Vector3(0f, 0f, 1f), rotation)
            .translate(position.x, position.y, 0f)

    /**
     * Creates a Matrix3 transformation matrix.
     * @return The transformation matrix.
     * @see Matrix3
     */
    fun mat3() = Matrix3()
            .scale(scale.x, scale.y)
            .translate(-origin.x, -origin.y)
            .rotate(rotation)
            .translate(position.x, position.y)

    override fun reset() {
        position.set(0f, 0f)
        origin.set(0f, 0f)
        scale.set(1f, 1f)
        rotation = 0f
    }
}