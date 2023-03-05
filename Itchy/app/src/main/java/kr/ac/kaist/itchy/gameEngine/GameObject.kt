package kr.ac.kaist.itchy.gameEngine

import android.opengl.GLES20
import android.opengl.Matrix
import kr.ac.kaist.itchy.collisionCheck.AABB
import kr.ac.kaist.itchy.collisionCheck.BoundingSphere
import kr.ac.kaist.itchy.collisionCheck.CollisionObject
import kr.ac.kaist.itchy.managers.Managers
import kr.ac.kaist.itchy.renderObjects.BulletModel
import kr.ac.kaist.itchy.renderObjects.MosquitoModel
import kr.ac.kaist.itchy.renderObjects.SphereModel
import kr.ac.kaist.itchy.util.*
import java.util.Vector

/** A Class representing a object in game. */
open class GameObject() {
    var id: UInt = 0U
        protected set
    open lateinit var transform: Transform
    var mRenderObject: RenderObject? = null
        private set
    var collisionObject: CollisionObject? = null

    constructor(renderObject: RenderObject) : this() {
        mRenderObject = renderObject
        //if it's a sphere -> Bounding sphere, else AABB
        collisionObject = if (mRenderObject is SphereModel || mRenderObject is BulletModel) {
            BoundingSphere(renderObject.vertices!!, transform)
        } else {
            AABB(renderObject.vertices!!, transform)
        }
    }

    constructor(renderObject: RenderObject, color: FloatArray?) : this() {
        mRenderObject = renderObject
        System.arraycopy(color!!, 0, mRenderObject!!.color, 0, 3)
        collisionObject = if (mRenderObject is SphereModel || mRenderObject is BulletModel) {
            BoundingSphere(renderObject.vertices!!, transform)
        } else {
            AABB(renderObject.vertices!!, transform)
        }
    }

    /** Call draw() function for mRenderObject. */
    open fun draw(viewMatrix: FloatArray?, shaderStates: Vector<ShaderState>) {
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
            mRenderObject!!.draw(curSS)
            collisionObject!!.update(mRenderObject!!.vertices!!, transform)
        }
    }

    protected open fun initGameObject() {
        id = Managers.obj.assignGameObjectId(this)
        transform = Transform(this)
    }

    init {
        initGameObject()
    }
}