package com.thedeadpixelsociety.twodee.service

import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ObjectMap

object ServiceProviderRepository : Disposable {
    private val providerMap = ObjectMap<Class<*>, ServiceProvider<*>>()

    fun <T> register(providerClass: Class<T>, provider: ServiceProvider<T>) {
        providerMap.put(providerClass, provider)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(providerClass: Class<T>): ServiceProvider<T> {
        return providerMap.get(providerClass, null) as? ServiceProvider<T>
                ?: throw IllegalArgumentException("No provider registered for class ${providerClass.name}")
    }

    override fun dispose() {
        providerMap.values().forEach { it.dispose() }
        providerMap.clear()
    }
}