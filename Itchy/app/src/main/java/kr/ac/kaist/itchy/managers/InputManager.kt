package kr.ac.kaist.itchy.managers

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.opengl.Matrix
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import kr.ac.kaist.itchy.util.Vector3
import kr.ac.kaist.itchy.util.clamp
import kr.ac.kaist.itchy.util.lerp
import kotlin.math.PI

/** A Manager class that manage user input. */
class InputManager {
    private var mPreviousX = 0f
    private var mPreviousY = 0f
    private var mInputRotationMatrix: FloatArray = FloatArray(16)
    private var sensitivity = 1f
    var isTouchingScreen = false
    var yRotation: Float = 0f
    var xRotation: Float = 0f
    var zRotation: Float = 0f

    fun clear() {
        mPreviousX = 0f
        mPreviousY = 0f
    }

    fun onTouchEvent(e: MotionEvent): Boolean {
        resetGyro()
        if (e.action == MotionEvent.ACTION_DOWN) {
            isTouchingScreen = true
        }
        if(e.action == MotionEvent.ACTION_UP){
            isTouchingScreen = false
        }
        return true
    }

    fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            xRotation -= sensitivity*event.values[1]
            yRotation += sensitivity*event.values[0]
            zRotation += sensitivity*event.values[2]

            xRotation = clamp(xRotation, -30f, 30f)
            yRotation = clamp(yRotation, -30f, 30f)
            zRotation = clamp(zRotation, -30f, 30f)
        }
    }

    fun changeSense(inp: Float){ // input -1 to 1
        sensitivity = 1.2f + inp*0.5f
    }

    fun resetGyro(){
        yRotation = 0f
        xRotation = 0f
        zRotation = 0f
    }
}