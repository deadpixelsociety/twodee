package com.thedeadpixelsociety.twodee.pooling

import org.junit.Assert.*
import org.junit.Test

class PoolingTests {
    private val delegatePool by pooled<Any>()

    @Test
    fun poolByDelegate() {
        val instance = delegatePool()
        assertNotNull(instance)
        delegatePool(instance)
        assertEquals(1, delegatePool.pool.free)
        assertSame(instance, delegatePool())
        assertNotSame(instance, delegatePool())
    }

    @Test
    fun inlinePool() {
        val mockPool = pool<Any>()
        val instance = mockPool()
        assertNotNull(instance)
        mockPool(instance)
        assertEquals(1, mockPool.pool.free)
        assertSame(instance, mockPool())
        assertNotSame(instance, mockPool())
    }

    @Test
    fun inlineProviderPool() {
        val mockPool = pool { Any() }
        val instance = mockPool()
        assertNotNull(instance)
        mockPool(instance)
        assertEquals(1, mockPool.pool.free)
        assertSame(instance, mockPool())
        assertNotSame(instance, mockPool())
    }
}
