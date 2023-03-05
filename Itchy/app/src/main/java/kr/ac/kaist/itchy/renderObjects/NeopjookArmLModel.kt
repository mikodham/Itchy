package kr.ac.kaist.itchy.renderObjects

import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.util.Constants

/**
 * Skeleton created by sjjeon on 16. 9. 20.
 * Reissued for Itchy on 27. 9. 22
 */
class NeopjookArmLModel: RenderObject() {
    override var color = Constants.DEFAULT_NEOPJOOK_COLOR
    private val verts = floatArrayOf(
            -0.66f, 8.342f, -2.21f,
            -0.66f, 7.007f, -5.11f,
            -0.66f, 7.007f, -2.21f,
            -0.66f, 7.007f, -5.11f,
            0.386f, 7.288f, -5.52f,
            0.667f, 7.007f, -5.11f,
            0.667f, 8.342f, -5.11f,
            0.667f, 7.007f, -2.21f,
            0.667f, 7.007f, -5.11f,
            -0.66f, 8.342f, -5.11f,
            0.667f, 8.342f, -2.21f,
            0.667f, 8.342f, -5.11f,
            0.667f, 7.007f, -5.11f,
            -0.66f, 7.007f, -2.21f,
            -0.66f, 7.007f, -5.11f,
            0.386f, 8.061f, -5.52f,
            -0.58f, 8.258f, -5.87f,
            -0.38f, 8.061f, -5.52f,
            0.667f, 8.342f, -5.11f,
            -0.38f, 8.061f, -5.52f,
            -0.66f, 8.342f, -5.11f,
            -0.66f, 7.007f, -5.11f,
            -0.38f, 8.061f, -5.52f,
            -0.38f, 7.288f, -5.52f,
            0.667f, 7.007f, -5.11f,
            0.386f, 8.061f, -5.52f,
            0.667f, 8.342f, -5.11f,
            0.667f, 8.342f, -2.21f,
            -0.66f, 7.007f, -2.21f,
            0.667f, 7.007f, -2.21f,
            -0.58f, 8.258f, -5.87f,
            -0.58f, 7.091f, -7.25f,
            -0.58f, 7.091f, -5.87f,
            -0.38f, 8.061f, -5.52f,
            -0.58f, 7.091f, -5.87f,
            -0.38f, 7.288f, -5.52f,
            0.386f, 7.288f, -5.52f,
            0.583f, 8.258f, -5.87f,
            0.386f, 8.061f, -5.52f,
            -0.38f, 7.288f, -5.52f,
            0.583f, 7.091f, -5.87f,
            0.386f, 7.288f, -5.52f,
            -0.58f, 8.258f, -7.25f,
            0.583f, 7.091f, -7.25f,
            -0.58f, 7.091f, -7.25f,
            0.583f, 7.091f, -5.87f,
            0.583f, 8.258f, -7.25f,
            0.583f, 8.258f, -5.87f,
            -0.58f, 7.091f, -5.87f,
            0.583f, 7.091f, -7.25f,
            0.583f, 7.091f, -5.87f,
            0.583f, 8.258f, -5.87f,
            -0.58f, 8.258f, -7.25f,
            -0.58f, 8.258f, -5.87f,
            -0.66f, 8.342f, -2.21f,
            -0.66f, 8.342f, -5.11f,
            -0.66f, 7.007f, -5.11f,
            -0.66f, 7.007f, -5.11f,
            -0.38f, 7.288f, -5.52f,
            0.386f, 7.288f, -5.52f,
            0.667f, 8.342f, -5.11f,
            0.667f, 8.342f, -2.21f,
            0.667f, 7.007f, -2.21f,
            -0.66f, 8.342f, -5.11f,
            -0.66f, 8.342f, -2.21f,
            0.667f, 8.342f, -2.21f,
            0.667f, 7.007f, -5.11f,
            0.667f, 7.007f, -2.21f,
            -0.66f, 7.007f, -2.21f,
            0.386f, 8.061f, -5.52f,
            0.583f, 8.258f, -5.87f,
            -0.58f, 8.258f, -5.87f,
            0.667f, 8.342f, -5.11f,
            0.386f, 8.061f, -5.52f,
            -0.38f, 8.061f, -5.52f,
            -0.66f, 7.007f, -5.11f,
            -0.66f, 8.342f, -5.11f,
            -0.38f, 8.061f, -5.52f,
            0.667f, 7.007f, -5.11f,
            0.386f, 7.288f, -5.52f,
            0.386f, 8.061f, -5.52f,
            0.667f, 8.342f, -2.21f,
            -0.66f, 8.342f, -2.21f,
            -0.66f, 7.007f, -2.21f,
            -0.58f, 8.258f, -5.87f,
            -0.58f, 8.258f, -7.25f,
            -0.58f, 7.091f, -7.25f,
            -0.38f, 8.061f, -5.52f,
            -0.58f, 8.258f, -5.87f,
            -0.58f, 7.091f, -5.87f,
            0.386f, 7.288f, -5.52f,
            0.583f, 7.091f, -5.87f,
            0.583f, 8.258f, -5.87f,
            -0.38f, 7.288f, -5.52f,
            -0.58f, 7.091f, -5.87f,
            0.583f, 7.091f, -5.87f,
            -0.58f, 8.258f, -7.25f,
            0.583f, 8.258f, -7.25f,
            0.583f, 7.091f, -7.25f,
            0.583f, 7.091f, -5.87f,
            0.583f, 7.091f, -7.25f,
            0.583f, 8.258f, -7.25f,
            -0.58f, 7.091f, -5.87f,
            -0.58f, 7.091f, -7.25f,
            0.583f, 7.091f, -7.25f,
            0.583f, 8.258f, -5.87f,
            0.583f, 8.258f, -7.25f,
            -0.58f, 8.258f, -7.25f,
    )
    private val norms = floatArrayOf(
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            0.0f, -0.82f, -0.56f,
            0.0f, -0.82f, -0.56f,
            0.0f, -0.82f, -0.56f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, 0.87f, 0.492f,
            0.0f, 0.87f, 0.492f,
            0.0f, 0.87f, 0.492f,
            0.0f, 0.827f, -0.56f,
            0.0f, 0.827f, -0.56f,
            0.0f, 0.827f, -0.56f,
            -0.82f, 0.0f, -0.56f,
            -0.82f, 0.0f, -0.56f,
            -0.82f, 0.0f, -0.56f,
            0.827f, 0.0f, -0.56f,
            0.827f, 0.0f, -0.56f,
            0.827f, 0.0f, -0.56f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -0.87f, 0.0f, 0.492f,
            -0.87f, 0.0f, 0.492f,
            -0.87f, 0.0f, 0.492f,
            0.87f, 0.0f, 0.492f,
            0.87f, 0.0f, 0.492f,
            0.87f, 0.0f, 0.492f,
            0.0f, -0.87f, 0.492f,
            0.0f, -0.87f, 0.492f,
            0.0f, -0.87f, 0.492f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            0.0f, -0.82f, -0.56f,
            0.0f, -0.82f, -0.56f,
            0.0f, -0.82f, -0.56f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.0f, 0.87f, 0.492f,
            0.0f, 0.87f, 0.492f,
            0.0f, 0.87f, 0.492f,
            0.0f, 0.827f, -0.56f,
            0.0f, 0.827f, -0.56f,
            0.0f, 0.827f, -0.56f,
            -0.82f, 0.0f, -0.56f,
            -0.82f, 0.0f, -0.56f,
            -0.82f, 0.0f, -0.56f,
            0.827f, 0.0f, -0.56f,
            0.827f, 0.0f, -0.56f,
            0.827f, 0.0f, -0.56f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -0.87f, 0.0f, 0.492f,
            -0.87f, 0.0f, 0.492f,
            -0.87f, 0.0f, 0.492f,
            0.87f, 0.0f, 0.492f,
            0.87f, 0.0f, 0.492f,
            0.87f, 0.0f, 0.492f,
            0.0f, -0.87f, 0.492f,
            0.0f, -0.87f, 0.492f,
            0.0f, -0.87f, 0.492f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
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