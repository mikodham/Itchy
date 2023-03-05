package kr.ac.kaist.itchy.scenes

import android.opengl.GLES20
import android.os.Build
import android.support.annotation.RequiresApi
import kr.ac.kaist.itchy.collisionCheck.BoundingSphere
import kr.ac.kaist.itchy.collisionCheck.CollisionDetector
import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.gameEngine.ShaderState
import kr.ac.kaist.itchy.gameObjects.*
import kr.ac.kaist.itchy.managers.Managers
import kr.ac.kaist.itchy.util.*
import kr.ac.kaist.itchy.util.Constants.NEOPJOOK_Y_COORD
import java.util.*
import java.util.Vector
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import kotlin.math.log
import kotlin.random.Random

/** Scene for in-game situation. */
class GameScene : BaseScene() {
    private var indicator: Indicator? = null
    private var playerObject: MosquitoObject? = null
    private var laserObjects: List<LaserObject>? = null
    var numLaser = Managers.game.numLaser
    private var roomObject: RoomObject? = null
    private var collisionDetector = CollisionDetector()
    private var neopjookObject: Neopjook2Object? = null

    private val turrets = mutableListOf<Turret>()
    private val neops = mutableListOf<Neopjook2Object>()

    val projMatrix: FloatArray
        get() = mCamera!!.projMatrix
    val viewMatrix: FloatArray
        get() = mCamera!!.viewMatrix

    private var mLight = Vector3()
    private var mLight2 = Vector3()

    private val defaultPlayerPos = Vector3(1.5f, 0.8f, 2f)
    private val defaultPlayerQuat = Quaternion() // 0.3631123f, 0.3631123f, -0.3631123f, 0.7774628f)
    private val defaultPlayerScale = Vector3(0.05f, 0.05f, 0.05f)

    private val defaultRoomPos = Vector3(0f, .5f, 0f)
    private val defaultRoomQuat = Quaternion()
    private val defaultRoomScale = Vector3(1f, 1f, 1f)

    private val defaultLaserQuat = Quaternion(0f, 0f, 0.7071068f, 0.7071068f)
    private val defaultLaserQuat_y = Quaternion(0f, 0.7071068f, 0f, 0.7071068f)
    private val defaultLaserQuat_z = Quaternion(0f, 0.7071068f, 0.7071068f, 0f)
    private val defaultLaserScale = Vector3(0.01f, 10f, 0.01f)

    private val defaultNeopjookScale = Vector3(.2f, .2f, .2f) // init 0.05f

    private val defaultEyePos = Vector3(0.0f, 0.0f, 4f)

    // Camera distance multiplier, camera is further back when player is fast
    private var cameraDistMult = .2f

    init {
        sceneType = Constants.SceneType.Game
    }

    override fun clear() {
        super.clear()
        playerObject = null
        roomObject = null
        laserObjects = null
        neopjookObject = null
        collisionDetector.clear()
        turrets.clear()
        Managers.obj.clear()
    }

    /** For initialization */
    override fun onSurfaceCreated(gl10: GL10, config: EGLConfig) {
        mLight.setValues(0.0f, 20.0f, 10.0f)
        mLight2.setValues(0.0f, -20.0f, -10.0f)
        initCamera()
        initObjects()
    }

    // Initialized Camera
    private fun initCamera() {
        mCamera = Camera()
        resetViewMatrix()
    }

    // Initializes GameObjects
    private fun initObjects() {
        initRoomObject()
        initPlayerObject()
        initLaserObject()
        initNeopjookObject() // ! for testing
        initIndicator()
        // initTurrets()
    }

    // ! for testing
    private fun initNeopjookObject() {
        neopjookObject = Neopjook2Object()
        neopjookObject!!.transform.setScale(defaultNeopjookScale)
        neopjookObject!!.setTarget()
        neopjookObject!!.transform.moveTo(Vector3(4.0f, NEOPJOOK_Y_COORD, -0.5f))
        collisionDetector.addObject(neopjookObject)
    }

    private fun cyberBullying() {
        neopjookObject!!.laugh()
        // Vector3(1.5f, 0.8f, 2f)
        val neop = Neopjook2Object()
        neop.transform.setScale(defaultNeopjookScale)
        neop.transform.moveTo(Vector3(1.6f, NEOPJOOK_Y_COORD, -1f))
        neop.laugh()
        neops.add(neop)

        val neop2 = Neopjook2Object()
        neop2.transform.setScale(defaultNeopjookScale)
        neop2.transform.moveTo(Vector3(-1.0f, NEOPJOOK_Y_COORD, -0.5f))
        neop2.laugh()
        neops.add(neop2)

        val neop3 = Neopjook2Object()
        neop3.transform.setScale(defaultNeopjookScale)
        neop3.transform.moveTo(Vector3(-2.5f, NEOPJOOK_Y_COORD, 1f))
        neop3.laugh()
        neops.add(neop3)

        val neop4 = Neopjook2Object()
        neop4.transform.setScale(defaultNeopjookScale)
        neop4.transform.moveTo(Vector3(5.5f, NEOPJOOK_Y_COORD, 1f))
        neop4.laugh()
        neops.add(neop4)
    }

    // Initializes Laser Object
    private fun initLaserObject() {
//        private va miArreglo = List(5) { LaserObject() }
//        laserObjects = LaserObject()
        laserObjects = List(numLaser) { LaserObject() }
        resetLaserObject()
        collisionDetector.addObject(laserObjects)
    }

    // Initializes cube
    private fun initPlayerObject() {
        playerObject = MosquitoObject()
        Managers.game.setAsPlayer(playerObject!!)
        resetPlayerObject()
        collisionDetector.addObject(playerObject)
    }

    // Initializes room
    private fun initRoomObject() {
        roomObject = RoomObject()
        defaultRoomQuat.setValues(roomObject!!.transform.quat)
        resetRoomObject()
        collisionDetector.addObject(roomObject)
    }

    private fun initIndicator() {
        indicator = Indicator(neopjookObject!!.target as GameObject)
    }

    private fun initTurrets() {
        for (type in Constants.TurretPlaneType.values())
            makeTurret(type)
    }

    private fun makeTurret(type: Constants.TurretPlaneType) {

        when (type) {
            Constants.TurretPlaneType.XY -> {
                return
            }
            Constants.TurretPlaneType.XYN -> {
                return
            }
            Constants.TurretPlaneType.YZ -> {
                return
            }
            Constants.TurretPlaneType.YZN -> {
                return
            }
            Constants.TurretPlaneType.ZX -> {
                val turret = Turret(type)
                turret.transform.moveTo(Vector3(1.0f, -2.2f, -4.0f))
                turret.lockOn(playerObject!!)
                turrets.add(turret)
            }
            Constants.TurretPlaneType.ZXN -> {
                val turret = Turret(type)
                turret.transform.moveTo(Vector3(1.0f, 3.2f, -4.0f))
                turret.transform.rotate(179.9f, Vector3.forward)
                turret.lockOn(playerObject!!)
                turrets.add(turret)
            }
        }

    }

    private fun resetObjects() {
        resetRoomObject()
        resetPlayerObject()
        resetLaserObject()
        resetNeopjookObject()
    }

    override fun resetNeopjookObject() {
        // Move the neopjook to the random position in the room
        val neopjookPos = Vector3(
        Random.nextFloat() * Constants.ROOM_SIZE/2 - 2,
            NEOPJOOK_Y_COORD,
        Random.nextFloat() * Constants.ROOM_SIZE/2 - 2
        )
        neopjookObject!!.transform.setScale(defaultNeopjookScale)
        // neopjookObject!!.transform.moveTo(neopjookPos)
        neopjookObject!!.walk(neopjookPos) // !TODO: need to be changed
        neopjookObject!!.resetTarget()
        collisionDetector.update(neopjookObject)
    }

    private fun resetLaserObject() {
        // TODO: LASER OBJECT RESET IMPLEMENT STRATA
        for (index in 0 until numLaser / 3) {
            val LaserPos = Vector3(
                Random.nextFloat() * Constants.ROOM_SIZE/2 - 2,
                Random.nextFloat() * Constants.ROOM_SIZE/2 - 2,
                0f
            )
            laserObjects!![index].laserModel.color = Constants.DEFAULT_LASER_COLOR
            laserObjects!![index].transform.setScale(defaultLaserScale)
            laserObjects!![index].transform.moveTo(LaserPos)
            laserObjects!![index].transform.setRotation(defaultLaserQuat)

            // ADD laser in other direction
            val idx = ((numLaser.toDouble()) / 3f).toInt() + index
            val LaserPosy = Vector3(
                Random.nextFloat() * Constants.ROOM_SIZE/2 - 2,
                0f,
                Random.nextFloat() * Constants.ROOM_SIZE/2 - 2
            )
            laserObjects!![idx].laserModel.color = Constants.COLOR_BLUE
            laserObjects!![idx].transform.setScale(defaultLaserScale)
            laserObjects!![idx].transform.moveTo(LaserPosy)
            laserObjects!![idx].transform.setRotation(defaultLaserQuat_y)

            val idx2 = (((numLaser * 2).toDouble()) / 3f).toInt() + index
            val LaserPosz = Vector3(
                0f,
                Random.nextFloat() * Constants.ROOM_SIZE/2 - 2,
                Random.nextFloat() * Constants.ROOM_SIZE/2 - 2
            )
            laserObjects!![idx2].laserModel.color = Constants.COLOR_PINK
            laserObjects!![idx2].transform.setScale(defaultLaserScale)
            laserObjects!![idx2].transform.moveTo(LaserPosz)
            laserObjects!![idx2].transform.setRotation(defaultLaserQuat_z)
        }
        collisionDetector.update(laserObjects)
    }

    private fun resetPlayerObject() {
        playerObject!!.mosquitoModel.color = Constants.DEFAULT_CUBE_COLOR
        playerObject!!.transform.setScale(defaultPlayerScale)
        playerObject!!.transform.moveTo(defaultPlayerPos)
        playerObject!!.transform.setRotation(defaultPlayerQuat)
        collisionDetector.update(playerObject)
    }

    private fun resetRoomObject() {
//      roomObject!!.roomModel.color = floatArrayOf(0.1f, 0.95f, 0.1f)
        roomObject!!.transform.setScale(defaultRoomScale)
        roomObject!!.transform.moveTo(defaultRoomPos)
        roomObject!!.transform.setRotation(defaultRoomQuat)
        // only relevant if room's scale is changed
        collisionDetector.update(roomObject)
    }

    // Set the view matrix. This matrix can be said to represent the camera position.
    private fun resetViewMatrix() {
        // NOTE: In OpenGL 1, a ModelView matrix is used, which is a combination of a model and
        // view matrix. In OpenGL 2, we can keep track of these matrices separately if we choose.
        val fwd = Vector3(0f, 0f, -0f)
        val up = Vector3(0f, 0.1f, 0f)
        mCamera!!.setLookAt(defaultEyePos, fwd, up)
//        mCamera!!.setLookAt(defaultEyePos, Vector3.forward, Vector3.up)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun resetPlayerPosition() {
        resetViewMatrix()
        resetObjects()
        Managers.input.resetGyro()
    }

    private fun resetTurrets() {
        turrets.map { t -> t.reset() }
    }

    override fun reset() {
        resetViewMatrix()
        resetObjects()
        resetTurrets()
        Managers.game.reset()
        Managers.input.resetGyro()
    }

    // Function to call neopjookObject.laugh() from outside
    fun makeNeopjookLaugh() { // only called after the game ends
        neopjookObject!!.transform.moveTo(Vector3(-1.6f, NEOPJOOK_Y_COORD, -1f))
        neopjookObject?.laugh()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUpdate(dt: Long) {
        updatePlayer(dt)
        updateCamera(dt)
        updateIndicator(dt)
        updateLaser(dt)
        updateNeopjookObject(dt)
        // updateTurrets(dt)
        if (!Managers.game.isLaugh) {
            val collObj = collisionDetector.checkCollision()
            if ((collObj != null) && (collObj.collisionObject is BoundingSphere)) {
                // if collide with target sphere
                Managers.game.hitTarget()
            } else if (collObj != null) {
                if (collObj is LaserObject) {
                    Managers.game.onCollision()
                } else {
                    if (!Managers.game.invisibleFrames) {
                        Managers.game.onCollision()
                        resetPlayerPosition()
                    }
                }
            }
        }
    }

    private fun updateTurrets(dt: Long) {
        turrets.map { t -> t.onUpdate(dt) }
    }

    private fun updateNeopjookObject(dt: Long) {
        neopjookObject?.doAnimation(dt)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updatePlayer(dt: Long) {
        // Player speed depending on screen touching state
        if (!Managers.game.isLaugh) {
            if (Managers.input.isTouchingScreen) {
                Managers.game.playerMoveSpeed = lerp(
                    Managers.game.playerMoveSpeed,
                    Managers.game.playerDefaultMoveSpeed * Managers.game.playerSpeedBoostMultiplier,
                    0.3f
                )
            } else {
                Managers.game.playerMoveSpeed = lerp(
                    Managers.game.playerMoveSpeed,
                    Managers.game.playerDefaultMoveSpeed,
                    0.3f
                )
            }
            // Player Moving Constantly Forward
            if (collisionDetector.playerIsInsideRoom()) {
                 playerObject!!.transform.goForward(Managers.game.playerMoveSpeed * dt)
                // Allow rotation only when not touching screen
                if (!Managers.input.isTouchingScreen) {
                    playerObject!!.transform.rotate(
                        Managers.game.playerRotateSpeed * Managers.input.yRotation * dt,
                        0f, 1f, 0f
                    )
                    playerObject!!.transform.rotate(
                        Managers.game.playerRotateSpeed * Managers.input.xRotation * dt,
                        1f, 0f, 0f
                    )
                    playerObject!!.transform.rotate(
                        Managers.game.playerRotateSpeed * Managers.input.zRotation * dt,
                        0f, 0f, 1f
                    )
                }
            } else { // collision with the wall -> Player position is reset to starting position
                Managers.game.onCollision()
                resetPlayerPosition()
            }
        }
    }

    private fun updateLaser(dt: Long) {
        // TODO: this function is for changing the laser, in case it's moving
    }

    private fun updateCamera(dt: Long) {
        // Camera tracking
        mCamera!!.transform.moveTo(playerObject!!)
        // TODO: Find adequate frustum of Camera and posDelta
        var posDelta =
            Vector3((playerObject!!.transform.up) * 0.5f + (playerObject!!.transform.backward) * 1.3f)
        var playerSpeedScaled = Managers.game.playerMoveSpeed * 2.0 / Constants.MILLI
        cameraDistMult = lerp(cameraDistMult, .7f + log(playerSpeedScaled, 10.0).toFloat(), 0.2f)
        posDelta.multiply(cameraDistMult)
        mCamera!!.transform.translate(posDelta)
        mCamera!!.transform.setRotation(playerObject!!)
    }

    private fun updateIndicator(dt: Long) {
        indicator!!.indicate()
    }

    override fun drawScene(shaderStates: Vector<ShaderState>) {
        val vM = viewMatrix
        val mL = mLight.toHomogeneousPoint()
        val mL2 = mLight2.toHomogeneousPoint()
        val eyeLight1 = getEyeCoordVec(vM, mL)
        val eyeLight2 = getEyeCoordVec(vM, mL2)
        for (shader in shaderStates) {
            GLES20.glUseProgram(shader.mProgram)
            GLES20.glUniform3f(shader.mLightHandle, eyeLight1[0], eyeLight1[1], eyeLight1[2])
            GLES20.glUniform3f(shader.mLight2Handle, eyeLight2[0], eyeLight2[1], eyeLight2[2])
        }
        Managers.obj.drawAll(vM, shaderStates)
    }
}