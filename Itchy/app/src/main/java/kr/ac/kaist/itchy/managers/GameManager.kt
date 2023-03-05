package kr.ac.kaist.itchy.managers

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.media.MediaPlayer
import android.os.*
import android.support.annotation.RequiresApi
import com.google.gson.Gson
import kr.ac.kaist.itchy.MainActivity
import kr.ac.kaist.itchy.R
import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.scenes.GameScene
import kr.ac.kaist.itchy.util.Constants
import kr.ac.kaist.itchy.util.playerData
import java.time.LocalDateTime

/** A Manager class that manage game-logic things. */
class GameManager() {
    // private var isColliding: Boolean = false
    // private var points = IntArray(1) // points history
    // private var position = FloatArray(4) // homog, w.r.t. to world frame
    var playerDefaultMoveSpeed =  0.4f * Constants.MILLI // random value

    /** Player's moving speed in length per millisecond */
    var playerMoveSpeed = playerDefaultMoveSpeed
    var playerSpeedBoostMultiplier = 1.5f

    var playerRotateSpeed = 2f * Constants.MILLI // random value
    var currentScore = 0f
    var currentTime = 0f
    var lastCollideTime = 0f
    var curLevel = 0
    var currentHp = 3
    var context: Context? = null
    var mainActivity: MainActivity? = null
    var numLaser = 40
    private var _player: GameObject? = null
    var playerName = "" // init empty playerName
    var semaphore_dt: Boolean = false
    val player: GameObject?
        get() = _player
    var hit= 0 // how many times users hit target
    var combo:Boolean = false
    var combonus: Int = 0 //comboBonus
    var mediaPlayer: MediaPlayer?= null
    var invisibleFrames: Boolean = false
    var isLaugh: Boolean = false

    fun setAsPlayer(gameObject: GameObject) {
        _player = gameObject
    }

    // TODO: Intiialize Stage
    fun initializeStage() {
        clear()
        clearData()
    }

    fun setStage(level: Int) {
        // TODO: Shall be changed by Settings Menu
        numLaser = 6 * level
        curLevel = level
        currentScore = (level * Constants.initLevelScore).toFloat()
    }

    fun setDefaultSpeed(speed: Float){
        // speed range -1 -> 1
        playerDefaultMoveSpeed =  (speed/2.5f + 0.8f) * Constants.MILLI
    }

    fun setBoostSpeed(speed: Float){
        // speed range -1 -> 1
        playerSpeedBoostMultiplier = speed/2f + 1.5f
    }


    fun clear() {
        _player = null
        currentScore = 0f
    }

    fun clearData(){
        hit = 0
        currentScore = 0f
        currentTime = 0f
        lastCollideTime = 0f
        combonus = 0
        combo = false
        invisibleFrames = false
    }

    fun reset() {
        currentScore = 0f
        currentHp = 3
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun reduceHp() {
        currentHp = (currentHp - 1).coerceIn(0, 3)
        mainActivity?.runOnUiThread {
            mainActivity?.updateHp()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun increaseHp() {
        currentHp = (currentHp + 1).coerceIn(0, 3)
        mainActivity?.runOnUiThread {
            mainActivity?.updateHp()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun vibrate2() {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                this.context?.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            this.context?.getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                300,
                VibrationEffect.EFFECT_CLICK
            )
        )
    }

    fun vibrate() {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                this.context?.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            this.context?.getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
        if (vibrator.hasVibrator()) { // Vibrator availability checking
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        500,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
                // New vibrate method for API Level 26 or higher
            } else {
                println("Can't vibrate the Vibratorrrrr")
            }
        }
    }

    fun playMusic(happy: Boolean){
//        val music?= null
        if (happy){
            // import from Prize Sound
            mediaPlayer = MediaPlayer.create(this.context, R.raw.win31)
            mediaPlayer?.start()
        }
        else{
            // Import from Error sound
            mediaPlayer = MediaPlayer.create(this.context, R.raw.winerror)
            mediaPlayer?.start()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveGame(){
        val playedTime = LocalDateTime.now()
        val name = playerName

        // UPDATE LEADERBOARD
        val gson = Gson()
        val score = currentScore
        val duration = currentTime*Constants.MILLI
        val user = playerData(name, playedTime, score, duration)

        // Append this user in BEST1-5 SPF only if the score beats the top 5
        // "Score: 1000*level + (60-second) * 50 + Prize(3000)"
        val lastData = MainActivity.prefs.getString("Best5", "None")
        var database = mutableListOf<playerData>()
        var rank = 0
        if (lastData != "None"){// CHECK IDX of player // higher
            database = gson.fromJson(lastData, Array<playerData>::class.java).toMutableList()
            val length = database.size
            for (idx in 0 until database.size) {
                if (currentScore >= database[idx].score){
                    break
                }
                rank++
            }
        }
        if (rank in 0..4){
            database.add(rank, user)
            if (database.size > 5) database.removeLast()
            val json: String = gson.toJson(database)
            MainActivity.prefs.setString("Best5", json)
//            commit may be needed
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun finishGame(){
        Managers.game.isLaugh = true
        (Managers.scene.currentScene as GameScene).resetPlayerPosition()
        (Managers.scene.currentScene as GameScene).makeNeopjookLaugh()
        playMusic(happy = false)
        saveGame()
        clearData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun hitTarget(){
        playMusic(happy = true)
        invisibleFrames = true
        if (combo) {
            // Get Extra Points if players dont take damage
            combonus++
            mainActivity?.runOnUiThread {
                object : CountDownTimer(1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        mainActivity?.showCombo(combonus*Constants.comboBonus)
                    }

                    override fun onFinish() {
                        mainActivity?.dialogcombo?.dismiss()
                    }
                }.start()
            }
        }
        mainActivity?.runOnUiThread {
            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    invisibleFrames = true
                }

                override fun onFinish() {
                    invisibleFrames = false
                }
            }.start()
        }

        hit++
        increaseHp()
        currentScore += (Constants.hitTargetBonus + combonus*Constants.comboBonus) // Combo Bonus Added
        Managers.scene.currentScene?.resetNeopjookObject()
        combo = true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onUpdate(dt: Long) {
        if (!isLaugh) {
            currentTime += (dt)
            // Add time constraint here
//        currentScore += (60-dt)*Constants.MILLI.coerceIn(maximumValue = 0f)
            mainActivity?.runOnUiThread {
                mainActivity?.updateScore(
                    currentScore.toLong(),
                    Managers.scene.currentLevel, currentTime * Constants.MILLI
                )
                mainActivity?.updateHp()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onCollision() {
        if (lastCollideTime + 3000 < currentTime && !semaphore_dt && !invisibleFrames && !isLaugh) {
            println("COLLIDE")
            invisibleFrames = true
            Managers.game.vibrate(); reduceHp()
            combo = false; combonus = 0
            lastCollideTime = currentTime
            println(lastCollideTime)
            if (currentHp == 0) return
            var counter = 3
            Managers.game.playerMoveSpeed = Managers.game.playerDefaultMoveSpeed / 2.0f
            mainActivity?.show321()
            mainActivity?.runOnUiThread {
                semaphore_dt = true
                object : CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        mainActivity?.update321(counter)
                        counter--
                    }

                    override fun onFinish() {
                        mainActivity?.text321?.text = ""
                        mainActivity?.dialog321?.dismiss()
                        semaphore_dt = false
                        invisibleFrames = false
                        Managers.game.playerMoveSpeed = Managers.game.playerDefaultMoveSpeed
                    }
                }.start()
            }
        }
    }

    /** This is called only once, when GameManger is created. */
    init {
        // TODO: Intialize Game
        initializeStage()
    }
}