package com.thedeadpixelsociety.twodee.service

import com.thedeadpixelsociety.twodee.Func

inline fun <reified T> serviceSingleton(instance: T) {
    ServiceProviderRepository.register(T::class.java, SingletonProvider(instance))
}

inline fun <reified T> serviceFactory(noinline factory: Func<T>) {
    ServiceProviderRepository.register(T::class.java, FactoryProvider(factory))
}

inline fun <reified T> service() = ServiceProviderRepository.getService(T::class.java)
