package com.thedeadpixelsociety.twodee.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.IntMap
import com.badlogic.gdx.utils.ObjectMap
import com.thedeadpixelsociety.twodee.Predicate
import com.thedeadpixelsociety.twodee.gdxArray

class DefaultActionController<T> : ActionController<T>() {
    private val keyMap = IntMap<T>()
    private val buttonMap = IntMap<T>()
    private val touchMap = IntMap<T>()
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
        val existing = buttonMap.findKey(action, false, -1)
        if (existing != -1) {
            buttonMap.remove(button)
            stateMap.remove(action)
            predicateMap.remove(action)
        }

        buttonMap.put(button, action)
        stateMap.put(action, false)
        if (predicate != null) predicateMap.put(action, predicate)
    }

    override fun mapTouch(action: T, pointer: Int, predicate: Predicate<T>?) {
        val existing = touchMap.findKey(action, false, -1)
        if (existing != -1) {
            touchMap.remove(pointer)
            stateMap.remove(action)
            predicateMap.remove(action)
        }

        touchMap.put(pointer, action)
        stateMap.put(action, false)
        if (predicate != null) predicateMap.put(action, predicate)
    }

    override fun actionDown(action: T): Boolean {
        val predicate = predicateMap.get(action, null)
        val key = keyMap.findKey(action, false, -1)
        if (key != -1) return Gdx.input.isKeyPressed(key) && predicate?.invoke(action) ?: true
        val button = buttonMap.findKey(action, false, -1)
        if (button != -1) return Gdx.input.isButtonPressed(button) && predicate?.invoke(action) ?: true
        val pointer = touchMap.findKey(action, false, -1)
        if (pointer != -1) return Gdx.input.isTouched(pointer) && predicate?.invoke(action) ?: true
        return false
    }

    override fun actionUp(action: T): Boolean {
        return !actionDown(action)
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