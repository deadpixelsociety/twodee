package com.thedeadpixelsociety.twodee.pooling

import com.badlogic.gdx.utils.Pools
import com.thedeadpixelsociety.twodee.Func

/**
 * A helper function to create a pool property delegate with an optional provider.
 * @param T The pool type.
 * @param max The maximum number of pooled objects. Defaults to 100.
 * @param provider The provider function, if any. This is invoked to create a new instance of a pooled object.
 * @return An invokable pool delegate.
 * @see InvokablePoolDelegate
 * @see InvokablePool
 */
inline fun <reified T> pooled(max: Int = 100, noinline provider: Func<T>? = null): InvokablePoolDelegate<T> {
    return InvokablePoolDelegate(T::class.java, max)
}

/**
 * A helper function to create a pool with an optional provider.
 * @param T The pool type.
 * @param max The maximum number of pooled objects. Defaults to 100.
 * @param provider The provider function, if any. This is invoked to create a new instance of a pooled object.
 * @return An invokable pool.
 * @see InvokablePool
 */
inline fun <reified T> pool(max: Int = 100, noinline provider: Func<T>? = null): InvokablePool<T> {
    return if (provider == null) InvokablePool(Pools.get(T::class.java, max)) else InvokablePool(provider, max)
}
