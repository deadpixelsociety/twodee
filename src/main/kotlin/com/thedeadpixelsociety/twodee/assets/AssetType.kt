package com.thedeadpixelsociety.twodee.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Cubemap
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.I18NBundle
import com.thedeadpixelsociety.twodee.service.injectService
import kotlin.system.exitProcess

/**
 * Enumerates the available default asset types.
 * @param T The asset type.
 * @param assetClass The asset class.
 */
sealed class AssetType<T>(val assetClass: Class<T>) {
    private val assetManager by injectService<AssetManager>()

    class AnyAsset<T>(assetClass: Class<T>) : AssetType<T>(assetClass)
    class BitmapFontAsset : AssetType<BitmapFont>(BitmapFont::class.java)
    class CubemapAsset : AssetType<Cubemap>(Cubemap::class.java)
    class I18NBundleAsset : AssetType<I18NBundle>(I18NBundle::class.java)
    class ModelAsset : AssetType<Model>(Model::class.java)
    class MusicAsset : AssetType<Music>(Music::class.java)
    class ParticleEffectAsset : AssetType<ParticleEffect>(ParticleEffect::class.java)
    class PixmapAsset : AssetType<Pixmap>(Pixmap::class.java)
    class ShaderProgramAsset : AssetType<ShaderProgram>(ShaderProgram::class.java) {
        override fun load(filename: String): ShaderProgram {
            val shader = super.load(filename)
            if (!shader.isCompiled) {
                println(shader.log)
                exitProcess(0)
            }

            if (shader.log.isNotEmpty()) println(shader.log)

            return shader
        }
    }

    class SkinAsset : AssetType<Skin>(Skin::class.java)
    class SoundAsset : AssetType<Sound>(Sound::class.java)
    class TextureAtlasAsset : AssetType<TextureAtlas>(TextureAtlas::class.java)
    class TextureAsset : AssetType<Texture>(Texture::class.java)

    /**
     * Loads the specified asset. This will check if the asset has been loaded and if not load it on demand.
     * @param filename The asset filename.
     * @return The loaded asset.
     */
    open fun load(filename: String): T {
        if (!assetManager.isLoaded(filename, assetClass)) {
            assetManager.load(filename, assetClass)
            assetManager.finishLoading()
        }

        return assetManager.get(filename, assetClass)
    }
}