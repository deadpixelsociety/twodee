package com.thedeadpixelsociety.twodee.scene

import com.badlogic.gdx.utils.Disposable

/**
 * Controls a collection of scenes and acts as an interface to the main libGDX lifecycle.
 */
interface SceneController : Disposable {
    /**
     * Returns an immutable list of scenes in this controller.
     * @return A @see List<Scene> containing the scenes in this controller.
     */
    fun scenes(): List<Scene>

    /**
     * Adds the specified scene to this controller. The scene will have its added and resize methods called in that
     * order.
     * @param scene The scene to add.
     */
    fun add(scene: Scene)

    /**
     * Removed the specified scene from this controller. The scene will have its removed and dispose methods called in
     * that order.
     * @param scene The scene to remove.
     */
    fun remove(scene: Scene)

    /**
     * Pause all scenes in this controller.
     */
    fun pause()

    /**
     * Resume all scenes in this controller from a paused state.
     */
    fun resume()

    /**
     * Resize all scenes in this controller.
     */
    fun resize(width: Int, height: Int)

    /**
     * Update all scenes in this controller.
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    fun update(deltaTime: Float)

    /**
     * Render all scenes in this controller.
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    fun render(deltaTime: Float)
}