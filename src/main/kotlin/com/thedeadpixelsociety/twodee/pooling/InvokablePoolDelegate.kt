package com.thedeadpixelsociety.twodee.pooling

import com.badlogic.gdx.utils.Pools
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A read-only delegate for an invokable pool. This allows the use of property delegates to easily create pools and
 * obtain objects via invocation of the property.
 * @param T The pool type.
 * @param poolClass The pool class.
 * @param max The maximum number of poolable objects. Defaults to 100.
 */
class InvokablePoolDelegate<T>(poolClass: Class<T>, max: Int = 100) : ReadOnlyProperty<Any, InvokablePool<T>> {
    private val pool = InvokablePool<T>(Pools.get(poolClass, max))

    override fun getValue(thisRef: Any, property: KProperty<*>) = pool
}