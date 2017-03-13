package com.thedeadpixelsociety.twodee.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.IntMap
import com.badlogic.gdx.utils.ObjectMap
import com.thedeadpixelsociety.twodee.Predicate
import com.thedeadpixelsociety.twodee.gdxArray

class DefaultActionController<T> : ActionController<T>() {
    private val keyMap = IntMap<T>()
    private val buttonMap = IntMap<Array<T>>()
    private val reverseButtonMap = ObjectMap<T, Int>()
    private val touchMap = IntMap<Array<T>>()
    private val reverseTouchMap = ObjectMap<T, Int>()
    private val stateMap = ObjectMap<T, Boolean>()
    private val predicateMap = ObjectMap<T, Predicate<T>>()
    private val listeners = gdxArray<ActionListener<T>>()

    override fun mapKey(action: T, key: Int, predicate: Predicate<T>?) {
        val existing = keyMap.findKey(action, false, -1)
        if (existing != -1) {
            keyMap.remove(key)
            stateMap.remove(action)
            predicateMap.remove(action)
        }

        keyMap.put(key, action)
        stateMap.put(action, false)
        if (predicate != null) predicateMap.put(action, predicate)
    }

    override fun mapButton(action: T, button: Int, predicate: Predicate<T>?) {
        val actions = getActions(buttonMap, button)
        if (actions.contains(action)) {
            actions.removeValue(action, false)
            stateMap.remove(action)
            predicateMap.remove(action)
            reverseButtonMap.remove(action)
        }

        actions.add(action)
        stateMap.put(action, false)
        reverseButtonMap.put(action, button)
        if (predicate != null) predicateMap.put(action, predicate)
    }

    override fun mapTouch(action: T, pointer: Int, predicate: Predicate<T>?) {
        val actions = getActions(buttonMap, pointer)
        if (actions.contains(action)) {
            actions.removeValue(action, false)
            stateMap.remove(action)
            predicateMap.remove(action)
            reverseTouchMap.remove(action)
        }

        actions.add(action)
        stateMap.put(action, false)
        reverseTouchMap.put(action, pointer)
        if (predicate != null) predicateMap.put(action, predicate)
    }

    override fun actionDown(action: T): Boolean {
        val predicate = predicateMap.get(action, null)
        val key = keyMap.findKey(action, false, -1)
        if (key != -1) return Gdx.input.isKeyPressed(key) && predicate?.invoke(action) ?: true
        val button = reverseButtonMap.get(action, -1)
        if (button != -1) return Gdx.input.isButtonPressed(button) && predicate?.invoke(action) ?: true
        val pointer = reverseTouchMap.get(action, -1)
        if (pointer != -1) return Gdx.input.isTouched(pointer) && predicate?.invoke(action) ?: true
        return false
    }

    override fun actionUp(action: T): Boolean {
        return !actionDown(action)
    }

    private fun getActions(map: IntMap<Array<T>>, key: Int): Array<T> {
        var actions = map.get(key)
        if (actions == null) {
            actions = Array()
            map.put(key, actions)
        }

        return actions
    }

    override fun update() {
        stateMap.forEach {
            val action = it.key
            val lastState = it.value
            val state = actionDown(action)
            if (state != lastState) {
                listeners.forEach { it.invoke(action, state) }
                stateMap.put(action, state)
            }
        }
    }

    override fun addListener(listener: ActionListener<T>) {
        listeners.add(listener)
    }

    override fun removeListener(listener: ActionListener<T>) {
        listeners.removeValue(listener, true)
    }
}