package kr.ac.kaist.itchy.scenes

import kr.ac.kaist.itchy.gameEngine.ShaderState
import kr.ac.kaist.itchy.gameObjects.Camera
import kr.ac.kaist.itchy.util.Constants
import java.util.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/** An Abstract class for all scenes. */
abstract class BaseScene {
    var sceneType = Constants.SceneType.Unknown
        protected set
    protected var mCamera: Camera? = null

    val camera: Camera?
        get() = mCamera

    open fun setCamera(width: Int, height: Int, curSS: ShaderState) {
        mCamera!!.setCamera(width, height, curSS)
    }

    open fun clear() {
        mCamera = null
    }

    abstract fun reset()
    abstract fun onUpdate(dt: Long)
    abstract fun drawScene(shaderStates: Vector<ShaderState>)
    abstract fun onSurfaceCreated(gl10: GL10, config: EGLConfig)
    abstract fun resetNeopjookObject()
}