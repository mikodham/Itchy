package kr.ac.kaist.itchy.gameObjects

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.renderObjects.CubeModel
import kr.ac.kaist.itchy.renderObjects.CylinderModel
import kr.ac.kaist.itchy.util.*
import kotlin.math.acos

class Turret(
    private var planeType: Constants.TurretPlaneType = Constants.TurretPlaneType.ZX
) : GameObject(CylinderModel()) {
    private lateinit var floor: GameObject
    private lateinit var legFL: GameObject
    private lateinit var legFR: GameObject
    private lateinit var legBL: GameObject
    private lateinit var legBR: GameObject
    private lateinit var gun: GameObject
    private lateinit var gunBarrel: GameObject

    private lateinit var target: GameObject
    var firerate = 1f // fire per second

    override fun initGameObject() {
        super.initGameObject()
        setFloor()
        setLegs()
        setGun()
        setAsPlaneType()
    }

    private fun setFloor() {
        floor = GameObject(CylinderModel(), Constants.DEFAULT_TURRET_COLOR)
        transform.addChild(floor.transform)
        floor.transform.setScale(0.8f)
        floor.transform.moveToLocal(defaultFloorPos)
    }

    private fun setLegs() {
        legFL = GameObject(CubeModel(), Constants.DEFAULT_TURRET_COLOR)
        floor.transform.addChild(legFL.transform)
        legFL.mRenderObject!!.setAnchor(legAnchor)
        legFL.transform.setScale(legScale)
        legFL.transform.moveToLocal(defaultLegFLPos)
        legFL.transform.rotate(LEG_DEG, Vector3(1f, 0f, -1f))

        legFR = GameObject(CubeModel(), Constants.DEFAULT_TURRET_COLOR)
        floor.transform.addChild(legFR.transform)
        legFR.mRenderObject!!.setAnchor(legAnchor)
        legFR.transform.setScale(legScale)
        legFR.transform.moveToLocal(defaultLegFRPos)
        legFR.transform.rotate(LEG_DEG, Vector3(1f, 0f, 1f))

        legBL = GameObject(CubeModel(), Constants.DEFAULT_TURRET_COLOR)
        floor.transform.addChild(legBL.transform)
        legBL.mRenderObject!!.setAnchor(legAnchor)
        legBL.transform.setScale(legScale)
        legBL.transform.moveToLocal(defaultLegBLPos)
        legBL.transform.rotate(LEG_DEG, Vector3(-1f, 0f, -1f))

        legBR = GameObject(CubeModel(), Constants.DEFAULT_TURRET_COLOR)
        floor.transform.addChild(legBR.transform)
        legBR.mRenderObject!!.setAnchor(legAnchor)
        legBR.transform.setScale(legScale)
        legBR.transform.moveToLocal(defaultLegBRPos)
        legBR.transform.rotate(LEG_DEG, Vector3(-1f, 0f, 1f))
    }

    private fun setGun() {
        gun = GameObject(CubeModel(), Constants.DEFAULT_TURRET_COLOR)
        floor.transform.addChild(gun.transform)
        gun.transform.setScale(gunScale)
        gun.transform.moveToLocal(defaultGunPos)
        gunBarrel = GameObject(CylinderModel(), Constants.DEFAULT_TURRET_COLOR)
        gun.transform.addChild(gunBarrel.transform)
        gunBarrel.transform.setScale(gunBarrelScale)
        gunBarrel.transform.rotate(90f, Vector3.left)
        gunBarrel.transform.moveToLocal(defaultGunBarrelPos)
    }

    private fun setAsPlaneType() {

    }

    fun lockOn(go: GameObject) {
        target = go
    }

    fun onUpdate(dt: Long) {
        val targetPos = target.transform.pos
        val proj = Vector3()
        when (planeType) {
            Constants.TurretPlaneType.XY -> {

            }
            Constants.TurretPlaneType.XYN -> {

            }
            Constants.TurretPlaneType.YZ -> {

            }
            Constants.TurretPlaneType.YZN -> {

            }
            Constants.TurretPlaneType.ZX -> {
                floor.transform.lookAt(targetPos.x, floor.transform.pos.y, targetPos.z)
                proj.setValues(Vector3(targetPos.x, gun.transform.pos.y, targetPos.z))
            }
            Constants.TurretPlaneType.ZXN -> {
                floor.transform.lookAt(targetPos.x, floor.transform.pos.y, targetPos.z)
                transform.rotate(179.9f, transform.forward)
                proj.setValues(Vector3(targetPos.x, gun.transform.pos.y, targetPos.z))
            }
        }
        val v0 = proj - gun.transform.pos
        val v1 = targetPos - gun.transform.pos
        val deg = Vector.getBetweenAngle(v0, v1)


        when (planeType) {
            Constants.TurretPlaneType.XY -> {

            }
            Constants.TurretPlaneType.XYN -> {

            }
            Constants.TurretPlaneType.YZ -> {

            }
            Constants.TurretPlaneType.YZN -> {

            }
            Constants.TurretPlaneType.ZX -> {
                gun.transform.setLocalRotation(Quaternion())
                gun.transform.rotate(deg, gun.transform.forward)
            }
            Constants.TurretPlaneType.ZXN -> {

            }
        }
    }

    fun reset() {

    }

    private fun fire() {

    }


    init {
        mRenderObject!!.color = Constants.DEFAULT_TURRET_COLOR
        transform.setScale(0.25f)
    }

    companion object {
        private const val LEG_X = 0.11f
        private const val LEG_Z = 0.11f
        private const val LEG_Y = 0.2f
        private const val LEG_DEG = 30f

        private val legAnchor = Vector3(0f, 1f, 0f)
        private val legScale = Vector3(0.05f, 0.4f, 0.05f)
        private val gunScale = Vector3(0.25f, 0.25f, 1f)
        private val gunBarrelScale = Vector3(0.8f, 3.5f, 0.2f)

        private val defaultFloorPos = Vector3(0f, 0.08f, 0f)
        private val defaultLegFLPos = Vector3(-LEG_X, LEG_Y, -LEG_Z)
        private val defaultLegFRPos = Vector3(LEG_X, LEG_Y, -LEG_Z)
        private val defaultLegBLPos = Vector3(-LEG_X, LEG_Y, LEG_Z)
        private val defaultLegBRPos = Vector3(LEG_X, LEG_Y, LEG_Z)
        private val defaultGunPos = Vector3(0f , 0.35f, -0.06f)
        private val defaultGunBarrelPos = Vector3(0f, 0f, -0.06f)
    }
}