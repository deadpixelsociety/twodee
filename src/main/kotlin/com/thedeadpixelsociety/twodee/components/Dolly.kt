package com.thedeadpixelsociety.twodee.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.SnapshotArray
import com.thedeadpixelsociety.twodee.components.dolly.actions.DollyAction

class Dolly(val camera: OrthographicCamera) : Component {
    val actions = Array<DollyAction>()
}