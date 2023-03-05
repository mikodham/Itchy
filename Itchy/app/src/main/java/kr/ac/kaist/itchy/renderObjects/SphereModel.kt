package kr.ac.kaist.itchy.renderObjects

import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.util.Constants

class SphereModel: RenderObject() {
    override var color = Constants.DEFAULT_SPHERE_COLOR

    init {
        setVertices(GeometrySet.sphereVertices)
        setNormals(GeometrySet.sphereNormal)
        setBuffer()
    }
}