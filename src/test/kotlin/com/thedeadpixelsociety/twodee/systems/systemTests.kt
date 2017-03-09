package com.thedeadpixelsociety.twodee.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.backends.headless.HeadlessApplication
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration
import com.badlogic.gdx.graphics.OrthographicCamera
import com.thedeadpixelsociety.twodee.components.*
import com.thedeadpixelsociety.twodee.scripts.Move
import com.thedeadpixelsociety.twodee.scripts.MoveTo
import com.thedeadpixelsociety.twodee.scripts.Rotate
import com.thedeadpixelsociety.twodee.scripts.RotateTo
import org.junit.Assert.*
import org.junit.Test

class SystemTests {
    companion object {
        const val PLAYER = "player"
        const val GROUP1 = 0x01
        const val GROUP2 = 0x02
        const val GROUP3 = 0x04
    }


    @Test
    fun dollySystem() {
        HeadlessApplication(object : ApplicationAdapter() {

        }, HeadlessApplicationConfiguration())

        val engine = Engine()
        val dollySystem = DollySystem()
        engine.addSystem(ScriptSystem())
        engine.addSystem(dollySystem)

        val entity = Entity()
        val transform = Transform()
        val scripted = Scripted()
        val dolly = Dolly(OrthographicCamera(100f, 100f).apply {
            setToOrtho(false)
        })

        entity.add(transform)
        entity.add(dolly)
        entity.add(scripted)

        engine.addEntity(entity)

        scripted.scripts.add(Move(1f, 0f, 1f))
        scripted.scripts.add(Rotate(90f, 1f))

        engine.update(.5f)
        assertEquals(.5f, transform.position.x)
        assertEquals(45f, transform.angle)

        engine.update(.5f)
        assertEquals(1f, transform.position.x)
        assertEquals(90f, transform.angle)

        assertTrue(scripted.scripts.size == 0) // actions removed

        transform.angle = 0f
        transform.position.set(0f, 0f)

        scripted.scripts.add(MoveTo(10f, 0f, 10f))

        for (i in 0..10) {
            engine.update(1f)
        }

        engine.update(1f)

        assertEquals(10f, transform.position.x)

        scripted.scripts.add(RotateTo(180f, 10f))

        for (i in 0..10) {
            engine.update(1f)
        }

        engine.update(1f)

        assertEquals(180f, transform.angle)

        assertEquals(10f, dolly.camera.position.x)
        assertEquals(-8.742278E-8f, dolly.camera.up.x)
    }

    @Test
    fun tagSystem() {
        val engine = Engine()
        val tagSystem = TagSystem()
        engine.addSystem(tagSystem)

        val entity = Entity()
        entity.add(Tag().apply { id = PLAYER })

        engine.addEntity(entity)

        val player = tagSystem[PLAYER]

        assertSame(entity, player)
    }

    @Test
    fun groupSystem() {
        val engine = Engine()
        val groupSystem = GroupSystem()
        engine.addSystem(groupSystem)

        fun createEntity(mask: Int): Entity {
            val entity = Entity()
            entity.add(GroupMask().apply { this.mask = mask })
            engine.addEntity(entity)
            return entity
        }

        val entity1 = createEntity(GROUP1)
        val entity2 = createEntity(GROUP2)
        val entity3 = createEntity(GROUP3)
        val entity4 = createEntity(GROUP2 or GROUP3)

        assertTrue(groupSystem[GROUP1].contains(entity1))
        assertTrue(groupSystem[GROUP2].contains(entity2))
        assertTrue(groupSystem[GROUP2].contains(entity4))
        assertTrue(groupSystem[GROUP3].contains(entity3))
        assertTrue(groupSystem[GROUP3].contains(entity4))
    }
}
