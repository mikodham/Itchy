package kr.ac.kaist.itchy.managers

import kr.ac.kaist.itchy.gameEngine.ShaderState
import kr.ac.kaist.itchy.scenes.*
import kr.ac.kaist.itchy.util.Constants
import java.util.*

/** A Manager class that manage scenes. */
class SceneManager {
    var currentScene: BaseScene? = null
    var currentLevel = 5

    fun clear() {
        // do not reset currentLevel
        currentScene?.clear()
        currentScene = null
    }

    fun loadScene(type: Constants.SceneType) {
        Managers.clear()

        when (type) {
            Constants.SceneType.Game-> currentScene = GameScene()
            Constants.SceneType.Title -> return // TODO
            else -> return
        }
        Managers.game.setStage(currentLevel)
    }

    fun startStage(level: Int) {
        currentLevel = level
    }

    fun drawScene(shaderStates: Vector<ShaderState>) {
        currentScene!!.drawScene(shaderStates)
    }
}