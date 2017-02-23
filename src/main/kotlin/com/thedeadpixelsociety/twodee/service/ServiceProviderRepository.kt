package com.thedeadpixelsociety.twodee.service

import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ObjectMap

/**
 * A repository for registering and retrieving various service providers. You should prefer to use the helper functions
 * (singletonProvider and delegateProvider) to access this instead.
 */
object ServiceProviderRepository : Disposable {
    private val providerMap = ObjectMap<Class<*>, ServiceProvider<*>>()

    /**
     * Registers the specified service provider for the specified class.
     * @param T The provider type.
     * @param providerClass The provider class.
     * @param provider The service provider.
     */
    fun <T> register(providerClass: Class<T>, provider: ServiceProvider<T>) {
        providerMap.put(providerClass, provider)
    }

    /**
     * Gets the specified service provider, if it exists. Otherwise an exception is thrown.
     * @param T The provider type.
     * @param providerClass The provider class.
     * @return The service provider.
     * @exception IllegalArgumentException if the requested provider has not been registered.
     */
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