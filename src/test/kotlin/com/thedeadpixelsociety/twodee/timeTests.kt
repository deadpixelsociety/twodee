package com.thedeadpixelsociety.twodee

import org.junit.Test
import org.junit.Assert.*

class TimeTests {
    @Test
    fun timeIncrements() {
        TimeController.reset()
        TimeController.update(TimeController.STEP)
        assertEquals(TimeController.STEP, TimeController.deltaTime)
        assertEquals(TimeController.STEP, TimeController.totalTime)
        TimeController.update(TimeController.STEP)
        assertEquals(TimeController.STEP, TimeController.deltaTime)
        assertEquals(TimeController.STEP * 2f, TimeController.totalTime)
    }

    @Test
    fun tickEventTriggers() {
        TimeController.reset()

        var ticked = false

        tickEvent { ticked = !ticked }

        TimeController.update(TimeController.STEP)
        assertEquals(true, ticked)
    }

    @Test
    fun tickEventTriggerAtIntervals() {
        TimeController.reset()

        var ticked = false

        tickEvent(3f, 1) {
            ticked = !ticked
        }

        TimeController.update(TimeController.STEP)
        assertEquals(true, ticked)
        TimeController.update(3f)
        assertEquals(false, ticked)
    }

    @Test
    fun tickEventRepeats() {
        TimeController.reset()

        var ticked = false

        tickEvent(0f, 2) {
            ticked = !ticked
        }

        TimeController.update(TimeController.STEP)
        assertEquals(true, ticked)
        TimeController.update(TimeController.STEP)
        assertEquals(false, ticked)
    }
}
