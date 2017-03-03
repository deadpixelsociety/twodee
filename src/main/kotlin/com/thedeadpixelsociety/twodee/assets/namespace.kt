package com.thedeadpixelsociety.twodee.assets

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.thedeadpixelsociety.twodee.Action

/**
 * Loads an asset from the asset manager.
 * @param T The asset type.
 * @param filename The asset filename.
 * @param init An optional initialization function with the realized asset.
 * @return A delegate used to load the asset.
 * @see AssetDelegate<T>
 */
inline fun <reified T> asset(filename: String, noinline init: Action<T>? = null) = AssetDelegate(
        filename,
        T::class.java,
        init
)

/**
 * Loads and initializes a ShaderProgram from the asset manager. This function will exit the program if the shader
 * fails to compile.
 * @param filename One of the shader files. If the .vert is loaded it will look for a .frag with the same name and vice
 * versa.
 * @return The created shader program.
 * @see ShaderProgram
 */
fun shader(filename: String) = asset<ShaderProgram>(filename) {
    if (!it.isCompiled) {
        error("Shader not compilted: ${it.log}")
    }

    if (it.log.isNotEmpty()) println("Shader log: ${it.log}")
}
