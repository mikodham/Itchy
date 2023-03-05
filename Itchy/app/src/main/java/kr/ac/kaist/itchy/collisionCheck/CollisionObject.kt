package kr.ac.kaist.itchy.collisionCheck

import android.opengl.Matrix
import kr.ac.kaist.itchy.gameEngine.RenderObject.Companion.COORDS_PER_VERTEX
import kr.ac.kaist.itchy.gameEngine.Transform
import kr.ac.kaist.itchy.util.Vector3

interface CollisionObject {

    fun intersects(obj: AABB): Boolean
    fun intersects(obj: BoundingSphere): Boolean
    fun update(vertices: FloatArray, transform: Transform)

    // takes the local object vertices and converts them to the global coordinate system
    fun getGlobalVertices(
        local_vert: FloatArray?,
        modelMatrix: FloatArray,
        scale: Vector3
    ): FloatArray {
        val globalVert = FloatArray(local_vert!!.size)
        for (i in globalVert.indices step COORDS_PER_VERTEX) {
            var lv3 = Vector3(
                local_vert[i] * scale.x,
                local_vert[i + 1] * scale.y, local_vert[i + 2] * scale.z
            )
            val lv4 = lv3.toHomogeneousPoint()
            val temp = FloatArray(4)
            Matrix.multiplyMV(temp, 0, modelMatrix, 0, lv4.toFloatArray(), 0)
            globalVert[i] = temp[0]
            globalVert[i + 1] = temp[1]
            globalVert[i + 2] = temp[2]
        }
        return globalVert
    }

}