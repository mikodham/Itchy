package kr.ac.kaist.itchy.renderObjects

import android.opengl.GLES20
import kr.ac.kaist.itchy.MyGLRenderer
import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.util.Constants
import kr.ac.kaist.itchy.util.Constants.DEFAULT_INDICATOR_COLOR
import java.nio.ByteBuffer
import java.nio.ByteOrder

class IndicatorModel: RenderObject() {
    override var color = Constants.DEFAULT_INDICATOR_COLOR

    init {
        setVertices(GeometrySet.indicatorVertices)
        setNormals(GeometrySet.indicatorNormals)
        setBuffer()
    }
}