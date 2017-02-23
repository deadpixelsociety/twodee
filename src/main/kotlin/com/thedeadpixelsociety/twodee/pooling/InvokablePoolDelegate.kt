package com.thedeadpixelsociety.twodee.pooling

import com.badlogic.gdx.utils.Pools
import com.thedeadpixelsociety.twodee.Func
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A read-only delegate for an invokable pool. This allows the use of property delegates to easily create pools and
 * obtain objects via invocation of the property.
 * @param T The pool type.
 * @param poolClass The pool class.
 * @param max The maximum number of poolable objects. Defaults to 100.
 * @param provider The provider function, if any. This is invoked to create a new instance of a pooled object.
 */
class InvokablePoolDelegate<T>(poolClass: Class<T>, max: Int = 100, provider: Func<T>? = null)
    : ReadOnlyProperty<Any, InvokablePool<T>> {

    private val invokablePool: InvokablePool<T> = if (provider == null)
        InvokablePool(Pools.get<T>(poolClass, max))
    else
        InvokablePool(provider, max)

    override fun getValue(thisRef: Any, property: KProperty<*>) = invokablePool
}