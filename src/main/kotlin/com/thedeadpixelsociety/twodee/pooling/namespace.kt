package com.thedeadpixelsociety.twodee.pooling

import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Pools

/**
 * A helper function to create a pool property delegate.
 * @param T The pool type.
 * @param poolClass The pool class.
 * @return An invokable pool delegate.
 * @see InvokablePoolDelegate
 * @see InvokablePool
 */
fun <T> pool(poolClass: Class<T>, max: Int = 100) = InvokablePoolDelegate(poolClass, max)

/**
 * A helper function to free any pooled object. Non-pooled objects will be ignored.
 * @param obj The object to free.
 */
fun free(obj: Any) = Pools.free(obj)

/**
 * A helper function to free any pooled object. Non-pooled objects will be ignored.
 * @param objs The objects to free.
 */
fun free(objs: Array<Any>) = Pools.freeAll(objs)

/**
 * A helper function to free an invokable pool. This clears the pool and all objects in it.
 * @see InvokablePool
 */
fun <T> free(pool: InvokablePool<T>) {
    pool.pool.clear()
}