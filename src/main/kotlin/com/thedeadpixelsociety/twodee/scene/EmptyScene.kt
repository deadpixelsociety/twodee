package com.thedeadpixelsociety.twodee.scene

/**
 * An empty implementation of a scene. Inherit from this class to cherry pick what is used and ignore the rest.
 */
abstract class EmptyScene : Scene {
    override val overlay = false

    override fun added() {
    }

    override fun removed() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun input(deltaTime: Float) {
    }

    override fun update(deltaTime: Float) {
    }

    override fun render(deltaTime: Float) {
    }

    override fun dispose() {
    }
}