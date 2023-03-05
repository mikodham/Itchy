package kr.ac.kaist.itchy.gameObjects

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.renderObjects.RoomModel
import kr.ac.kaist.itchy.renderObjects.SquareModel

/** Wrapper Class for GameObject whose RenderObject is CubSquareModel. */
class RoomObject: GameObject(RoomModel()) {
    val roomModel: RoomModel
        get() = mRenderObject as RoomModel
}