package kr.ac.kaist.itchy.renderObjects

import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.util.Constants

class CylinderModel: RenderObject() {
    override var color = Constants.COLOR_BLACK

    init {
        setVertices(GeometrySet.cylinderVertices)
        setNormals(GeometrySet.cylinderNormals)
        setBuffer()
    }
}