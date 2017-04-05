package com.thedeadpixelsociety.twodee.assets

import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.AssetManager
import com.thedeadpixelsociety.twodee.Action
import com.thedeadpixelsociety.twodee.service.injectService
import java.lang.IllegalArgumentException
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A read-only delegate for an asset.
 * @param T The asset type.
 * @param filename The asset filename.
 * @param assetClass The asset class.
 * @param parameters Optional asset loader parameters.
 * @param init An optional initialization function.
 */
class AssetDelegate<T, P : AssetLoaderParameters<T>>(
        private val filename: String,
        private val assetClass: Class<T>,
        private val parameters: P? = null,
        private val init: Action<T>? = null) : ReadOnlyProperty<Any, T> {
    private val assetManager by injectService<AssetManager>()
    private var asset: T? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        if (asset == null) asset = loadAsset()
        return asset ?: throw IllegalArgumentException("$filename: Invalid asset or asset not loaded.")
    }

    private fun loadAsset(): T? {
        if (!assetManager.isLoaded(filename, assetClass)) {
            assetManager.load(filename, assetClass, parameters)
            assetManager.finishLoading()
        }

        return assetManager.get(filename, assetClass)?.apply { init?.invoke(this) }
    }
}