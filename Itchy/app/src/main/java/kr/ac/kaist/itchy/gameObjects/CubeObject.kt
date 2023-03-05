package kr.ac.kaist.itchy.gameObjects

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.renderObjects.CubeModel

/** Wrapper Class for GameObject whose RenderObject is CubeModel. */
class CubeObject: GameObject(CubeModel()) {
    val cubeModel: CubeModel
        get() = mRenderObject as CubeModel
}