package com.thedeadpixelsociety.twodee.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A read-only delegate for a component mapper. This allows the use of property delegates to easily create mappers.
 * @param T The pool type.
 * @param componentClass The component class.
 */
class ComponentMapperDelegate<T : Component>(componentClass: Class<T>) : ReadOnlyProperty<Any, ComponentMapper<T>> {
    private val mapper = ComponentMapper.getFor(componentClass)

    override fun getValue(thisRef: Any, property: KProperty<*>): ComponentMapper<T> = mapper
}