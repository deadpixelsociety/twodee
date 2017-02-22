package com.thedeadpixelsociety.twodee.service

import com.thedeadpixelsociety.twodee.Func

inline fun <reified T> singletonProvider(instance: T) {
    ServiceProviderRepository.register(T::class.java, SingletonProvider(instance))
}

inline fun <reified T> delegateprovider(noinline delegate: Func<T>) {
    ServiceProviderRepository.register(T::class.java, DelegateProvider(delegate))
}