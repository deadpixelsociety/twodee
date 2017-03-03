package com.thedeadpixelsociety.twodee.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.backends.headless.HeadlessApplication
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration
import com.badlogic.gdx.graphics.OrthographicCamera
import com.thedeadpixelsociety.twodee.components.Dolly
import com.thedeadpixelsociety.twodee.components.GroupMask
import com.thedeadpixelsociety.twodee.components.Tag
import com.thedeadpixelsociety.twodee.components.Transform
import com.thedeadpixelsociety.twodee.components.dolly.actions.PanDolly
import com.thedeadpixelsociety.twodee.components.dolly.actions.PanDollyTo
import com.thedeadpixelsociety.twodee.components.dolly.actions.RotateDolly
import com.thedeadpixelsociety.twodee.components.dolly.actions.RotateDollyTo
import org.junit.Assert.*
import org.junit.Test

class SystemTests {
    companion object {
        const val PLAYER = 1
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
        engine.addSystem(dollySystem)

        val entity = Entity()
        val transform = Transform()
        val dolly = Dolly(OrthographicCamera(100f, 100f).apply {
            setToOrtho(false)
        })

        entity.add(transform)
        entity.add(dolly)

        engine.addEntity(entity)

        dolly.actions.add(PanDolly().apply {
            amount.set(1f, 0f)
            duration = 1f
        })

        dolly.actions.add(RotateDolly().apply {
            amount = 90f
            duration = 1f
        })

        engine.update(.5f)
        assertEquals(.5f, transform.position.x)
        assertEquals(45f, transform.angle)

        engine.update(.5f)
        assertEquals(1f, transform.position.x)
        assertEquals(90f, transform.angle)

        assertTrue(dolly.actions.size == 0) // actions removed

        transform.angle = 0f
        transform.position.set(0f, 0f)

        dolly.actions.add(PanDollyTo().apply {
            target.set(10f, 0f)
            time = 10f
        })

        for (i in 0..10) {
            engine.update(1f)
        }

        engine.update(1f)

        assertEquals(10f, transform.position.x)

        dolly.actions.add(RotateDollyTo().apply {
            target = 180f
            time = 10f
        })

        for (i in 0..10) {
            engine.update(1f)
        }

        engine.update(1f)

        assertEquals(180f, transform.angle)
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
