package kr.ac.kaist.itchy.gameEngine

import kr.ac.kaist.itchy.util.*
import android.opengl.Matrix
import kr.ac.kaist.itchy.gameObjects.RootGameObject
import kr.ac.kaist.itchy.managers.Managers
import java.util.Vector

/** The class containing spatial information (position, quaternion, scale) of object containing it. */
class Transform {
    var gameObject: GameObject
    var parent: Transform? = null
    private val _children = Vector<Transform>(6, 6)
    val children: Vector<Transform>
        get() = Vector<Transform>(_children)
    val numChild: Int
        get() = _children.size

    /** 3D local Position of object containing this Transform in parent's local coordinate.
     * If you want change value of the local position of Transform,
     * you should change the value of this,
     * by defining/calling public method of Transform. */
    val localPos = Vector3()
    /** 3D world Position of object containing this Transform
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform  */
    val pos: Vector3
        get() {
            if (parent == null)
                return Vector3(localPos)
            else {
                val v = localPos.toHomogeneousPoint().toFloatArray()
                val res = FloatArray(4)
                Matrix.multiplyMV(res, 0, parent!!.rotationMatrix, 0, v, 0)
                return Vector3(parent!!.pos + Vector3(res))
            }
        }
    /** Quaternion representing rotation of the transform
     * relative to the transform rotation of the parent.
     * If you want change value of the rotation of Transform,
     * you should change the value of this,
     * by defining/calling public method of Transform.*/
    private val _localQuat = Quaternion()
    val localQuat: Quaternion
        get() = Quaternion(_localQuat)
    /** Quaternion representing rotation of object containing this Transform
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform */
    val quat: Quaternion
        get() {
            return if (parent == null)
                Quaternion(_localQuat)
            else
                Quaternion(parent!!.quat).multiply(_localQuat)
        }
    /** A Matrix representing rotation of the transform
     * relative to the transform rotation of the parent.
     * If you want change value of the rotation of Transform,
     * you should change the value of this,
     * by defining/calling public method of Transform. */
    private val _localRotMatrix = FloatArray(16)
    val localRotMatrix: FloatArray
        get() {
            val m = FloatArray(16)
            System.arraycopy(_localRotMatrix, 0, m, 0, 16)
            return m
        }
    /** A Matrix representing rotation of object containing this Transform
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform */
    val rotationMatrix: FloatArray
        get() {
            return if (parent == null)
                localRotMatrix
            else
                Quaternion.quatToMat(quat)
        }
    /** Scale vector of object containing this Transform */
    val localScale = Vector3(1f, 1f, 1f)
    val scale: Vector3
        get() {
            return if (parent == null)
                localScale
            else
                Vector3(
                    parent!!.scale.x * localScale.x,
                    parent!!.scale.y * localScale.y,
                    parent!!.scale.z * localScale.z
                )
        }

    private val temp = FloatArray(16)
    ////////////////////// CONSTRUCTORS //////////////////////
    constructor(rootGameObject: RootGameObject) {
        val mat = Quaternion.quatToMat(_localQuat)
        System.arraycopy(mat, 0, _localRotMatrix, 0, 16)
        this.gameObject = rootGameObject
        this.parent = null
    }

    constructor(gameObject: GameObject) {
        val mat = Quaternion.quatToMat(_localQuat)
        System.arraycopy(mat, 0, _localRotMatrix, 0, 16)
        this.gameObject = gameObject
        this.parent = Managers.obj.rootGameObject.transform
    }

    constructor(gameObject: GameObject, parentTr: Transform) {
        val mat = Quaternion.quatToMat(_localQuat)
        System.arraycopy(mat, 0, _localRotMatrix, 0, 16)
        this.gameObject = gameObject
        this.parent = parentTr
    }
    /////////////////////////////////////////////////////////

    /////////////////// HANDLING CHILDREN ///////////////////
    fun addChild(child: Transform) {
        child.parent?.removeChild(child)
        _children.add(child)
        child.parent = this
        child.moveToLocal(Vector3(child.localPos-localPos))
    }

    fun removeChild(child: Transform) {
        _children.remove(child)
        child.parent = Managers.obj.rootGameObject.transform
        child.moveToLocal(Vector3(child.localPos+localPos))
    }

    fun getChild(i: Int): Transform {
        return _children[i]
    }
    /////////////////////////////////////////////////////////

    override fun toString(): String {
        return "pos: " + pos.toString() + "quat: " + _localQuat.toString()
    }

    /** Set scale vector as (s, s, s) */
    fun setScale(s: Float) {
        setScale(s, s, s)
    }

    /** Set scale vector as (x, y, z) */
    fun setScale(x: Float, y: Float, z: Float) {
        localScale.x = x
        localScale.y = y
        localScale.z = z
    }

    /** Set scale vector as (vec.x, vec.y, vec.z) */
    fun setScale(vec: Vector3) {
        localScale.x = vec[0]
        localScale.y = vec[1]
        localScale.z = vec[2]
    }

    /** Set scale vector as (vec[0], vec[1], vec[2]) */
    fun setScale(vec: FloatArray) {
        localScale.x = vec[0]
        localScale.y = vec[1]
        localScale.z = vec[2]
    }

    /** Set position and rotation using input modelMatrix */
    fun setByModelMatrix(modelMatrix: FloatArray) {
        moveToMat(modelMatrix)
        setRotation(modelMatrix)
    }

    //////////////////////// GETTERS ////////////////////////
    /** Model matrix of this object
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform */
    val modelMatrix: FloatArray
        get() {
            val ret = FloatArray(16)
            Matrix.multiplyMM(ret, 0, translationMatrix, 0, rotationMatrix, 0)
            return ret
        }
    /** Translation matrix of this object
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform */
    val translationMatrix: FloatArray
        get() {
            val ret = FloatArray(16)
            val posArr = pos.toHomogeneousPoint().toFloatArray()
            Matrix.setIdentityM(ret, 0)
            System.arraycopy(posArr, 0, ret, 12, 4)
            return ret
        }
    /** Forward direction of this object
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform */
    val forward: Vector3
        get() = getForwardVec(modelMatrix)
    /** Backward direction of this object
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform */
    val backward: Vector3
        get() = getBackwardVec(modelMatrix)
    /** Upward(head) direction of this object
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform */
    val up: Vector3
        get() = getUpVec(modelMatrix)
    /** Downward(feet) direction of this object
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform*/
    val down: Vector3
        get() = getDownVec(modelMatrix)
    /** Left direction of this object
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform*/
    val left: Vector3
        get() = getLeftVec(modelMatrix)
    /** Right direction of this object
     *
     * ! CAUTION ! : Manipulating this doesn't change actual Transform.
     * If you want to manipulate actual Transform,
     * you need to call methods of Transform*/
    val right: Vector3
        get() = getRightVec(modelMatrix)
    /////////////////////////////////////////////////////////

    /////////////// FUNCTIONS FOR TRANSLATION ///////////////
    /** Move this GameObject to the given point (world coordinate). Input array should be affine Matrix */
    fun moveToMat(affine: FloatArray) {
        moveTo(affine[12], affine[13], affine[14])
    }

    /** Move this GameObject to the Transform of the given GameObject (world coordinate) */
    fun moveTo(gameObject: GameObject) {
        moveTo(gameObject.transform.pos)
    }

    /** Move this GameObject to the given Transform (world coordinate) */
    fun moveTo(tr: Transform) {
        moveTo(tr.pos)
    }

    /** Move this GameObject to the given point (world coordinate) */
    fun moveTo(vec: Vector3) {
        moveTo(vec[0], vec[1], vec[2])
    }

    /** Move this GameObject to the given point (world coordinate) */
    fun moveTo(vec: FloatArray) {
        moveTo(vec[0], vec[1], vec[2])
    }

    /** Move this GameObject to the given point (world coordinate) */
    fun moveTo(x: Float, y: Float, z: Float) {
        if (parent == null) {
            localPos.x = x
            localPos.y = y
            localPos.z = z
        } else {
            val tp = parent!!.pos.toHomogeneousPoint().toFloatArray()
            val invRTp = FloatArray(4)
            Matrix.invertM(temp, 0, parent!!.rotationMatrix, 0)
            Matrix.multiplyMV(invRTp, 0, temp, 0, tp, 0)
            // translation: -r^(-1)t of parent
            localPos.x = x - invRTp[0]
            localPos.y = y - invRTp[1]
            localPos.z = z - invRTp[2]
        }
    }

    /** Move this GameObject to the given point (local coordinate) */
    fun moveToLocal(vec: Vector3) {
        moveToLocal(vec[0], vec[1], vec[2])
    }

    /** Move this GameObject to the given point (local coordinate) */
    fun moveToLocal(vec: FloatArray) {
        moveToLocal(vec[0], vec[1], vec[2])
    }

    /** Move this GameObject to the given point (local coordinate) */
    fun moveToLocal(x: Float, y: Float, z: Float) {
        localPos.x = x
        localPos.y = y
        localPos.z = z
    }

    /** Translate this GameObject given amount.
     * localPos of this GameObject become (x+dx, y+dy, z+dz) */
    fun translate(deltaVec: Vector3) {
        translate(deltaVec[0], deltaVec[1], deltaVec[2])
    }

    /** Translate this GameObject given amount.
     * localPos of this GameObject become (x+dx, y+dy, z+dz) */
    fun translate(deltaVec: FloatArray) {
        translate(deltaVec[0], deltaVec[1], deltaVec[2])
    }

    /** Translate this GameObject given amount.
     * localPos of this GameObject become (x+dx, y+dy, z+dz) */
    fun translate(dx: Float, dy:Float, dz:Float) {
        localPos.add(dx, dy, dz)
    }

    /** Move object along dir direction given length
     * dir should be a normalized vector. */
    fun goAlongDir(length: Float, dir: Vector3) {
        translate(Vector3(dir * length))
    }

    /** Move object along forward direction given length */
    fun goForward(length: Float) {
        goAlongDir(length, forward)
    }

    /** Move object along backward direction given length */
    fun goBack(length: Float) {
        goAlongDir(length, backward)
    }

    /** Move object along upward direction given length */
    fun goUp(length: Float) {
        goAlongDir(length, up)
    }

    /** Move object along downward direction given length */
    fun goDown(length: Float) {
        goAlongDir(length, down)
    }

    /** Move object along left direction given length */
    fun goLeft(length: Float) {
        goAlongDir(length, left)
    }

    /** Move object along right direction given length */
    fun goRight(length: Float) {
        goAlongDir(length, right)
    }
    /////////////////////////////////////////////////////////

    //////////////// FUNCTIONS FOR ROTATION /////////////////
    /** Set the rotation of this Transform according to input GameObject */
    fun setRotation(gameObject: GameObject) {
        setRotation(gameObject.transform.rotationMatrix)
    }

    /** Set the rotation of this Transform according to input Transform */
    fun setRotation(tr: Transform) {
        setRotation(tr.rotationMatrix)
    }

    /** Set the rotation of this Transform according to input affine Matrix */
    fun setRotation(affine: FloatArray) {
        if (parent == null) {
            setLocalRotation(affine)
        } else {
            val res = FloatArray(16)
            Matrix.invertM(temp, 0, parent!!.rotationMatrix,0)
            Matrix.multiplyMM(res, 0, affine, 0, temp, 0)
            setLocalRotation(res)
        }
    }

    /** Set the rotation of this Transform according to input Quaternion */
    fun setRotation(q: Quaternion) {
        if (parent == null) {
            setLocalRotation(q)
        } else {
            val invParentQ = Quaternion.inv(parent!!.quat)
            setLocalRotation(q.multiply(invParentQ))
        }
    }

    /** Set the local rotation of this Transform according to input affine Matrix */
    fun setLocalRotation(affine: FloatArray) {
        System.arraycopy(linFact(affine), 0, _localRotMatrix, 0, 16)
        val q = Quaternion.matToQuat(affine)
        _localQuat.setValues(Quaternion.normalize(q))
    }

    /** Set the local rotation of this Transform according to input Quaternion */
    fun setLocalRotation(q: Quaternion) {
        _localQuat.setValues(Quaternion.normalize(q))
        val mat = Quaternion.quatToMat(q)
        System.arraycopy(mat, 0, _localRotMatrix, 0, 16)
    }

    /** Rotate 'a' degree along with the axis (x, y, z) */
    fun rotate(a: Float, x: Float, y: Float, z: Float) {
        Matrix.rotateM(_localRotMatrix, 0, a, x, y, z)
        setLocalRotation(_localRotMatrix)
    }

    /** Rotate 'a' degree along with the axis (x, y, z) */
    fun rotate(a: Float, axis: Vector3) {
        rotate(a, axis[0], axis[1], axis[2])
    }

    /** Rotate 'a' degree along with the axis (x, y, z) */
    fun rotate(a: Float, axis: FloatArray) {
        rotate(a, axis[0], axis[1], axis[2])
    }

    /** Rotate a object so that forward direction of object points "gameObject" */
    fun lookAt(gameObject: GameObject) {
        lookAt(gameObject.transform.pos)
    }

    /** Rotate a object so that forward direction of object points "tr" */
    fun lookAt(tr: Transform) {
        lookAt(tr.pos)
    }

    /** Rotate a object so that forward direction of object points "p" */
    fun lookAt(p: Vector3) {
        setLookAt(p, up)
    }

    /** Rotate a object so that forward direction of object points a point "(x, y, z)" */
    fun lookAt(x: Float, y: Float, z: Float) {
        lookAt(Vector3(x, y, z))
    }

    /** Set position and rotation of the Transform
     *  so that this GameObject looks at lookPoint, at eyePos,
     *  while it's upward vector is upVec. */
    fun setLookAt(eyePos: Vector3, lookPoint: Vector3, upVec: Vector3) {
        moveTo(eyePos)
        setLookAt(lookPoint, upVec)
    }

    /** Set position and rotation of the Transform
     *  so that this GameObject looks at lookPoint,
     *  while it's upward vector is upVec. */
    fun setLookAt(lookPoint: Vector3, upVec: Vector3) {
        val m = FloatArray(16)
        setLookAtM(temp, 0, pos, lookPoint, upVec)
        Matrix.invertM(m, 0, temp, 0)
        setByModelMatrix(m)
    }

    /** Rotate a object so that forward direction of object becomes "dir" */
    fun setForwardDir(dir: Vector3) {
        lookAt(Vector3(pos + dir))
    }

    /** Rotate a object so that upward direction of object becomes "dir" */
    fun setUpDir(dir: Vector3) {
        setLookAt(pos, dir)
    }

    fun turnLeft() {
        setForwardDir(left)
    }

    fun turnRight() {
        setForwardDir(right)
    }

    /////////////////////////////////////////////////////////
}