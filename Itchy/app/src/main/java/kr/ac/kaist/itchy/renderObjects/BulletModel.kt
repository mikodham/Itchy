package kr.ac.kaist.itchy.renderObjects

import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.util.Constants

class BulletModel: RenderObject() {
    override var color = Constants.DEFAULT_BULLET_COLOR
    override var shaderIndex = Constants.ShaderIndex.LASER.ordinal
    init {
        setVertices(GeometrySet.sphereVertices)
        setNormals(GeometrySet.sphereNormal)
        setBuffer()
    }
}