package com.thedeadpixelsociety.twodee.service

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ServiceDelegate<out T>(private val serviceClass: Class<T>) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>) = ServiceProviderRepository.getService(serviceClass)
}