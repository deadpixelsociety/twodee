package com.thedeadpixelsociety.twodee.input

import com.thedeadpixelsociety.twodee.Predicate

typealias ActionListener<T> = (T, Boolean) -> Unit

/**
 * Maps actions to input keys and buttons.
 * @param T The action type. Usually an enum but strings or ints would work as well.
 */
abstract class ActionController<T> {
    /**
     * Maps the specified action to the specified key.
     * @param action The action to map.
     * @param key The key to map the action to.
     * @param predicate An optional predicate that must return true for the action to be 'down'.
     */
    abstract fun mapKey(action: T, key: Int, predicate: Predicate<T>? = null)

    /**
     * Maps the specified action to the specified button.
     * @param action The action to map.
     * @param button The button to map the action to.
     * @param predicate An optional predicate that must return true for the action to be 'down'.
     */
    abstract fun mapButton(action: T, button: Int, predicate: Predicate<T>? = null)

    /**
     * Maps the specified action to the specified pointer touch.
     * @param action The action to map.
     * @param pointer The pointer to map the action to.
     * @param predicate An optional predicate that must return true for the action to be 'down'.
     */
    abstract fun mapTouch(action: T, pointer: Int, predicate: Predicate<T>? = null)

    /**
     * Checks if the specified action is in a down (pressed) state.
     * @param action The action to check.
     * @return true if the action is down.
     */
    abstract fun actionDown(action: T): Boolean

    /**
     * Checks if the specified action is in an up (unpressed) state.
     * @param action The action to check.
     * @return true if the action is up.
     */
    abstract fun actionUp(action: T): Boolean

    /**
     * Updates the mapper so that action events can be fired.
     */
    abstract fun update()

    /**
     * Adds the specified action listener.
     * @param listener The listener.
     */
    abstract fun addListener(listener: ActionListener<T>)

    /**
     * Removes the specified action listener.
     * @param listener The listener.
     */
    abstract fun removeListener(listener: ActionListener<T>)
}