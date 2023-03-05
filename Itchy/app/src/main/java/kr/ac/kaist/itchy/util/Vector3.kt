package kr.ac.kaist.itchy.util

import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt

/** A class representing a 3-dimensional vector */
class Vector3: Vector {
    var x: Float
        get() = vec[0]
        set(value) {
            vec[0] = value
        }
    var y: Float
        get() = vec[1]
        set(value) {
            vec[1] = value
        }
    var z: Float
        get() = vec[2]
        set(value) {
            vec[2] = value
        }

    constructor(): super(3) {
        vec[0] = 0f
        vec[1] = 0f
        vec[2] = 0f
    }

    constructor(x: Float, y: Float, z: Float): super(3) {
        vec[0] = x
        vec[1] = y
        vec[2] = z
    }

    constructor(vec3: FloatArray): super(3) {
        vec[0] = vec3[0]
        vec[1] = vec3[1]
        vec[2] = vec3[2]
    }

    constructor(vec3: Vector3): super(3) {
        vec[0] = vec3[0]
        vec[1] = vec3[1]
        vec[2] = vec3[2]
    }

    constructor(vec: Vector): super(3) {
        this.vec[0] = vec[0]
        this.vec[1] = vec[1]
        this.vec[2] = vec[2]
    }

    fun toHomogeneousPoint(): Vector4 {
        return Vector4(this, 1f)
    }

    fun toHomogeneousVector(): Vector4 {
        return Vector4(this, 0f)
    }

    fun add(dx: Float, dy: Float, dz: Float) {
        x += dx
        y += dy
        z += dz
    }

    override operator fun plus(rhs: Vector): Vector3 {
        val ret = Vector3(this)
        ret.add(rhs)
        return ret
    }

    override operator fun minus(rhs: Vector): Vector3 {
        val ret = Vector3(this)
        ret.subtract(rhs)
        return ret
    }

    override operator fun times(s: Number): Vector3 {
        val ret = Vector3(this)
        ret.multiply(s.toFloat())
        return ret
    }

    fun setValues(x: Float, y: Float, z: Float) {
        this.x = x
        this.y = y
        this.z = z
    }

    fun getDistance(v2 : Vector3) : Float {
        // Pythagorean distance
        return sqrt((x - v2.x).pow(2) + (y - v2.y).pow(2) + (z - v2.z).pow(2))
    }

    companion object {
        val forward = Vector3(0f, 0f, -1f)
        val backward = Vector3(0f, 0f, 1f)
        val up = Vector3(0f, 1f, 0f)
        val down = Vector3(0f, -1f, 0f)
        val left = Vector3(-1f, 0f, 0f)
        val right = Vector3(1f, 0f, 0f)
        val zeros = Vector3(0f, 0f, 0f)
        val ones = Vector3(1f, 1f, 1f)
    }
}