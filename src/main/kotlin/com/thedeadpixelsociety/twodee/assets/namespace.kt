package com.thedeadpixelsociety.twodee.assets

import com.badlogic.gdx.assets.AssetLoaderParameters
import com.badlogic.gdx.assets.loaders.ShaderProgramLoader
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.thedeadpixelsociety.twodee.Action

/**
 * Loads an asset from the asset manager.
 * @param T The asset type.
 * @param filename The asset filename.
 * @param parameters Optional asset loader parameters.
 * @param init An optional initialization function with the realized asset.
 * @return A delegate used to load the asset.
 * @see AssetDelegate<T>
 */
inline fun <reified T> asset(
        filename: String,
        parameters: AssetLoaderParameters<T>? = null,
        noinline init: Action<T>? = null) = AssetDelegate(
        filename,
        T::class.java,
        parameters,
        init
)

/**
 * Loads and initializes a ShaderProgram from the asset manager. This function will exit the program if the shader
 * fails to compile.
 * @param vertFileName The vertex shader filename. Should end with a 'vert' extension.
 * @param fragFileName The fragment sahder filename. Should end with a 'frag' extension. If left empty this will
 * default to the vertex filename.
 * versa.
 * @return The created shader program.
 * @see ShaderProgram
 */
fun shader(vertFileName: String, fragFileName: String? = null) = asset<ShaderProgram>(
        vertFileName,
        ShaderProgramLoader.ShaderProgramParameter().apply {
            this.vertexFile = vertFileName
            this.fragmentFile = fragFileName
        }
) {
    if (!it.isCompiled) {
        error("Shader not compilted: ${it.log}")
    }

    if (it.log.isNotEmpty()) println("Shader log: ${it.log}")
}
