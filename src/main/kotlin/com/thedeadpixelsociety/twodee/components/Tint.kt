package com.thedeadpixelsociety.twodee.components

import com.badlogic.gdx.graphics.Color

/**
 * An entity component containing color information.
 */
class Tint : PoolableComponent() {
    /**
     * The tint color. Defaults to white.
     */
    val color = Color(Color.WHITE)

    override fun reset() {
        color.set(Color.WHITE)
    }
}