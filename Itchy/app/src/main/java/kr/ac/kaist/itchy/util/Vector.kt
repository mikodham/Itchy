package kr.ac.kaist.itchy.util

import kotlin.math.acos

/** A class representing a multi-dimensional vector */
open class Vector {
    var vec: FloatArray = FloatArray(3)
        private set
    val size: Int
        get() = vec.size

    //////////////// CONSTRUCTORS ////////////////
    constructor(n: Int) {
        vec = FloatArray(n)
        for (i in vec.indices)
            vec[i] = 0f
    }

    constructor(v: FloatArray) {
        vec = FloatArray(v.size)
        for (i in v.indices)
            vec[i] = v[i]
    }

    constructor(v: Vector?) {
        vec = FloatArray(v!!.size)
        for (i in v.vec.indices)
            vec[i] = v.vec[i]
    }
    /////////////////////////////////////////////

    fun toFloatArray(): FloatArray {
        val ret = FloatArray(size)
        for (i in vec.indices)
            ret[i] = vec[i]
        return ret
    }

    /** Set values of vector using input FloatArray.
     * Size of input Float Array should be greater than or equal to size of this Vector. */
    fun setValues(values: FloatArray) {
        for (i in vec.indices)
            vec[i] = values[i]
    }

    /** Set values of vector using input Vector.
     * Size of input Vector should be greater than or equal to size of this Vector. */
    fun setValues(values: Vector) {
        for (i in vec.indices)
            vec[i] = values[i]
    }

    //////////// ARITHMETIC METHODS /////////////
    fun add(rhs: Vector) {
        require(size == rhs.size) {"Vector addition should be applied to two same-dimensional vectors"}
        for (i in vec.indices)
            vec[i] += rhs[i]
    }

    fun subtract(rhs: Vector) {
        require(size == rhs.size) {"Vector addition should be applied to two same-dimensional vectors"}
        for (i in vec.indices)
            vec[i] -= rhs[i]
    }

    fun multiply(value: Float) {
        for (i in vec.indices)
            vec[i] *= value
    }

    fun div(value: Float) {
        multiply(1f/value)
    }
    /////////////////////////////////////////////

    ///////////////// OPERATORS /////////////////
    operator fun get(i: Int): Float {
        return vec[i]
    }

    operator fun set(i: Int, value: Float) {
        vec[i] = value
    }

    open operator fun plus(rhs: Vector): Vector {
        val ret = Vector(this)
        ret.add(rhs)
        return ret
    }

    open operator fun minus(rhs: Vector): Vector {
        val ret = Vector(this)
        ret.subtract(rhs)
        return ret
    }

    open operator fun times(s: Number): Vector {
        val ret = Vector(this)
        ret.multiply(s.toFloat())
        return ret
    }

    operator fun div(s: Number): Vector {
        val ret = Vector(this)
        ret.div(s.toFloat())
        return ret
    }
    /////////////////////////////////////////////

    ///////////// OVERLOADED METHODS /////////////
    override fun toString(): String {
        return "(" + vec.joinToString(", ") + ")"
    }
    /////////////////////////////////////////////

    ////////////// STATIC METHODS //////////////
    companion object {
        /** Returns a dot product of two Vectors */
        fun dot(lhs: Vector, rhs: Vector): Float {
            require(lhs.size == rhs.size) {"Dot product should be applied to two same-dimensional vectors"}
            var sum = 0f
            for (i in lhs.vec.indices)
                sum += lhs[i] * rhs[i]
            return sum
        }

        /** Returns a Angle between two Vectors in degrees */
        fun getBetweenAngle(lhs: Vector, rhs: Vector): Float {
            return Math.toDegrees(
                acos(clamp(
                    dot(normalize(lhs), normalize(rhs)).toDouble(),
                0 + Constants.EPSILON, 1 - Constants.EPSILON))
            ).toFloat()
        }

        /** Calculate a cross product of two Vector3-s. Then save it to result */
        fun cross(p1: Vector3, p2: Vector3, result: Vector3) {
            val tmp = FloatArray(3)
            cross(p1.vec, p2.vec, tmp)
            result[0] = tmp[0]
            result[1] = tmp[1]
            result[2] = tmp[2]
        }

        /** Calculate a cross product of two Vector3-s. Then save it to result.
         * This function uses only first three elements of p1 and p2 */
        fun cross(p1: FloatArray, p2: FloatArray, result: FloatArray) {
            result[0] = p1[1] * p2[2] - p2[1] * p1[2]
            result[1] = p1[2] * p2[0] - p2[2] * p1[0]
            result[2] = p1[0] * p2[1] - p2[0] * p1[1]
        }

        /** Returns a squared norm of Vector */
        fun norm2(v: Vector): Float {
            return dot(v, v)
        }

        /** Returns a norm of Vector */
        fun norm(v: Vector): Float {
            return Math.sqrt(norm2(v).toDouble()).toFloat()
        }

        /** Returns a normalized Vector */
        fun normalize(v: Vector): Vector {
            val temp = norm(v)
            return v * (1.0f / temp)
        }
    }
    /////////////////////////////////////////////
}