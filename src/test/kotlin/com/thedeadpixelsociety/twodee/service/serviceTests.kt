package com.thedeadpixelsociety.twodee.service

import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Test

class ServiceTests {
    private val singletonDelegate by injectService<SingletonService>()
    private val factoryDelegate by injectService<FactoryService>()

    @Test
    fun serviceSingletonReturnsSameInstance() {
        serviceSingleton(SingletonService())
        assertSame(service<SingletonService>(), service<SingletonService>())
    }

    @Test
    fun serviceFactoryReturnsDifferentInstance() {
        serviceFactory { FactoryService() }
        assertNotSame(service<FactoryService>(), service<FactoryService>())
    }

    @Test
    fun injectServiceFromSingleton() {
        serviceSingleton(SingletonService())
        assertSame(singletonDelegate, singletonDelegate)
    }

    @Test
    fun injectServiceFromFactory() {
        serviceFactory { FactoryService() }
        assertNotSame(factoryDelegate, factoryDelegate)
    }

    class SingletonService

    class FactoryService
}
