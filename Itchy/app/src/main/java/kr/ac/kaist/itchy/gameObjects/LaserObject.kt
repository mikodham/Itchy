package kr.ac.kaist.itchy.gameObjects
import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.renderObjects.LaserModel

/** Wrapper Class for GameObject whose RenderObject is LaserModel. */
class LaserObject: GameObject(LaserModel()){
    val laserModel: LaserModel
        get() = mRenderObject as LaserModel
}


