package com.thedeadpixelsociety.twodee.pooling

import com.badlogic.gdx.utils.Pool
import com.thedeadpixelsociety.twodee.Func

/**
 * Wraps a libGDX object pool into an invokable class. This retains access to the underlying pool while being able to
 * obtain new items from that pool by invoking this instance.
 * @see Pool
 */
class InvokablePool<T>(val pool: Pool<T>) {
    operator fun invoke(): T = pool.obtain()

    operator fun invoke(obj: T) {
        pool.free(obj)
    }
}