package com.thedeadpixelsociety.twodee.scene

import com.badlogic.gdx.utils.Disposable

/**
 * A scene acts as a game state. A collection of scenes is used to build the structure of your game.
 */
interface Scene : Disposable {
    /**
     * Determines whether this scene is an overlay or not.
     * @return true if the scene is an overlay.
     */
    val overlay: Boolean

    /**
     * Called when the scene is added to its controller.
     */
    fun added()

    /**
     * Called when the scene is removed from its controller.
     */
    fun removed()

    /**
     * Called when the game has paused.
     */
    fun pause()

    /**
     * Called when the game has resumed from a paused state.
     */
    fun resume()

    /**
     * Called when the game window has resized.
     * @param width The new width of the game window, in pixels.
     * @param height The new height of the game window, in pixels.
     */
    fun resize(width: Int, height: Int)

    /**
     * Called when the scene should handle user input.
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    fun input(deltaTime: Float)

    /**
     * Called when the scene should be updated.
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    fun update(deltaTime: Float)

    /**
     * Called when the scene should be rendered.
     */
    fun render()
}