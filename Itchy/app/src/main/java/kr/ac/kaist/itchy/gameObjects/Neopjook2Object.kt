package kr.ac.kaist.itchy.gameObjects

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.managers.Managers
import kr.ac.kaist.itchy.renderObjects.*
import kr.ac.kaist.itchy.util.*
import kr.ac.kaist.itchy.util.Constants.NEOPJOOK_Y_COORD
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Neopjook2Object: GameObject(NeopjookBodyModel()) {
    val bodyModel: NeopjookBodyModel
        get() = mRenderObject as NeopjookBodyModel

    private val head: GameObject
        get() = transform.getChild(0).gameObject
    private val armL: GameObject
        get() = transform.getChild(1).gameObject
    private val armR: GameObject
        get() = transform.getChild(2).gameObject
    private val legL: GameObject
        get() = transform.getChild(3).gameObject
    private val legR: GameObject
        get() = transform.getChild(4).gameObject
    lateinit var target: BloodPoint

    ////////////////////// FOR ANIMATION //////////////////////
    private var state: Constants.NeopjookState = Constants.NeopjookState.Default
    private val targetPos = Vector3(0f, NEOPJOOK_Y_COORD, 0f)
    private val targetRotation = Quaternion()
    private val children_targetRotation = ArrayList(default_children_targetRotation)
    private val children_targetPos = ArrayList(default_children_targetPos)

    private var state_laughing = 0

    private var flag_default = true
    ///////////////////////////////////////////////////////////

    /** Make Neopjook2Object do default motion (breathing) */
    fun setDefaultMotion() {
        state = Constants.NeopjookState.Default
        children_targetPos[HEAD] = defaultHeadPos
        children_targetPos[LEG_L] = defaultLegLPos
        children_targetPos[LEG_R] = defaultLegRPos
        children_targetRotation[HEAD] = defaultHeadRot
        children_targetRotation[ARM_L] = defaultArmLRot
        children_targetRotation[ARM_R] = defaultArmRRot
        children_targetRotation[LEG_L] = defaultLegLRot
        children_targetRotation[LEG_R] = defaultLegRRot
    }

    /** Make Neopjook2Object walk to input position with walking animation */
    fun walk(pos: Vector3) {
        walk(pos.x, pos.y, pos.z)
    }

    /** Make Neopjook2Object walk to input position with walking animation */
    fun walk(x: Float, y: Float, z: Float) {
        targetPos.setValues(x, y, z)
        val prev = transform.modelMatrix
        transform.setForwardDir(targetPos - transform.pos)
        transform.turnLeft()
        targetRotation.setValues(transform.quat)
        transform.setByModelMatrix(prev)

        state = Constants.NeopjookState.Walking
        children_targetPos[HEAD] = defaultHeadPos
        children_targetPos[ARM_L] = defaultArmLPos
        children_targetPos[ARM_R] = defaultArmRPos
        children_targetPos[LEG_L] = defaultLegLPos
        children_targetPos[LEG_R] = defaultLegRPos
        children_targetRotation[HEAD] = defaultHeadRot
    }

    /** Make Neopjook2Object laugh at Player */
    fun laugh() {
        state = Constants.NeopjookState.Laughing
        state_laughing = 0
        children_targetPos[HEAD] = defaultHeadPos
        children_targetPos[ARM_L] = defaultArmLPos
        children_targetPos[ARM_R] = defaultArmRPos
        children_targetPos[LEG_L] = defaultLegLPos
        children_targetPos[LEG_R] = defaultLegRPos
        children_targetRotation[HEAD] = defaultHeadRot
        children_targetRotation[ARM_L] = defaultArmLRot

        val prev = transform.modelMatrix
        transform.lookAt(Managers.game.player!!)
        transform.turnLeft()
        val deg = 35f
        transform.rotate(deg, Vector3.backward)
        targetRotation.setValues(transform.quat)
        transform.setByModelMatrix(prev)

        legL.transform.rotate(-deg, Vector3.backward)
        children_targetRotation[LEG_L] = legL.transform.localQuat
        children_targetRotation[LEG_R] = legL.transform.localQuat
        legL.transform.rotate(deg, Vector3.backward)

        val prev_armR = armR.transform.modelMatrix
        armR.transform.setLocalRotation(Quaternion.getQuat(rightAngRad, Vector3.up))
        armR.transform.rotate(deg, Vector3.right)
        children_targetRotation[ARM_R] = armR.transform.localQuat
        armR.transform.setByModelMatrix(prev_armR)
    }

    /** Make Neopjook2Object dance */
    fun dance() {
        state = Constants.NeopjookState.Dancing
    }

    /** Do animation corresponding to current state of Neopjook. */
    fun doAnimation(dt: Long) {
        when (state) {
            Constants.NeopjookState.Walking -> {
                doWalking(dt)
            }
            Constants.NeopjookState.Laughing -> {
                doLaughing(dt)
            }
            Constants.NeopjookState.Dancing-> {
                doDancing(dt)
            }
            else -> {
                doDefault(dt)
            }
        }
        for ((i, child) in transform.children.withIndex()) {
            if (i >= NUM_OF_PARTS-1) break

            val newPos = Vector3(
                lerp(child.localPos.x, children_targetPos[i].x, speeds[state.idx]*dt),
                lerp(child.localPos.y, children_targetPos[i].y, speeds[state.idx]*dt),
                lerp(child.localPos.z, children_targetPos[i].z, speeds[state.idx]*dt)
            )
            child.moveToLocal(newPos)
            child.setLocalRotation(
                slerp(child.localQuat, children_targetRotation[i], rotSpeeds[state.idx]*dt)
            )
        }

        transform.setLocalRotation(
            slerp(
                transform.localQuat,
                targetRotation,
                ROT_SPEED*dt
            )
        )
    }

    private fun doDefault(dt: Long) {
        var new_armL_targetPos = defaultArmLPosMax
        var new_armR_targetPos = defaultArmRPosMax

        // Going up
        if (flag_default) {
            if (armL.transform.localPos.y >= defaultArmLPosMax.y - 0.01f) {
                new_armL_targetPos = defaultArmLPos
                new_armR_targetPos = defaultArmRPos
                flag_default = false
            }
        }
        // Going down
        else {
            if (armL.transform.localPos.y <= defaultArmLPos.y + 0.01f) {
                new_armL_targetPos = defaultArmLPosMax
                new_armR_targetPos = defaultArmRPosMax
                flag_default = true
            } else {
                new_armL_targetPos = defaultArmLPos
                new_armR_targetPos = defaultArmRPos
            }
        }
        children_targetPos[ARM_L] = new_armL_targetPos
        children_targetPos[ARM_R] = new_armR_targetPos
    }

    private fun doWalking(dt: Long) {
        var new_armL_targetRot = walkingArmLRotFwd
        var new_armR_targetRot = walkingArmRRotBack
        var new_legL_targetRot = walkingLegRotBack
        var new_legR_targetRot = walkingLegRotFwd

        // left arm going front, left leg going back, right arm going front, right leg going back
        if (flag_default) {
            if (Quaternion.dot(armL.transform.localQuat, children_targetRotation[ARM_L]) >= 0.995f) {
                new_armL_targetRot = walkingArmLRotBack
                new_armR_targetRot = walkingArmRRotFwd
                new_legL_targetRot = walkingLegRotFwd
                new_legR_targetRot = walkingLegRotBack
                flag_default = false
            }
        }
        // left arm going back, left leg going front, right arm going back, right leg going front
        else {
            if (Quaternion.dot(armL.transform.localQuat, children_targetRotation[ARM_L]) >= 0.995f) {
                new_armL_targetRot = walkingArmLRotFwd
                new_armR_targetRot = walkingArmRRotBack
                new_legL_targetRot = walkingLegRotBack
                new_legR_targetRot = walkingLegRotFwd
                flag_default = true
            } else {
                new_armL_targetRot = walkingArmLRotBack
                new_armR_targetRot = walkingArmRRotFwd
                new_legL_targetRot = walkingLegRotFwd
                new_legR_targetRot = walkingLegRotBack
            }
        }
        children_targetRotation[ARM_L] = new_armL_targetRot
        children_targetRotation[ARM_R] = new_armR_targetRot
        children_targetRotation[LEG_L] = new_legL_targetRot
        children_targetRotation[LEG_R] = new_legR_targetRot

        if (Vector.norm2(transform.pos - targetPos) <= 0.01f) {
            transform.moveTo(targetPos)
            setDefaultMotion()
        } else {
            transform.goAlongDir(SPEED * dt,
                Vector.normalize(targetPos-transform.pos) as Vector3
            )
        }
    }

    // TODO
    private fun doLaughing(dt: Long) {
        when(state_laughing) {
            0 -> {
                if (Quaternion.dot(armR.transform.localQuat, children_targetRotation[ARM_R]) >= 0.995f) {
                    state_laughing = 1
                    // make it going up (transform.pos -> up, leg.transform.pos -> down
                    targetPos.setValues(transform.pos.x, LaughingPosMaxY, transform.pos.z)
                    children_targetPos[LEG_L].setValues(
                        legL.transform.localPos.x,
                        legL.transform.localPos.y - LaughingPosMaxDelta,
                        legL.transform.localPos.z
                    )
                    children_targetPos[LEG_R].setValues(
                        legR.transform.localPos.x,
                        legR.transform.localPos.y - LaughingPosMaxDelta,
                        legR.transform.localPos.z
                    )
                }
            }
            1 -> { // going up
                if (Vector.norm2(transform.pos - targetPos) <= 0.0001f) {
                    transform.moveTo(targetPos)
                    legL.transform.moveToLocal(children_targetPos[LEG_L])
                    legR.transform.moveToLocal(children_targetPos[LEG_R])
                    state_laughing = 2
                    // make it going down
                    targetPos.setValues(transform.pos.x, Constants.NEOPJOOK_Y_COORD, transform.pos.z)
                    children_targetPos[LEG_L].setValues(
                        legL.transform.localPos.x,
                        legL.transform.localPos.y + LaughingPosMaxDelta,
                        legL.transform.localPos.z
                    )
                    children_targetPos[LEG_R].setValues(
                        legR.transform.localPos.x,
                        legR.transform.localPos.y + LaughingPosMaxDelta,
                        legR.transform.localPos.z
                    )
                } else {
                    val newPos = Vector3(
                        lerp(transform.pos.x, targetPos.x, SPEED_LAUGHING*dt),
                        lerp(transform.pos.y, targetPos.y, SPEED_LAUGHING*dt),
                        lerp(transform.pos.z, targetPos.z, SPEED_LAUGHING*dt)
                    )
                    transform.moveToLocal(newPos)
                }
            }
            2 -> { // going down
                if (Vector.norm2(transform.pos - targetPos) <= 0.0001f) {
                    transform.moveTo(targetPos)
                    legL.transform.moveToLocal(children_targetPos[LEG_L])
                    legR.transform.moveToLocal(children_targetPos[LEG_R])
                    state_laughing = 1
                    // make it going up
                    targetPos.setValues(transform.pos.x, LaughingPosMaxY, transform.pos.z)
                    children_targetPos[LEG_L].setValues(
                        legL.transform.localPos.x,
                        legL.transform.localPos.y - LaughingPosMaxDelta,
                        legL.transform.localPos.z
                    )
                    children_targetPos[LEG_R].setValues(
                        legR.transform.localPos.x,
                        legR.transform.localPos.y - LaughingPosMaxDelta,
                        legR.transform.localPos.z
                    )
                } else {
                    val newPos = Vector3(
                        lerp(transform.pos.x, targetPos.x, SPEED_LAUGHING*dt),
                        lerp(transform.pos.y, targetPos.y, SPEED_LAUGHING*dt),
                        lerp(transform.pos.z, targetPos.z, SPEED_LAUGHING*dt)
                    )
                    transform.moveToLocal(newPos)
                }
            }
        }
    }

    // TODO
    private fun doDancing(dt: Long) {

    }

    override fun initGameObject() {
        super.initGameObject()
        setHead()
        setArmL()
        setArmR()
        setLegL()
        setLegR()
        for ((i, child) in transform.children.withIndex()) {
            child.moveToLocal(default_children_targetPos[i])
            child.setLocalRotation(default_children_targetRotation[i])
        }
        setWalkingParameters()
    }

    private fun setWalkingParameters() {
        val rad = Math.toRadians(DEG.toDouble()).toFloat()
        var rotDelta = 90f - DEG
        armL.transform.setLocalRotation(Quaternion.getQuat(-rightAngRad, Vector3.up))
        armR.transform.setLocalRotation(Quaternion.getQuat(rightAngRad, Vector3.up))
        armL.transform.rotate(-rotDelta, Vector3.right)
        armR.transform.rotate(rotDelta, Vector3.right)
        walkingArmLRotFwd.setValues(armL.transform.localQuat)
        walkingArmRRotFwd.setValues(armR.transform.localQuat)
        rotDelta = 2f * DEG
        armL.transform.rotate(-rotDelta, Vector3.right)
        armR.transform.rotate(rotDelta, Vector3.right)
        walkingArmLRotBack.setValues(armL.transform.localQuat)
        walkingArmRRotBack.setValues(armR.transform.localQuat)
        armL.transform.setLocalRotation(default_children_targetRotation[ARM_L])
        armR.transform.setLocalRotation(default_children_targetRotation[ARM_R])
        walkingLegRotBack.setValues(Quaternion(cos(rad), 0.0f, 0.0f, -sin(rad)))
        walkingLegRotFwd.setValues(Quaternion(cos(rad), 0.0f, 0.0f, sin(rad)))
    }

    private fun setHead() {
        val head = GameObject(NeopjookHeadModel())
        head.mRenderObject!!.setAnchor(headAnchor)
        transform.addChild(head.transform)
    }

    private fun setArmL() {
        val armL = GameObject(NeopjookArmLModel())
        // armL.mRenderObject!!.color = floatArrayOf(1f, 0.2f, 0.2f) // red
        armL.mRenderObject!!.setAnchor(armLAnchor)
        transform.addChild(armL.transform)
    }

    private fun setArmR() {
        val armR = GameObject(NeopjookArmRModel())
        // armR.mRenderObject!!.color = floatArrayOf(0.2f, 1f, 0.2f) // green
        armR.mRenderObject!!.setAnchor(armRAnchor)
        transform.addChild(armR.transform)
    }

    private fun setLegL() {
        val legL = GameObject(NeopjookLegLModel())
        // legL.mRenderObject!!.color = floatArrayOf(1f, 0.2f, 0.2f) // red
        legL.mRenderObject!!.setAnchor(legLAnchor)
        transform.addChild(legL.transform)
    }

    private fun setLegR() {
        val legR = GameObject(NeopjookLegRModel())
        // legR.mRenderObject!!.color = floatArrayOf(0.2f, 1f, 0.2f) // green
        legR.mRenderObject!!.setAnchor(legRAnchor)
        transform.addChild(legR.transform)
    }

    fun setTarget() {
        target = BloodPoint()
        // bloodPoint.transform.setScale(Vector3.ones) // TODO: Adjust the size of bloodPoint
        target.transform.setScale(Vector3(1.2f, 1.2f, 1.2f))
        resetTarget()
    }

    fun resetTarget() {
        // Select the part to attach blood point
        var idx: Int = idxRange.random()
        var attachSubject: GameObject =
            if (idx < NUM_OF_PARTS-1) {
                transform.getChild(idx).gameObject
            } else this
        attachSubject.transform.addChild(target.transform)

        // Select the position to attach blood point
        val N: Int = (attachSubject.mRenderObject!!.vertices!!.size) / RenderObject.COORDS_PER_VERTEX
        idx = (0 until N).random() * 3
        val pos = Vector3(attachSubject.mRenderObject!!.vertices!!
            .slice(idx until idx+RenderObject.COORDS_PER_VERTEX).toFloatArray())
        val scaledPos = Vector3(
            pos.x * attachSubject.transform.scale.x,
            pos.y * attachSubject.transform.scale.y,
            pos.z * attachSubject.transform.scale.z)
        target.transform.moveToLocal(scaledPos)
    }

    companion object {
        private const val NUM_OF_PARTS = 6
        private val idxRange = (0 until NUM_OF_PARTS)

        private const val HEAD = 0
        private const val ARM_L = 1
        private const val ARM_R = 2
        private const val LEG_L = 3
        private const val LEG_R = 4

        private const val SPEED = 1.2f * Constants.MILLI
        private const val SPEED_DEFAULT = 2.5f * Constants.MILLI
        private const val SPEED_WALKING = 2f * Constants.MILLI
        private const val SPEED_LAUGHING = 35f * Constants.MILLI
        private const val SPEED_DANCING = 2f * Constants.MILLI

        private const val ROT_SPEED = 10f * Constants.MILLI
        private const val ROT_SPEED_DEFAULT = 8f * Constants.MILLI
        private const val ROT_SPEED_WALKING = 3f * Constants.MILLI
        private const val ROT_SPEED_LAUGHING = 10f * Constants.MILLI
        private const val ROT_SPEED_DANCING = 3f * Constants.MILLI

        private val speeds =
            listOf(SPEED_DEFAULT, SPEED_WALKING, SPEED_LAUGHING, SPEED_DANCING)
        private val rotSpeeds =
            listOf(ROT_SPEED_DEFAULT, ROT_SPEED_WALKING, ROT_SPEED_LAUGHING, ROT_SPEED_DANCING)

        private val headAnchor = Vector3(0f, -8.955f, 0f)
        private val armLAnchor = Vector3(0f, -7.6745f, 2.21f)
        private val armRAnchor = Vector3(0f, -7.6745f, -2.21f)
        private val legLAnchor = Vector3(-0.4205f, -3.404f, 0.9215f)
        private val legRAnchor = Vector3(-0.4205f, -3.404f, -0.9215f)

        private val defaultHeadPos = Vector3(0f, 0.55f, 0f)
        private val defaultArmLPos = Vector3(0f, 0.45f, -0.55f)
        private val defaultArmRPos = Vector3(0f, 0.45f, 0.55f)
        private val defaultLegLPos = Vector3(0.1f, -0.53f, -0.15f)
        private val defaultLegRPos = Vector3(0.1f, -0.53f, 0.15f)

        private val defaultArmLPosMax = Vector3(0f, 0.6f, -0.55f)
        private val defaultArmRPosMax = Vector3(0f, 0.6f, 0.55f)

        private val defaultHeadRot = Quaternion()
        private val defaultArmLRot = Quaternion(0.5f, -0.5f, -0.5f, -0.5f)
        private val defaultArmRRot = Quaternion(0.5f, 0.5f, 0.5f, -0.5f)
        private val defaultLegLRot = Quaternion()
        private val defaultLegRRot = Quaternion()

        private const val rightAngRad = PI.toFloat() / 2f
        private const val DEG = 20f
        private const val RAD = 0.349066f
        private val walkingArmLRotBack = Quaternion(0.27059808f, -0.6532815f, -0.27059808f, -0.6532815f)
        private val walkingArmRRotBack = Quaternion(0.27059808f, 0.6532815f, 0.27059808f, -0.6532815f)
        private val walkingLegRotBack = Quaternion(cos(RAD), 0.0f, 0.0f, -sin(RAD))
        private val walkingArmLRotFwd = Quaternion(0.65328157f, -0.27059805f, -0.6532815f, -0.27059805f)
        private val walkingArmRRotFwd = Quaternion(0.65328157f, 0.27059805f, 0.6532815f, -0.27059805f)
        private val walkingLegRotFwd = Quaternion(cos(RAD), 0.0f, 0.0f, sin(RAD))

        private val LaughingPosMaxDelta = 0.2f
        private val LaughingPosMaxY = Constants.NEOPJOOK_Y_COORD + LaughingPosMaxDelta

        private val default_children_targetRotation =
            listOf(defaultHeadRot, defaultArmLRot, defaultArmRRot, defaultLegLRot, defaultLegRRot)
        private val default_children_targetPos =
            listOf(defaultHeadPos, defaultArmLPos, defaultArmRPos, defaultLegLPos, defaultLegRPos)
    }
}