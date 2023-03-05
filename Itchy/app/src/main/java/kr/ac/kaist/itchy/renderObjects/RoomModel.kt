package kr.ac.kaist.itchy.renderObjects

import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.util.Constants

/**
 * Created by sjjeon on 16. 9. 21.
 * * Reissued for Itchy on 27. 9. 22
 */
class RoomModel: RenderObject() {
    override var color = Constants.DEFAULT_ROOM_COLOR
    override var shaderIndex = Constants.ShaderIndex.ROOM.ordinal

    // room size (20, 5, 20)
    // room.obj
    private val verts = floatArrayOf(-10.0f, -2.5f, -10.0f, -10.0f, 2.5f, 10.0f, -10.0f, -2.5f, 10.0f, 10.0f, -2.5f, -10.0f, -10.0f, 2.5f, -10.0f, -10.0f, -2.5f, -10.0f, 10.0f, -2.5f, 10.0f, 10.0f, 2.5f, -10.0f, 10.0f, -2.5f, -10.0f, -10.0f, -2.5f, 10.0f, 10.0f, 2.5f, 10.0f, 10.0f, -2.5f, 10.0f, -10.0f, -2.5f, 10.0f, 10.0f, -2.5f, -10.0f, -10.0f, -2.5f, -10.0f, 10.0f, 2.5f, 10.0f, -10.0f, 2.5f, -10.0f, 10.0f, 2.5f, -10.0f, -10.0f, -2.5f, -10.0f, -10.0f, 2.5f, -10.0f, -10.0f, 2.5f, 10.0f, 10.0f, -2.5f, -10.0f, 10.0f, 2.5f, -10.0f, -10.0f, 2.5f, -10.0f, 10.0f, -2.5f, 10.0f, 10.0f, 2.5f, 10.0f, 10.0f, 2.5f, -10.0f, -10.0f, -2.5f, 10.0f, -10.0f, 2.5f, 10.0f, 10.0f, 2.5f, 10.0f, -10.0f, -2.5f, 10.0f, 10.0f, -2.5f, 10.0f, 10.0f, -2.5f, -10.0f, 10.0f, 2.5f, 10.0f, -10.0f, 2.5f, 10.0f, -10.0f, 2.5f, -10.0f, )
    private val norms = floatArrayOf(1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, 1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, -0.0f, -1.0f, -0.0f, )
    private val texts = floatArrayOf(0.375f, 0.25f, 0.625f, 0.0f, 0.375f, 0.0f, 0.375f, 0.5f, 0.625f, 0.25f, 0.375f, 0.25f, 0.375f, 0.75f, 0.625f, 0.5f, 0.375f, 0.5f, 0.375f, 1.0f, 0.625f, 0.75f, 0.375f, 0.75f, 0.125f, 0.75f, 0.375f, 0.5f, 0.125f, 0.5f, 0.625f, 0.75f, 0.875f, 0.5f, 0.625f, 0.5f, 0.375f, 0.25f, 0.625f, 0.25f, 0.625f, 0.0f, 0.375f, 0.5f, 0.625f, 0.5f, 0.625f, 0.25f, 0.375f, 0.75f, 0.625f, 0.75f, 0.625f, 0.5f, 0.375f, 1.0f, 0.625f, 1.0f, 0.625f, 0.75f, 0.125f, 0.75f, 0.375f, 0.75f, 0.375f, 0.5f, 0.625f, 0.75f, 0.875f, 0.75f, 0.875f, 0.5f, )

    init {
        setVertices(verts)
        setNormals(norms)
        setTexture(texts, "wallTexture.jpg", "floorTexture.jpg")
        setBuffer()
    }
}