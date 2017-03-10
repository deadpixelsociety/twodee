package com.thedeadpixelsociety.twodee.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.IntMap
import com.badlogic.gdx.utils.ObjectMap
import com.thedeadpixelsociety.twodee.gdxArray

class DefaultActionController<T> : ActionController<T> {
    private val keyMap = IntMap<T>()
    private val buttonMap = IntMap<T>()
    private val listeners = gdxArray<ActionListener<T>>()
    private val actionStateMap = ObjectMap<T, Boolean>()

    override fun mapKey(action: T, key: Int) {
        val existingKey = keyMap.findKey(action, false, -1)
        if (existingKey != -1) keyMap.remove(existingKey)
        keyMap.put(key, action)
        actionStateMap.put(action, false)
    }

    override fun mapButton(action: T, button: Int) {
        val existingKey = buttonMap.findKey(action, false, -1)
        if (existingKey != -1) buttonMap.remove(existingKey)
        buttonMap.put(button, action)
        actionStateMap.put(action, false)
    }

    override fun actionDown(action: T): Boolean {
        val key = keyMap.findKey(action, false, -1)
        if (key != -1) return Gdx.input.isKeyPressed(key)
        val button = buttonMap.findKey(action, false, -1)
        if (button != -1) return Gdx.input.isButtonPressed(button)
        return false
    }

    override fun actionUp(action: T): Boolean {
        return !actionDown(action)
    }

    override fun update() {
        actionStateMap.forEach {
            val action = it.key
            val lastState = it.value
            val state = actionDown(action)
            if (state != lastState) {
                listeners.forEach { it.invoke(action, state) }
                actionStateMap.put(action, state)
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