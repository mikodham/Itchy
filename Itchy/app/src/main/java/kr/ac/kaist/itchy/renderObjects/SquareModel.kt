package kr.ac.kaist.itchy.renderObjects

import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.util.Constants

/**
 * Created by sjjeon on 16. 9. 21.
 * * Reissued for Itchy on 27. 9. 22
 */
class SquareModel: RenderObject() {
    override var color = Constants.DEFAULT_SQUARE_COLOR

    init {
        setVertices(GeometrySet.squareVertices)
        setNormals(GeometrySet.squareNormals)
        setBuffer()
    }
}