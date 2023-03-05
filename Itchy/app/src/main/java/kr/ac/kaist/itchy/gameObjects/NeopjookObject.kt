package kr.ac.kaist.itchy.gameObjects

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.renderObjects.NeopjookModel
import kr.ac.kaist.itchy.renderObjects.SquareModel

class NeopjookObject: GameObject(NeopjookModel()) {
    val neopjookModel: NeopjookModel
        get() = mRenderObject as NeopjookModel
}