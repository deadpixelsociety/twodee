package com.thedeadpixelsociety.twodee.scene

import org.junit.Assert.assertEquals
import org.junit.Test

class SceneTests {
    @Test
    fun sceneInStackController() {
        val controller = StackSceneController(640, 480)
        val scene = MockScene(false)
        controller.add(scene)
        assertEquals(true, scene.wasAdded)
        assertEquals(true, scene.wasResized)
        controller.pause()
        assertEquals(true, scene.wasPaused)
        controller.resume()
        assertEquals(true, scene.wasResumed)
        controller.update(0f)
        assertEquals(true, scene.handledInput)
        assertEquals(true, scene.wasUpdated)
        controller.render(0f)
        assertEquals(true, scene.wasRendered)
        controller.remove(scene)
        assertEquals(true, scene.wasRemoved)
        assertEquals(true, scene.wasDisposed)
    }

    @Test
    fun sceneRemovedDueToNonOverlay() {
        val controller = StackSceneController(640, 480)
        val scene = MockScene(false)
        val scene2 = MockScene(false)
        controller.add(scene)
        controller.add(scene2)
        controller.update(0f)

        assertEquals(true, scene.wasRemoved)
        assertEquals(1, controller.scenes().size)
    }

    @Test
    fun overlaySceneAdded() {
        val controller = StackSceneController(640, 480)
        val scene = MockScene(false)
        val scene2 = MockScene(true)
        controller.add(scene)
        controller.add(scene2)
        controller.update(0f)

        assertEquals(false, scene.wasRemoved)
        assertEquals(2, controller.scenes().size)
    }

    class MockScene(override val overlay: Boolean) : EmptyScene() {
        var wasAdded = false
        var wasRemoved = false
        var wasPaused = false
        var wasResumed = false
        var wasResized = false
        var handledInput = false
        var wasUpdated = false
        var wasRendered = false
        var wasDisposed = false

        override fun added() {
            wasAdded = true
        }

        override fun removed() {
            wasRemoved = true
        }

        override fun pause() {
            wasPaused = true
        }

        override fun resume() {
            wasResumed = true
        }

        override fun resize(width: Int, height: Int) {
            wasResized = true
        }

        override fun input(deltaTime: Float) {
            handledInput = true
        }

        override fun update(deltaTime: Float) {
            wasUpdated = true
        }

        override fun render(deltaTime: Float) {
            wasRendered = true
        }

        override fun dispose() {
            wasDisposed = true
        }
    }
}