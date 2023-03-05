package kr.ac.kaist.itchy.gameObjects

import android.opengl.GLES20
import android.opengl.Matrix
import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.gameEngine.ShaderState
import kr.ac.kaist.itchy.managers.Managers
import kr.ac.kaist.itchy.renderObjects.IndicatorModel
import kr.ac.kaist.itchy.util.*
import java.util.*
import java.util.Vector

/** An indicator that indicates the location of target in game. */
class Indicator() : GameObject(IndicatorModel()) {
    var target: GameObject? = null
        private set
    val indicatorModel: IndicatorModel
        get() = mRenderObject as IndicatorModel

    constructor(target: GameObject) : this() {
        this.target = target
    }

    fun setTarget(gameObject: GameObject) {
        target = gameObject
    }

    /** Make this indicator Indicates target. */
    fun indicate() {
        if (target != null) {
            val player = Managers.game.player!!
            val camera = Managers.scene.currentScene!!.camera!!
            val posDelta = Vector3(camera.transform.up * 1.15f + camera.transform.forward * 1.5f)
            transform.moveTo(camera)
            transform.translate(posDelta)
            transform.setRotation(player)
            transform.lookAt(target!!)
        }
    }

    override fun draw(viewMatrix: FloatArray?, shaderStates: Vector<ShaderState>) {
        if (mRenderObject != null) {
            val scale = transform.scale
            val modelViewMatrix = getMVM(viewMatrix, transform.modelMatrix)
            val normalMatrix = FloatArray(16)
            Matrix.scaleM(modelViewMatrix, 0, scale.x, scale.y, scale.z)
            normalMatrix(normalMatrix, 0, modelViewMatrix, 0)

            val curSS = shaderStates[mRenderObject!!.shaderIndex]
            GLES20.glUseProgram(curSS.mProgram)
            GLES20.glUniformMatrix4fv(curSS.mModelViewMatrixHandle, 1, false, modelViewMatrix, 0)
            GLES20.glUniformMatrix4fv(curSS.mNormalMatrixHandle, 1, false, normalMatrix, 0)
            GLES20.glUniform3fv(curSS.mColorHandle, 1, mRenderObject!!.color, 0)

            val light1 = Vector3(transform.pos + (transform.up+transform.forward*0.7f)).toHomogeneousPoint()
            val eyeLight1 = getEyeCoordVec(viewMatrix, light1)
            val light2 = Vector3(transform.pos + (transform.down+transform.backward*0.7f)).toHomogeneousPoint()
            val eyeLight2 = getEyeCoordVec(viewMatrix, light2)

            GLES20.glUniform3f(curSS.mLightHandle, eyeLight1[0], eyeLight1[1], eyeLight1[2])
            GLES20.glUniform3f(curSS.mLight2Handle, eyeLight2[0], eyeLight2[1], eyeLight2[2])

            /** What is different from its superclass.
             * Depth testing is temporarily disabled to always show indicator */
            GLES20.glDisable(GLES20.GL_DEPTH_TEST)
            mRenderObject!!.draw(curSS)
            GLES20.glEnable(GLES20.GL_DEPTH_TEST)

            collisionObject!!.update(mRenderObject!!.vertices!!, transform)
        }
    }

    override fun initGameObject() {
        super.initGameObject()
        transform.setScale(0.1f, 0.06f, 0.1f)
    }
}