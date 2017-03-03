package com.thedeadpixelsociety.twodee.components

import com.badlogic.ashley.core.Component

/**
 * A helper function to easily create component mappers.
 * @param T The component type.
 */
inline fun <reified T : Component> mapper() = ComponentMapperDelegate(T::class.java)
