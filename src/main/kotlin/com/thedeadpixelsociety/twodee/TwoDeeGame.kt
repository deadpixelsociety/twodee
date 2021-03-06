package com.thedeadpixelsociety.twodee

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ai.GdxAI
import com.badlogic.gdx.ai.msg.MessageDispatcher
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.thedeadpixelsociety.twodee.scene.StackSceneController
import com.thedeadpixelsociety.twodee.service.ServiceProviderRepository
import com.thedeadpixelsociety.twodee.service.injectService
import com.thedeadpixelsociety.twodee.service.service
import com.thedeadpixelsociety.twodee.service.serviceSingleton

/**
 * A base game class to kickstart your own. Automatically adds a StackSceneController service, a SpriteBatch service,
 * and a ShapeRender service. It also manages the TimeController and SceneController for you.
 */
abstract class TwoDeeGame : ApplicationAdapter() {
    protected val sceneService by injectService<StackSceneController>()

    override fun create() {
        TimeController.reset()

        serviceSingleton(AssetManager())
        serviceSingleton(StackSceneController(Gdx.graphics.width, Gdx.graphics.height))
        serviceSingleton(SpriteBatch())
        serviceSingleton(ShapeRenderer())
        serviceSingleton(MessageDispatcher())
    }

    override fun pause() {
        sceneService.pause()
    }

    override fun resume() {
        sceneService.resume()
    }

    override fun resize(width: Int, height: Int) {
        sceneService.resize(width, height)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        GdxAI.getTimepiece().update(TimeController.deltaTime)
        sceneService.update(deltaTime())
        service<MessageDispatcher>().update()
        sceneService.render()
    }

    override fun dispose() {
        ServiceProviderRepository.dispose()
    }
}