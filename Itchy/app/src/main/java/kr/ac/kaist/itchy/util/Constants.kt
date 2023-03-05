package kr.ac.kaist.itchy.util

/**
 * Created by PCPC on 2016-10-04.
 */

/** Constants should be located here */
object Constants {
    const val CS175_PI = 3.14159265358979323846264338327950288
    const val CS175_EPS = 1e-8
    const val CS175_EPS2 = CS175_EPS * CS175_EPS
    const val CS175_EPS3 = CS175_EPS * CS175_EPS * CS175_EPS
    const val EPSILON = 1e-5
    const val MILLI = 0.001f
    const val SCORESCALE = 0.01f
    /////////////////////// BOUNDARIES ////////////////////////
    const val DOTPRODUCT_BOUNDARY = 1F - 1e-5 // Dot Product can become larger than 1 because of limit of Float -> acos() returns Nan
    const val LOOKAT_DISTANCE_BOUNDARY = 0.01f // LookAt() function becomes unstable when two objects are too close
    ///////////////////////////////////////////////////////////

    /////////////////////// GAME SCORE ////////////////////////
    const val comboBonus = 20 // prize if you consistently hit target without colliding
    const val initLevelScore = 10 // initial prize
    const val timeScaleScore = -10 // in 1 minute, - value
    const val hitTargetBonus = 30
    ///////////////////////////////////////////////////////////

    ///////////////////// COLOR CONSTANTS /////////////////////
    val DEFAULT_CUBE_COLOR = floatArrayOf(0.2f, 0.709803922f, 0.898039216f)
    val DEFAULT_LASER_COLOR = floatArrayOf(1.0f, 0f, 0f)
    val DEFAULT_SQUARE_COLOR = floatArrayOf(0.2f, 0.709803922f, 0.898039216f)
    val DEFAULT_SPHERE_COLOR = floatArrayOf(1.0f, 0.1f, 0.1f)
    val DEFAULT_TURRET_COLOR = floatArrayOf(0.2f, 0.2f, 0.2f)
    val DEFAULT_BULLET_COLOR = floatArrayOf(1.0f, 0.1f, 0.1f)
    val DEFAULT_INDICATOR_COLOR = floatArrayOf(1.0f, 1.0f, 0.2f)
    val DEFAULT_NEOPJOOK_COLOR = floatArrayOf(.1f, .4f, 1.0f)
    val DEFAULT_ROOM_COLOR = floatArrayOf(0.9f, 0.9f, 0.8f)

    val COLOR_BLUE = floatArrayOf(0f, 0f, 1.0f)
    val COLOR_WHITE = floatArrayOf(1.0f, 1.0f, 1.0f)
    val COLOR_BLACK = floatArrayOf(0f, 0f, 0f)
    val COLOR_PINK = floatArrayOf(0.9f, 0.0f, 0.9f)
    ///////////////////////////////////////////////////////////

    const val ROOM_SIZE = 20f
    const val NEOPJOOK_Y_COORD = -0.75f

    ////////////////////////// TIMES //////////////////////////
    const val PERIOD_60FPS = 16L
    const val PERIOD_30FPS = 33L
    ///////////////////////////////////////////////////////////

    //////////////////// ENUM DEFINITIONS /////////////////////
    enum class SceneType {
        Unknown, Title, Game, Laser
    }

    enum class ShaderIndex(val idx: Int) {
        DEFAULT(0),
        LASER(1),
        MOSQUITO(2),
        ROOM(3)
    }

    enum class NeopjookState(val idx: Int) {
        Default(0), Walking(1), Laughing(2), Dancing(3)
    }

    enum class TurretPlaneType(val idx: Int) {
        XY(0), XYN(1), YZ(2), YZN(3), ZX(4), ZXN(5)
    }
    ///////////////////////////////////////////////////////////
}