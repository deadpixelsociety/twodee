package com.thedeadpixelsociety.twodee.assets

import com.thedeadpixelsociety.twodee.Action

/**
 * Loads an asset from the asset manager.
 * @param T The asset type.
 * @param filename The asset filename.
 * @param init An optional initialization function with the realized asset.
 */
inline fun <reified T> asset(filename: String, noinline init: Action<T>? = null) = AssetDelegate(
        filename,
        T::class.java,
        init
)