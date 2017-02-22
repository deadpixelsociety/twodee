package com.thedeadpixelsociety.twodee.scene

import com.badlogic.gdx.Gdx
import com.thedeadpixelsociety.twodee.gdxArray

/**
 * A stack scene controller maintains a collection of scenes 'stacked' on top of each other. When a new scene is
 * added all others below it will automatically be removed unless the new scene is an overlay. The top-most scene is
 * updated first and only it will have its input method called.
 *
 * All active scenes will be rendered bottom-to-top.
 *
 * @see Scene
 */
class StackSceneController : SceneController {
    private val stack = gdxArray<Scene>()

    override fun scenes(): List<Scene> = stack.toList()

    override fun add(scene: Scene) {
        stack.add(scene)
        scene.added()
        scene.resize(Gdx.graphics.width, Gdx.graphics.height)
    }

    override fun remove(scene: Scene) {
        stack.removeValue(scene, true)
        scene.removed()
        scene.dispose()
    }

    override fun pause() {
        scenes().forEach(Scene::pause)
    }

    override fun resume() {
        scenes().forEach(Scene::resume)
    }

    override fun resize(width: Int, height: Int) {
        scenes().forEach { it.resize(width, height) }
    }

    override fun update(deltaTime: Float) {
        var top = true
        val scenes = scenes()
        for (i in scenes.size - 1 downTo 0) {
            val scene = scenes[i]
            scene.update(deltaTime)
            if (!top) remove(scene)
            if (!scene.overlay) top = false
        }
    }

    override fun render(deltaTime: Float) {
        scenes().forEach { it.render(deltaTime) }
    }

    override fun dispose() {
        scenes().forEach { remove(it) }
    }
}