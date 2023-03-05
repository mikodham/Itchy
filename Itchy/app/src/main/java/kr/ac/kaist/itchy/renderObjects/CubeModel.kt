package kr.ac.kaist.itchy.renderObjects

import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.util.Constants

/**
 * Skeleton created by sjjeon on 16. 9. 20.
 * Reissued for Itchy on 27. 9. 22
 */
class CubeModel: RenderObject() {
    override var color = Constants.DEFAULT_CUBE_COLOR

    init {
        setVertices(GeometrySet.cubeVertices)
        setNormals(GeometrySet.cubeNormals)
        setBuffer()
    }
}