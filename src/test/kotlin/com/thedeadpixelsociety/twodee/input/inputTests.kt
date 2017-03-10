package com.thedeadpixelsociety.twodee.input

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.backends.headless.HeadlessApplication
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration
import com.badlogic.gdx.utils.IntMap
import org.junit.Assert
import org.junit.Test

class InputTests {
    private val input = MockInput()

    init {
        HeadlessApplication(
                object : ApplicationAdapter() {},
                HeadlessApplicationConfiguration()
        )

        Gdx.input = input
    }

    @Test
    fun remapInput() {
        input.reset()

        val controller = DefaultActionController<Action>()
        controller.mapKey(Action.UP, Input.Keys.UP)

        Assert.assertEquals(false, controller.actionDown(Action.UP))
        input.simKey(Input.Keys.UP, true)
        Assert.assertEquals(true, controller.actionDown(Action.UP))

        controller.mapKey(Action.UP, Input.Keys.DOWN)

        Assert.assertEquals(false, controller.actionDown(Action.UP))
        input.simKey(Input.Keys.DOWN, true)
        Assert.assertEquals(true, controller.actionDown(Action.UP))
    }

    @Test
    fun pollMappedInput() {
        input.reset()

        val controller = DefaultActionController<Action>()
        controller.mapKey(Action.UP, Input.Keys.UP)
        controller.mapKey(Action.DOWN, Input.Keys.DOWN)
        controller.mapKey(Action.LEFT, Input.Keys.LEFT)
        controller.mapKey(Action.RIGHT, Input.Keys.RIGHT)
        controller.mapKey(Action.JUMP, Input.Keys.Z)
        controller.mapButton(Action.SHOOT, Input.Buttons.LEFT)

        Assert.assertEquals(false, controller.actionDown(Action.UP))
        input.simKey(Input.Keys.UP, true)
        Assert.assertEquals(true, controller.actionDown(Action.UP))

        Assert.assertEquals(false, controller.actionDown(Action.SHOOT))
        input.simButton(Input.Buttons.LEFT, true)
        Assert.assertEquals(true, controller.actionDown(Action.SHOOT))
    }

    @Test
    fun listenMappedInput() {
        input.reset()

        val controller = DefaultActionController<Action>()
        controller.mapKey(Action.UP, Input.Keys.UP)
        controller.mapKey(Action.DOWN, Input.Keys.DOWN)
        controller.mapKey(Action.LEFT, Input.Keys.LEFT)
        controller.mapKey(Action.RIGHT, Input.Keys.RIGHT)
        controller.mapKey(Action.JUMP, Input.Keys.Z)
        controller.mapButton(Action.SHOOT, Input.Buttons.LEFT)

        var listenerState = false

        controller.addListener { action, state ->
            listenerState = action == Action.UP && state
        }

        input.simKey(Input.Keys.UP, true)
        controller.update()

        Assert.assertEquals(true, listenerState)

        input.simKey(Input.Keys.UP, false)
        controller.update()

        Assert.assertEquals(false, listenerState)
    }

    class MockInput : Input {
        private val simKeys = IntMap<Boolean>()
        private val simButtons = IntMap<Boolean>()

        fun reset() {
            simKeys.clear()
            simButtons.clear()
        }

        fun simKey(key: Int, state: Boolean) {
            simKeys.put(key, state)
        }

        fun simButton(button: Int, state: Boolean) {
            simButtons.put(button, state)
        }

        override fun isButtonPressed(button: Int): Boolean {
            return simButtons.get(button, false)
        }

        override fun setCatchBackKey(catchBack: Boolean) {
        }

        override fun getGyroscopeZ(): Float {
            return 0f
        }

        override fun getGyroscopeX(): Float {
            return 0f
        }

        override fun getAccelerometerY(): Float {
            return 0f
        }

        override fun getInputProcessor(): InputProcessor? {
            return null
        }

        override fun getAccelerometerZ(): Float {
            return 0f
        }

        override fun getRotationMatrix(matrix: FloatArray?) {
        }

        override fun isTouched(): Boolean {
            return false
        }

        override fun isTouched(pointer: Int): Boolean {
            return false
        }

        override fun getCurrentEventTime(): Long {
            return 0L
        }

        override fun setInputProcessor(processor: InputProcessor?) {
        }

        override fun getDeltaY(): Int {
            return 0
        }

        override fun getDeltaY(pointer: Int): Int {
            return 0
        }

        override fun getRoll(): Float {
            return 0f
        }

        override fun isKeyPressed(key: Int): Boolean {
            return simKeys.get(key, false)
        }

        override fun isCatchMenuKey(): Boolean {
            return false
        }

        override fun getRotation(): Int {
            return 0
        }

        override fun justTouched(): Boolean {
            return false
        }

        override fun getNativeOrientation(): Input.Orientation {
            return Input.Orientation.Portrait
        }

        override fun cancelVibrate() {
        }

        override fun setCursorCatched(catched: Boolean) {
        }

        override fun getAzimuth(): Float {
            return 0f
        }

        override fun getTextInput(listener: Input.TextInputListener?, title: String?, text: String?, hint: String?) {
        }

        override fun getPitch(): Float {
            return 0f
        }

        override fun getY(): Int {
            return 0
        }

        override fun getY(pointer: Int): Int {
            return 0
        }

        override fun setCatchMenuKey(catchMenu: Boolean) {
        }

        override fun isCatchBackKey(): Boolean {
            return false
        }

        override fun getAccelerometerX(): Float {
            return 0f
        }

        override fun isKeyJustPressed(key: Int): Boolean {
            return false
        }

        override fun getGyroscopeY(): Float {
            return 0f
        }

        override fun setOnscreenKeyboardVisible(visible: Boolean) {
        }

        override fun getDeltaX(): Int {
            return 0
        }

        override fun getDeltaX(pointer: Int): Int {
            return 0
        }

        override fun vibrate(milliseconds: Int) {
        }

        override fun vibrate(pattern: LongArray?, repeat: Int) {
        }

        override fun setCursorPosition(x: Int, y: Int) {
        }

        override fun isCursorCatched(): Boolean {
            return false
        }

        override fun getX(): Int {
            return 0
        }

        override fun getX(pointer: Int): Int {
            return 0
        }

        override fun isPeripheralAvailable(peripheral: Input.Peripheral?): Boolean {
            return true
        }

    }

    enum class Action {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        JUMP,
        SHOOT
    }
}