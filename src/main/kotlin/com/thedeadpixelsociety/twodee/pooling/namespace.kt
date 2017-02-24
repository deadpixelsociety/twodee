package com.thedeadpixelsociety.twodee.pooling

import com.badlogic.gdx.utils.Pool
import com.badlogic.gdx.utils.Pools
import com.thedeadpixelsociety.twodee.Func

/**
 * A helper function to create a pool property delegate with an optional factory.
 * @param T The pool type.
 * @param max The maximum number of pooled objects. Defaults to 100.
 * @param factory The factory function, if any. This is invoked to create a new instance of a pooled object.
 * @return An invokable pool delegate.
 * @see InvokablePoolDelegate
 * @see InvokablePool
 */
inline fun <reified T> pooled(max: Int = 100, noinline factory: Func<T>? = null): InvokablePoolDelegate<T> {
    return InvokablePoolDelegate(T::class.java, max)
}

/**
 * A helper function to create a pool with an optional factory.
 * @param T The pool type.
 * @param max The maximum number of pooled objects. Defaults to 100.
 * @param factory The factory function, if any. This is invoked to create a new instance of a pooled object.
 * @return An invokable pool.
 * @see InvokablePool
 */
inline fun <reified T> pool(max: Int = 100, noinline factory: Func<T>? = null): InvokablePool<T> {
    val pool = if (factory == null) Pools.get(T::class.java, max) else object : Pool<T>() {
        override fun newObject() = factory()
    }

    return InvokablePool(pool)
}
