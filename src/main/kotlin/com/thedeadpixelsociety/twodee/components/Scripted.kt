package com.thedeadpixelsociety.twodee.components

import com.thedeadpixelsociety.twodee.gdxArray
import com.thedeadpixelsociety.twodee.scripts.Script

class Scripted : PoolableComponent() {
    val scripts = gdxArray<Script>()

    override fun reset() {
        scripts.clear()
    }
}