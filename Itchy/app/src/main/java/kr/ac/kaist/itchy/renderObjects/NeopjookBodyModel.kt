package kr.ac.kaist.itchy.renderObjects

import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.util.Constants

/**
 * Skeleton created by sjjeon on 16. 9. 20.
 * Reissued for Itchy on 27. 9. 22
 */
class NeopjookBodyModel: RenderObject() {
    override var color = Constants.DEFAULT_NEOPJOOK_COLOR
    private val verts = floatArrayOf(
            -0.89f, 2.442f, 1.843f,
            -0.89f, -2.442f, -1.84f,
            -0.89f, -2.442f, 1.843f,
            -0.89f, 2.442f, -1.84f,
            0.893f, -2.442f, -1.84f,
            -0.89f, -2.442f, -1.84f,
            0.893f, 2.442f, -1.84f,
            0.893f, -2.442f, 1.843f,
            0.893f, -2.442f, -1.84f,
            0.893f, 2.442f, 1.843f,
            -0.89f, -2.442f, 1.843f,
            0.893f, -2.442f, 1.843f,
            0.893f, -2.442f, -1.84f,
            -0.89f, -2.442f, 1.843f,
            -0.89f, -2.442f, -1.84f,
            -0.89f, 2.442f, -1.84f,
            0.893f, 2.442f, 1.843f,
            0.893f, 2.442f, -1.84f,
            -0.89f, 2.442f, 1.843f,
            -0.89f, 2.442f, -1.84f,
            -0.89f, -2.442f, -1.84f,
            -0.89f, 2.442f, -1.84f,
            0.893f, 2.442f, -1.84f,
            0.893f, -2.442f, -1.84f,
            0.893f, 2.442f, -1.84f,
            0.893f, 2.442f, 1.843f,
            0.893f, -2.442f, 1.843f,
            0.893f, 2.442f, 1.843f,
            -0.89f, 2.442f, 1.843f,
            -0.89f, -2.442f, 1.843f,
            0.893f, -2.442f, -1.84f,
            0.893f, -2.442f, 1.843f,
            -0.89f, -2.442f, 1.843f,
            -0.89f, 2.442f, -1.84f,
            -0.89f, 2.442f, 1.843f,
            0.893f, 2.442f, 1.843f,
    )

    private val norms = floatArrayOf(
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
    )
    init {
        setVertices(verts)
        setNormals(norms)
        setBuffer()
    }
}