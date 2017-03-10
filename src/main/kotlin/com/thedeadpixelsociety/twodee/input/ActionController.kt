package com.thedeadpixelsociety.twodee.input

typealias ActionListener<T> = (T, Boolean) -> Unit

/**
 * Maps actions to input keys and buttons.
 * @param T The action type. Usually an enum but strings or ints would work as well.
 */
interface ActionController<T> {
    /**
     * Maps the specified action to the specified key.
     * @param action The action to map.
     * @param key The key to map the action to.
     */
    fun mapKey(action: T, key: Int)

    /**
     * Maps the specified action to the specified button.
     * @param action The action to map.
     * @param button The button to map the action to.
     */
    fun mapButton(action: T, button: Int)

    /**
     * Checks if the specified action is in a down (pressed) state.
     * @param action The action to check.
     * @return true if the action is down.
     */
    fun actionDown(action: T): Boolean

    /**
     * Checks if the specified action is in an up (unpressed) state.
     * @param action The action to check.
     * @return true if the action is up.
     */
    fun actionUp(action: T): Boolean

    /**
     * Updates the mapper so that action events can be fired.
     */
    fun update()

    /**
     * Adds the specified action listener.
     * @param listener The listener.
     */
    fun addListener(listener: ActionListener<T>)

    /**
     * Removes the specified action listener.
     * @param listener The listener.
     */
    fun removeListener(listener: ActionListener<T>)
}