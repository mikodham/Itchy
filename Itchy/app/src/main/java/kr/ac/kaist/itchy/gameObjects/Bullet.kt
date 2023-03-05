package kr.ac.kaist.itchy.gameObjects

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.renderObjects.BulletModel

class Bullet: GameObject(BulletModel()) {
    init {
        transform.setScale(0.1f)
    }
}