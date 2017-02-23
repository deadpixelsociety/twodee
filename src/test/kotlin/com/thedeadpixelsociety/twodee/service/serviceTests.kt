package com.thedeadpixelsociety.twodee.service

import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Test

class ServiceTests {
    @Test
    fun serviceSingletonReturnsSameInstance() {
        val service = MockService()
        serviceSingleton(service)
        assertSame(service, service<MockService>())
    }

    @Test
    fun serviceFactoryReturnsDifferentInstance() {
        serviceFactory { MockService() }
        val service = ServiceProviderRepository.getService(MockService::class.java)
        assertNotSame(service, service<MockService>())
    }

    class MockService
}
