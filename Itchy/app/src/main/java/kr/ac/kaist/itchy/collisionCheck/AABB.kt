package kr.ac.kaist.itchy.collisionCheck

import kr.ac.kaist.itchy.gameEngine.Transform
import kr.ac.kaist.itchy.renderObjects.LaserModel
import kr.ac.kaist.itchy.renderObjects.MosquitoModel
import kr.ac.kaist.itchy.renderObjects.RoomModel
import kotlin.math.sqrt

//Axis-aligned bounding box for collision detection between objects
class AABB(
) : CollisionObject {
    var minX = Float.POSITIVE_INFINITY
    var maxX = Float.NEGATIVE_INFINITY
    var minY = Float.POSITIVE_INFINITY
    var maxY = Float.NEGATIVE_INFINITY
    var minZ = Float.POSITIVE_INFINITY
    var maxZ = Float.NEGATIVE_INFINITY
    var vertices: FloatArray? = null
    lateinit var transform: Transform

    //value for scaling the AABBs down to make collision feel more natural
    val scale = 0.8f

    constructor(verts: FloatArray, transform: Transform) : this() {
        this.transform = transform
        this.vertices = getGlobalVertices(verts, transform.modelMatrix, transform.scale)
        findMinMax()
    }

    private fun findMinMax() {
        //iterate over all the vertices and find min & max values for x, y,
        for (i in 0 until vertices!!.size step 3) {
            //X check
            if (vertices!![i] < minX)
                minX = vertices!![i]
            if (vertices!![i] > maxX)
                maxX = vertices!![i]
            //Y check
            if (vertices!![i + 1] < minY)
                minY = vertices!![i + 1]
            if (vertices!![i + 1] > maxY)
                maxY = vertices!![i + 1]
            //Z check
            if (vertices!![i + 2] < minZ)
                minZ = vertices!![i + 2]
            if (vertices!![i + 2] > maxZ)
                maxZ = vertices!![i + 2]
        }
        //println("MinX: " + minX)
        //println("MaxX: " + maxX)
        // don't rescale the room and the lasers
        if (transform.gameObject.mRenderObject!! !is RoomModel
            && transform.gameObject.mRenderObject!! !is LaserModel
            //&& transform.gameObject.mRenderObject!! !is MosquitoModel
        ) {
            rescale(minY, maxY, 'Y')
            rescale(minZ, maxZ, 'Z')
            rescale(minX, maxX, 'X')
        }
        //println("new MinX: " + minX)
        //println("New maxX: " + maxX)
        //println("br")
    }

    private fun rescale(min: Float, max: Float, axis: Char) {
        val newMin = (min + max) / 2 - (max - min) / 2 * scale
        val newMax = (min + max) / 2 + (max - min) / 2 * scale
        when (axis) {
            'X' -> {
                minX = newMin
                maxX = newMax
            }
            'Y' -> {
                minY = newMin
                maxY = newMax
            }
            else -> {
                minZ = newMin
                maxZ = newMax
            }
        }
    }

    override fun intersects(obj: AABB): Boolean {
        var bool = minX <= obj.maxX &&
                maxX >= obj.minX &&
                minY <= obj.maxY &&
                maxY >= obj.minY &&
                minZ <= obj.maxZ &&
                maxZ >= obj.minZ
        return bool
    }

    override fun intersects(sphere: BoundingSphere): Boolean {
        val x = minX.coerceAtLeast(sphere.center!!.x.coerceAtMost(maxX))
        val y = minY.coerceAtLeast(sphere.center!!.y.coerceAtMost(maxY))
        val z = minZ.coerceAtLeast(sphere.center!!.z.coerceAtMost(maxZ))

        val distance = sqrt(
            (x - sphere.center!!.x) * (x - sphere.center!!.x) +
                    (y - sphere.center!!.y) * (y - sphere.center!!.y) +
                    (z - sphere.center!!.z) * (z - sphere.center!!.z)
        )

        return distance < sphere.radius!!
    }

    override fun update(verts: FloatArray, transform: Transform) {
        resetMinMax()
        this.transform = transform
        this.vertices = getGlobalVertices(verts, transform.modelMatrix, transform.scale)
        findMinMax()
    }

    private fun resetMinMax() {
        minX = Float.POSITIVE_INFINITY
        maxX = Float.NEGATIVE_INFINITY
        minY = Float.POSITIVE_INFINITY
        maxY = Float.NEGATIVE_INFINITY
        minZ = Float.POSITIVE_INFINITY
        maxZ = Float.NEGATIVE_INFINITY
    }


}