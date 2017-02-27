package com.thedeadpixelsociety.twodee.service

import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ObjectMap

/**
 * A repository for registering and retrieving various service providers. You should prefer to use the helper functions
 * (serviceSingleton and delegateProvider) to access this instead.
 */
object ServiceProviderRepository : Disposable {
    private val providerMap = ObjectMap<Class<*>, ServiceProvider<*>>()

    /**
     * Registers the specified service provider for the specified class.
     * @param T The provider type.
     * @param serviceClass The provider class.
     * @param provider The service provider.
     */
    fun <T> register(serviceClass: Class<T>, provider: ServiceProvider<T>) {
        providerMap.put(serviceClass, provider)
    }

    /**
     * Gets the specified service provider, if it exists. Otherwise an exception is thrown.
     * @param T The provider type.
     * @param serviceClass The provider class.
     * @return The service provider.
     * @throws IllegalArgumentException if the requested provider has not been registered.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getProvider(serviceClass: Class<T>): ServiceProvider<T> {
        return providerMap.get(serviceClass, null) as? ServiceProvider<T>
                ?: throw MissingServiceException(serviceClass.name)
    }

    /**
     * Gets the specified service instance, if it exists. Otherwise an exception is thrown.
     * @param T The service type.
     * @param serviceClass The service class.
     * @return An instance of the specified service.
     * @throws IllegalArgumentException if the requested provider has not been registered.
     */
    fun <T> getService(serviceClass: Class<T>) = getProvider(serviceClass)()

    override fun dispose() {
        providerMap.values().forEach { it.dispose() }
        providerMap.clear()
    }
}