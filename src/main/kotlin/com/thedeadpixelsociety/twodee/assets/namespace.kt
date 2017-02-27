package com.thedeadpixelsociety.twodee.assets

/**
 * Loads the specified asset from the asset manager.
 * @param T The asset type.
 * @param assetClass The asset class.
 * @param filename The filename.
 * @return The loaded asset.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun <T> asset(assetClass: Class<T>, filename: String) = AssetDelegate(AssetType.AnyAsset(assetClass), filename)

/**
 * Loads the specified bitmap font from the asset manager.
 * @param filename The filename.
 * @return The loaded BitmapFont.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun bitmapFont(filename: String) = AssetDelegate(AssetType.BitmapFontAsset(), filename)

/**
 * Loads the specified cubemap from the asset manager.
 * @param filename The filename.
 * @return The loaded Cubemap.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun cubemap(filename: String) = AssetDelegate(AssetType.CubemapAsset(), filename)

/**
 * Loads the specified I18N bundle from the asset manager.
 * @param filename The filename.
 * @return The loaded I18N bundle.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun i18n(filename: String) = AssetDelegate(AssetType.I18NBundleAsset(), filename)

/**
 * Loads the specified model from the asset manager.
 * @param filename The filename.
 * @return The loaded Model.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun model(filename: String) = AssetDelegate(AssetType.ModelAsset(), filename)

/**
 * Loads the specified music from the asset manager.
 * @param filename The filename.
 * @return The loaded Music.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun music(filename: String) = AssetDelegate(AssetType.MusicAsset(), filename)

/**
 * Loads the specified particle effect from the asset manager.
 * @param filename The filename.
 * @return The loaded ParticleEffect.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun particleEffect(filename: String) = AssetDelegate(AssetType.ParticleEffectAsset(), filename)

/**
 * Loads the specified pixmap from the asset manager.
 * @param filename The filename.
 * @return The loaded Pixmap.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun pixmap(filename: String) = AssetDelegate(AssetType.PixmapAsset(), filename)

/**
 * Loads the specified shader program from the asset manager.
 * @param filename The filename.
 * @return The loaded ShaderProgram.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun shader(filename: String) = AssetDelegate(AssetType.ShaderProgramAsset(), filename)

/**
 * Loads the specified UI skin from the asset manager.
 * @param filename The filename.
 * @return The loaded Skin.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun skin(filename: String) = AssetDelegate(AssetType.SkinAsset(), filename)

/**
 * Loads the specified sound from the asset manager.
 * @param filename The filename.
 * @return The loaded Sound.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun sound(filename: String) = AssetDelegate(AssetType.SoundAsset(), filename)

/**
 * Loads the specified texture atlas from the asset manager.
 * @param filename The filename.
 * @return The loaded TextureAtlas.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun textureAtlas(filename: String) = AssetDelegate(AssetType.TextureAtlasAsset(), filename)

/**
 * Loads the specified texture from the asset manager.
 * @param filename The filename.
 * @return The loaded Texture.
 * @throws IllegalArgumentException if the asset does not exist or was not loaded.
 */
fun texture(filename: String) = AssetDelegate(AssetType.TextureAsset(), filename)
