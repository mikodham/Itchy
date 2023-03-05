package kr.ac.kaist.itchy

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.opengl.GLSurfaceView
import android.view.MotionEvent
import kr.ac.kaist.itchy.managers.Managers
import kr.ac.kaist.itchy.util.Constants

/**
 * Created by sjjeon on 16. 9. 20.
 *  * Reissued for Itchy on 27. 9. 22
 */
class MyGLSurfaceView(context: Context?) : GLSurfaceView(context), SensorEventListener {
    private val mRenderer: MyGLRenderer
//    var mode = 0
    private lateinit var sensorManager: SensorManager
    private var mGyroscopeSensor: Sensor? = null

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        return Managers.input.onSensorChanged(event)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        return Managers.input.onTouchEvent(e)
    }

    fun resetRenderer() {
        mRenderer.reset()
        requestRender()
    }

    init {

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2)

        // Set the Renderer for drawing on the GLSurfaceView
        Managers.scene.loadScene(Constants.SceneType.Game) // Scene should be loaded before setting renderer
        mRenderer = MyGLRenderer()
        setRenderer(mRenderer)

        // Render the view only when there is a change in the drawing data
        // renderMode = RENDERMODE_WHEN_DIRTY
        // Render the view continuously
        renderMode = RENDERMODE_CONTINUOUSLY

        // Setup sensor manager to use gyroscope
        sensorManager = context!!.getSystemService(Activity.SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()
        // Setup gyroscope sensor when app runs
        mGyroscopeSensor = sensorManager!!.getDefaultSensor(
                Sensor.TYPE_GYROSCOPE
        )
        mGyroscopeSensor!!.also { sensor ->
            sensorManager!!.registerListener(this,
                    sensor, SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    override fun onPause() {
        super.onPause()
        // Pause gyroscope sensor when app pause
        sensorManager.unregisterListener(this)
    }
}