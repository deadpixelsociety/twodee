package com.thedeadpixelsociety.twodee

/**
 * Controller for global duration keeping. Tracks the current update delta duration and the accumulated total duration. This
 * controller also accepts tick events that can be triggered at intervals and repeated.
 * @see TickEvent
 */
object TimeController {
    const val INFINITE = -1

    private val events = gdxArray<TickEvent>()
    private val addEventQueue = gdxArray<TickEvent>()
    private val removeEventQueue = gdxArray<TickEvent>()

    /**
     * The current update delta duration, in seconds.
     */
    var deltaTime = 0f
        get
        private set

    /**
     * The total accumulated duration, in seconds.
     */
    var totalTime = 0f
        get
        private set

    /**
     * Resets the delta duration, total duration and clears all tick events.
     */
    fun reset() {
        deltaTime = 0f
        totalTime = 0f
        events.clear()
    }

    /**
     * Updates the controller with the current delta duration.
     * @param deltaTime The current delta duration, in seconds.
     */
    fun update(deltaTime: Float) {
        addEventQueue.forEach { events.add(it) }
        removeEventQueue.forEach { events.removeValue(it, true) }

        addEventQueue.clear()
        removeEventQueue.clear()

        runEvents()

        this.deltaTime = deltaTime
        totalTime += deltaTime
    }

    /**
     * Registers a tick event.
     * @param event The tick event.
     * @see TickEvent
     */
    fun register(event: TickEvent) {
        addEventQueue.add(event)
    }

    /**
     * Unregisters a tick event.
     * @param event The tick event.
     */
    fun unregister(event: TickEvent) {
        removeEventQueue.add(event)
    }

    private fun runEvents() {
        events.toList().forEach {
            if (it.cancel) {
                events.removeValue(it, true)
            } else {
                if (it.delayExpired() && it.intervalReady()) {
                    it.func()
                    it.lastTime = totalTime
                    it.count++
                }

                if (!it.delayExpired()) it.delayTimer += deltaTime
                if (it.expired()) events.removeValue(it, true)
            }
        }
    }

    /**
     * An event that will be fired at each tick up to [repeat] number of times. At each tick [func] will be
     * invoked. At least one invocation of [func] will always occur.
     * @param delay The initial delay before the event happens.
     * @param interval The interval between ticks.
     * @param repeat The number of times to repeat the event after the first invocation.
     * @param func The function to perform at each tick.
     */
    open class TickEvent(
            val delay: Float = 0f,
            val interval: Float = 0f,
            val repeat: Int = 0,
            val func: Func<Unit>
    ) {
        /**
         * Cancels the event if true.
         */
        var cancel = false

        internal var delayTimer = 0f
        internal var lastTime = 0f
        internal var count = 0

        internal fun delayExpired() = delayTimer >= delay
        internal fun intervalReady() = lastTime == 0f || (totalTime() - lastTime) >= interval
        internal fun expired() = repeat != INFINITE && delayExpired() && count > repeat
    }
}