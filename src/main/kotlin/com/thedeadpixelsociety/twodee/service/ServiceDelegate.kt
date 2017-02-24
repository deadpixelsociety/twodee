package com.thedeadpixelsociety.twodee.service

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A read-only delegate for lazily injecting a service via a property.
 * @param T The service type.
 * @param serviceClass The service class.
 */
class ServiceDelegate<out T>(private val serviceClass: Class<T>) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>) = ServiceProviderRepository.getService(serviceClass)
}