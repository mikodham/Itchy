package kr.ac.kaist.itchy.gameObjects

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.renderObjects.SquareModel

/** Wrapper Class for GameObject whose RenderObject is CubSquareModel. */
class SquareObject: GameObject(SquareModel()) {
    val squareModel: SquareModel
        get() = mRenderObject as SquareModel
}