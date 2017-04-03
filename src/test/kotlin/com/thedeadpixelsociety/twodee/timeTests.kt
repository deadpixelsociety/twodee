package com.thedeadpixelsociety.twodee

import org.junit.Assert.assertEquals
import org.junit.Test

class TimeTests {
    companion object {
        const val STEP = 1f / 60f
    }

    @Test
    fun timeIncrements() {
        TimeController.reset()
        TimeController.update(STEP)
        assertEquals(STEP, TimeController.deltaTime)
        assertEquals(STEP, TimeController.totalTime)
        TimeController.update(STEP)
        assertEquals(STEP, TimeController.deltaTime)
        assertEquals(STEP * 2f, TimeController.totalTime)
    }

    @Test
    fun tickEventTriggers() {
        TimeController.reset()

        var ticked = false

        tickEvent { ticked = !ticked }

        TimeController.update(STEP)
        assertEquals(true, ticked)
    }

    @Test
    fun tickEventTriggerAtIntervals() {
        TimeController.reset()

        var ticked = false

        tickEvent(interval = 3f, repeat = 1) {
            ticked = !ticked
        }

        TimeController.update(STEP)
        assertEquals(true, ticked)
        TimeController.update(3f)
        assertEquals(false, ticked)
    }

    @Test
    fun tickAfterDelay() {
        TimeController.reset()

        var ticked = false
        tickEvent(STEP * 2) {
            ticked = true
        }

        TimeController.update(STEP)
        assertEquals(false, ticked)
        TimeController.update(STEP)
        TimeController.update(STEP)
        TimeController.update(STEP)
        assertEquals(true, ticked)
    }

    @Test
    fun tickEventRepeats() {
        TimeController.reset()

        var ticked = false

        tickEvent(interval = 0f, repeat = 2) {
            ticked = !ticked
        }

        TimeController.update(STEP)
        assertEquals(true, ticked)
        TimeController.update(STEP)
        assertEquals(false, ticked)
    }
}
