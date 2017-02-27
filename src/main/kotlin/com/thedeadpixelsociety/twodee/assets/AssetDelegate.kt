package com.thedeadpixelsociety.twodee.assets

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class AssetDelegate<out T>(private val assetType: AssetType<T>, private val filename: String) : ReadOnlyProperty<Any, T> {
    private var asset: T? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        if (asset == null) asset = assetType.load(filename)
        return asset ?: throw IllegalArgumentException("Missing asset $filename or asset not loaded.")
    }
}