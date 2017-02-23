package com.thedeadpixelsociety.twodee.pooling

import com.badlogic.gdx.utils.Pool
import com.thedeadpixelsociety.twodee.Func

/**
 * Wraps a libGDX object pool into an invokable class. This retains access to the underlying pool while being able to
 * obtain new items from that pool by invoking this instance.
 * @see Pool
 */
class InvokablePool<T>(val pool: Pool<T>) {
    /**
     * Creates an invokable pool with a provider function. This generates a new pool that uses the provider to create
     * instances of poolable object.
     * @param provider The object provider.
     * @param max The maximum number of poolable objects.
     */
    constructor(provider: Func<T>, max: Int) : this(object : Pool<T>(max) {
        override fun newObject() = provider()
    })

    operator fun invoke(): T = pool.obtain()

    operator fun invoke(obj: T) {
        pool.free(obj)
    }
}