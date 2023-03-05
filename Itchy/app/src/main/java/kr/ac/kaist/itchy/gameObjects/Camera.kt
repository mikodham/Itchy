package kr.ac.kaist.itchy.gameObjects

import android.opengl.GLES20
import android.opengl.Matrix
import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.gameEngine.ShaderState
import kr.ac.kaist.itchy.util.Vector3
import java.util.*

/** Wrapper Class for Camera. It has nothing to draw (no RenderObject) */
class Camera: GameObject() {
    var viewWidth = 0
        private set
    var viewHeight = 0
        private set
    val ratio: Float
        get() = viewWidth.toFloat() / viewHeight

    ////////////////////////// FOR FRUSTUM //////////////////////////
    val left: Float
        get() = -right
    val right: Float
        get() = ratio * top
    val bottom = -0.1f
    val top = 0.1f
    val near = 0.1f
    val far = 20.0f
    ////////////////////////////////////////////////////////////////

    // ! getter of viewMatrix and eyeMatrix is called per frame.
    // It might lower performance of our program(game).
    /** View matrix of this Camera.
     *
     * ! CAUTION ! : Manipulating viewMatrix doesn't affect to
     * actual Transform of Camera. If you want to manipulate Transform of Camera,
     * you need to call methods of Camera and Camera.transform */
    val viewMatrix: FloatArray
        get() {
            val m = FloatArray(16)
            Matrix.invertM(m, 0, eyeMatrix, 0)
            return m
        }
    /** Eye matrix of this Camera.
     *
     * ! CAUTION ! : Manipulating eyeMatrix doesn't affect to
     * actual Transform of Camera. If you want to manipulate Transform of Camera,
     * you need to call methods of Camera and Camera.transform */
    val eyeMatrix: FloatArray
        get() = transform.modelMatrix
    /** If you want change value of projection matrix of Camera,
     * you should change the value of this,
     * by defining/calling public method of Camera. */
    private val _projMatrix = FloatArray(16)
    /** Projection matrix of this Camera.
     *
     * ! CAUTION ! : Manipulating projMatrix doesn't change
     * real value of the projection matrix of Camera.
     * You should change the value of the projection matrix,
     * by defining/calling public method of Camera. */
    val projMatrix: FloatArray
        get() {
            val m = FloatArray(16)
            System.arraycopy(_projMatrix, 0, m, 0, 16)
            return m
        }

    private val temp = FloatArray(16)

    /** Set Camera projMatrix using given viewWidth and viewHeight */
    fun setCamera(width: Int, height: Int, curSS: ShaderState) {
        viewWidth = width
        viewHeight = height

        // Create a new perspective projection matrix. The height will stay the same
        // while the width will vary as per aspect ratio.
        Matrix.frustumM(_projMatrix, 0, left, right, bottom, top, near, far)
        GLES20.glUniformMatrix4fv(curSS.mProjMatrixHandle,1, false, projMatrix, 0)
    }

    /** Change Camera viewMatrix to look at input position */
    fun setLookAt(eyePos: Vector3, lookPoint: Vector3, upVec: Vector3) {
        transform.setLookAt(eyePos, lookPoint, upVec)
    }

    /** Set Camera Transform so that model matrix of Camera become modelMatrix */
    fun setByModelMatrix(modelMatrix: FloatArray) {
        setByEyeMatrix(modelMatrix)
    }

    /** Set Camera Transform so that eye matrix of Camera become eyeMatrix */
    fun setByEyeMatrix(eyeMatrix: FloatArray) {
        transform.setByModelMatrix(eyeMatrix)
    }

    /** Set Camera Transform so that view matrix of Camera become viewMatrix */
    fun setByViewMatrix(viewMatrix: FloatArray) {
        Matrix.invertM(temp, 0, viewMatrix, 0)
        setByEyeMatrix(temp)
    }

    /** Do nothing since a Camera has nothing to draw. */
    override fun draw(viewMatrix: FloatArray?, shaderStates: Vector<ShaderState>) {}
}