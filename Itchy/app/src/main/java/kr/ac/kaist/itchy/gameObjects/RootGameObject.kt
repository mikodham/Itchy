package kr.ac.kaist.itchy.gameObjects

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.gameEngine.ShaderState
import kr.ac.kaist.itchy.gameEngine.Transform
import java.util.*

/** A GameObject corresponding to the root of the scene tree */
class RootGameObject: GameObject() {
    /** Its transform always returns a default Transform (located at origin, with no rotation) */
    override var transform: Transform = Transform(this)
        get() = Transform(this)

    /** Do nothing since a RootObject has nothing to draw. */
    override fun draw(viewMatrix: FloatArray?, shaderStates: Vector<ShaderState>) {}

    override fun initGameObject() {}
}