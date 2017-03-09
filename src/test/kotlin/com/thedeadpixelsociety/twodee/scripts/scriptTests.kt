package com.thedeadpixelsociety.twodee.scripts

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.thedeadpixelsociety.twodee.components.Scripted
import com.thedeadpixelsociety.twodee.components.Tint
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.systems.ScriptSystem
import org.junit.Assert
import org.junit.Test

class ScriptTests {
    @Test
    fun chainScriptMovesThenRotates() {
        val engine = Engine()
        engine.addSystem(ScriptSystem())
        val entity = Entity()
        val transform = Transform()
        entity.add(transform)

        entity.add(Scripted().apply {
            scripts.add(ChainScript().apply {
                scripts.add(MoveTween(1f, 0f))
                scripts.add(RotateTween(1f))
            })
        })

        engine.addEntity(entity)
        Assert.assertEquals(0f, transform.position.x)
        Assert.assertEquals(0f, transform.angle)
        engine.update(1f)
        Assert.assertEquals(1f, transform.position.x)
        Assert.assertEquals(0f, transform.angle)
        engine.update(1f)
        Assert.assertEquals(1f, transform.position.x)
        Assert.assertEquals(1f, transform.angle)
    }

    @Test
    fun compositeScriptMovesAndRotates() {
        val engine = Engine()
        engine.addSystem(ScriptSystem())
        val entity = Entity()
        val transform = Transform()
        entity.add(transform)

        entity.add(Scripted().apply {
            scripts.add(CompositeScript().apply {
                scripts.add(Move(1f, 0f))
                scripts.add(Rotate(1f))
            })
        })

        engine.addEntity(entity)
        Assert.assertEquals(0f, transform.position.x)
        Assert.assertEquals(0f, transform.angle)
        engine.update(1f)
        Assert.assertEquals(1f, transform.position.x)
        Assert.assertEquals(1f, transform.angle)
    }

    @Test
    fun changeColorFromRedToBlue() {
        val engine = Engine()
        engine.addSystem(ScriptSystem())

        val entity = Entity()
        val tint = Tint().apply { color.set(Color.RED) }
        entity.add(tint)
        entity.add(Scripted().apply {
            scripts.add(ColorTween(Color.RED, Color.BLUE, 3f))
        })

        engine.addEntity(entity)

        Assert.assertEquals(Color.RED, tint.color)

        engine.update(1f)
        engine.update(1f)
        engine.update(.5f)
        engine.update(.25f)
        engine.update(.25f)

        Assert.assertEquals(Color.BLUE, tint.color)
    }

    @Test
    fun move10Units() {
        val engine = Engine()
        engine.addSystem(ScriptSystem())

        val entity = Entity()
        val transform = Transform()
        entity.add(transform)
        entity.add(Scripted().apply {
            scripts.add(Move(10f, 0f))
        })

        engine.addEntity(entity)

        Assert.assertEquals(0f, transform.position.x)
        engine.update(1f)
        Assert.assertEquals(10f, transform.position.x)
    }

    @Test
    fun move10UnitsFor2Seconds() {
        val engine = Engine()
        engine.addSystem(ScriptSystem())

        val entity = Entity()
        val transform = Transform()
        entity.add(transform)
        entity.add(Scripted().apply {
            scripts.add(Move(10f, 0f, 2f))
        })

        engine.addEntity(entity)

        Assert.assertEquals(0f, transform.position.x)
        engine.update(1f)
        Assert.assertEquals(10f, transform.position.x)
        engine.update(1f)
        Assert.assertEquals(20f, transform.position.x)
        engine.update(1f)
        Assert.assertEquals(20f, transform.position.x) // finished last step so shouldn't move again
    }

    @Test
    fun move10UnitsOver2seconds() {
        val engine = Engine()
        engine.addSystem(ScriptSystem())

        val entity = Entity()
        val transform = Transform()
        entity.add(transform)
        entity.add(Scripted().apply {
            scripts.add(MoveTween(10f, 0f, 2f))
        })

        engine.addEntity(entity)

        Assert.assertEquals(0f, transform.position.x)
        engine.update(1f)
        engine.update(1f)
        Assert.assertEquals(10f, transform.position.x)
        engine.update(1f)
        Assert.assertEquals(10f, transform.position.x) // finished last step so shouldn't move again
    }

    @Test
    fun rotate10Degrees() {
        val engine = Engine()
        engine.addSystem(ScriptSystem())

        val entity = Entity()
        val transform = Transform()
        entity.add(transform)
        entity.add(Scripted().apply {
            scripts.add(Rotate(10f))
        })

        engine.addEntity(entity)

        Assert.assertEquals(0f, transform.angle)
        engine.update(1f)
        Assert.assertEquals(10f, transform.angle)
    }

    @Test
    fun rotate10DegreesFor2Seconds() {
        val engine = Engine()
        engine.addSystem(ScriptSystem())

        val entity = Entity()
        val transform = Transform()
        entity.add(transform)
        entity.add(Scripted().apply {
            scripts.add(Rotate(10f, 2f))
        })

        engine.addEntity(entity)

        Assert.assertEquals(0f, transform.angle)
        engine.update(1f)
        Assert.assertEquals(10f, transform.angle)
        engine.update(1f)
        Assert.assertEquals(20f, transform.angle)
        engine.update(1f)
        Assert.assertEquals(20f, transform.angle) // finished last step so shouldn't rotate again
    }

    @Test
    fun rotate10DegreesOver2seconds() {
        val engine = Engine()
        engine.addSystem(ScriptSystem())

        val entity = Entity()
        val transform = Transform()
        entity.add(transform)
        entity.add(Scripted().apply {
            scripts.add(RotateTween(10f, 2f))
        })

        engine.addEntity(entity)

        Assert.assertEquals(0f, transform.angle)
        engine.update(1f)
        engine.update(1f)
        Assert.assertEquals(10f, transform.angle)
        engine.update(1f)
        Assert.assertEquals(10f, transform.angle) // finished last step so shouldn't move again
    }
}