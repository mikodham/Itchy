package kr.ac.kaist.itchy.managers

import kr.ac.kaist.itchy.util.Constants

/** A Manager class that manage time in game. */
class TimeManager {
    /** timeScale (default = 1f) */
    var timeScale: Float = 1f
    val targetDeltaTime: Long = Constants.PERIOD_60FPS
    /** Time amount between the current frame
     *  and the previous frame in milliseconds*/
    var deltaTime: Long = Constants.PERIOD_60FPS
    var totalTime: Float = 0F
        set(value) {
            field = value.mod(16383F)
        }
    fun clear() {
        timeScale = 1f
        deltaTime = Constants.PERIOD_60FPS
    }
}