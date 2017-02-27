package com.thedeadpixelsociety.twodee

/**
 * Controller for global time keeping. Tracks the current update delta time and the accumulated total time. This
 * controller also accepts tick events that can be triggered at intervals and repeated.
 * @see TickEvent
 */
object TimeController {
    const val MAX_DELTA_TIME = .01666666666666666666666666666667f
    const val STEP = .01f
    const val REPEAT = -1

    private val events = gdxArray<TickEvent>()
    private var accumulator = 0f


    /**
     * The current update delta time, in seconds.
     */
    var deltaTime = 0f
        get
        private set

    /**
     * The total accumulated time, in seconds.
     */
    var totalTime = 0f
        get
        private set

    /**
     * Resets the delta time, total time and clears all tick events.
     */
    fun reset() {
        deltaTime = 0f
        totalTime = 0f
        events.clear()
    }

    /**
     * Updates the controller with the current delta time.
     * @param deltaTime The current delta time, in seconds.
     */
    fun update(deltaTime: Float) {
        accumulator += deltaTime
        while (accumulator >= STEP) {
            runEvents()

            this.deltaTime = STEP
            this.totalTime += STEP
            accumulator -= STEP
        }
    }

    /**
     * Registers a tick event.
     * @param event The tick event.
     * @see TickEvent
     */
    fun register(event: TickEvent) {
        events.add(event)
    }

    private fun runEvents() {
        events.toList().forEach {
            if (it.interval == 0f || it.lastTime == 0f || (totalTime - it.lastTime) >= it.interval) {
                it.func()
                it.lastTime = totalTime
                it.count++
            }

            if (it.repeat != REPEAT && it.count > it.repeat) events.removeValue(it, true)
        }
    }

    /**
     * An event that will be fired at each tick up to [repeat] number of times. At each tick [func] will be
     * invoked. At least one invocation of [func] will always occur.
     * @param interval The interval between ticks.
     * @param repeat The number of times to repeat the event after the first invocation.
     * @param func The function to perform at each tick.
     */
    open class TickEvent(val interval: Float, val repeat: Int, val func: Func<Unit>) {
        internal var lastTime = 0f
        internal var count = 0
    }
}