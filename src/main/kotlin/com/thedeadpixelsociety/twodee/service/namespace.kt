package com.thedeadpixelsociety.twodee.service

import com.thedeadpixelsociety.twodee.Func

/**
 * A helper function to create and register a singleton service provider for the specified service instance.
 * @param T The service type.
 * @param instance The service instance.
 */
inline fun <reified T> serviceSingleton(instance: T) {
    ServiceProviderRepository.register(T::class.java, SingletonProvider(instance))
}

/**
 * A helper function to create and register a factory service provider.
 * @param T The service type.
 * @param factory The factory function used to instantiate new service instances.
 */
inline fun <reified T> serviceFactory(noinline factory: Func<T>) {
    ServiceProviderRepository.register(T::class.java, FactoryProvider(factory))
}

/**
 * A helper function to inject services on-demand.
 * @param T The service type.
 * @return An instance of the service, if it exists. Otherwise an exception is thrown.
 * @throws IllegalArgumentException if a provider for the specified service has not been registered.
 */
inline fun <reified T> service() = ServiceProviderRepository.getService(T::class.java)

/**
 * A helper function to inject services via a property delegate.
 * @param T The service type.
 */
inline fun <reified T> injectService() = ServiceDelegate(T::class.java)

