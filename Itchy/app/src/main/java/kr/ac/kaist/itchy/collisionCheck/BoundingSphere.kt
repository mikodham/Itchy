package kr.ac.kaist.itchy.collisionCheck

import kr.ac.kaist.itchy.gameEngine.Transform
import kr.ac.kaist.itchy.util.Vector3
import kotlin.math.sqrt

//Sphere Wrapper for Collision checking between objects, currently only used for the blood point target
class BoundingSphere(
) : CollisionObject {
    var vertices: FloatArray? = null
    var radius: Float? = null
    var center: Vector3? = null
    val scale = 1f
    //var scaled: Boolean = false
    lateinit var transform: Transform

    constructor(verts: FloatArray, transform: Transform) : this() {
        this.transform = transform
        this.vertices = getGlobalVertices(verts, transform.modelMatrix, transform.scale)
        center = transform.pos
        radius = getRadius() * scale
    }

    private fun getRadius(): Float {
        var maxDistance = 0.0f
        for (i in 0 until vertices!!.size step 3) {
            val posVertex = Vector3(vertices!![i], vertices!![i + 1], vertices!![i + 2])
            var distance = center!!.getDistance(posVertex)
            if (distance > maxDistance) {
                maxDistance = distance
            }
        }
        return maxDistance
    }

    override fun intersects(aabb: AABB): Boolean {
        val x = aabb.minX.coerceAtLeast(center!!.x.coerceAtMost(aabb.maxX))
        val y = aabb.minY.coerceAtLeast(center!!.y.coerceAtMost(aabb.maxY))
        val z = aabb.minZ.coerceAtLeast(center!!.z.coerceAtMost(aabb.maxZ))

        var distance = sqrt(
            (x - center!!.x) * (x - center!!.x) +
                    (y - center!!.y) * (y - center!!.y) +
                    (z - center!!.z) * (z - center!!.z)
        )
        if (distance < radius!!) {
            return true
        }

        return distance < radius!!
    }

    override fun intersects(obj: BoundingSphere): Boolean {
        if (center!!.getDistance(obj.center!!) <= radius!! + obj.radius!!) {
            return true
        }
        return false
    }

    override fun update(verts: FloatArray, transform: Transform) {
        this.transform = transform
        this.vertices = getGlobalVertices(verts, transform.modelMatrix, transform.scale)
        /*if(!scaled) {
            radius = radius!! * transform.scale.x
            scaled = true
        }*/
        center = transform.pos
        radius = getRadius() * scale
    }


}