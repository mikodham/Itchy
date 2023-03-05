package kr.ac.kaist.itchy.util

import android.opengl.Matrix
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/** A class representing a quaternion */
class Quaternion {
    private val quat = FloatArray(4)

    val w: Float
        get() = quat[0]
    val x: Float
        get() = quat[1]
    val y: Float
        get() = quat[2]
    val z: Float
        get() = quat[3]
    val k: Vector3
        get() = Vector3(x, y, z)


    constructor() {
        quat[0] = 1f
        quat[1] = 0f
        quat[2] = 0f
        quat[3] = 0f
    }

    constructor(q: Quaternion?) {
        quat[0] = q!!.quat[0]
        quat[1] = q.quat[1]
        quat[2] = q.quat[2]
        quat[3] = q.quat[3]
    }

    constructor(w: Float, vec3: FloatArray) {
        quat[0] = w
        quat[1] = vec3[0]
        quat[2] = vec3[1]
        quat[3] = vec3[2]
    }

    constructor(w: Float, vec3: Vector3) {
        quat[0] = w
        quat[1] = vec3[0]
        quat[2] = vec3[1]
        quat[3] = vec3[2]
    }

    constructor(w: Float, x: Float, y: Float, z: Float) {
        quat[0] = w
        quat[1] = x
        quat[2] = y
        quat[3] = z
    }

    override fun toString(): String {
        return "(" + quat.joinToString(", ") + ")"
    }

    /** Set values of quaternion using input quaternion. */
    fun setValues(values: Quaternion) {
        for (i in quat.indices)
            quat[i] = values[i]
    }

    /** Set values of quaternion using input FloatArray.
     * Size of input FloatArray should be greater than or equal to 4. */
    fun setValues(values: FloatArray) {
        for (i in quat.indices)
            quat[i] = values[i]
    }

    operator fun get(i: Int): Float {
        return quat[i]
    }

    operator fun set(i: Int, value: Float) {
        quat[i] = value
    }

    operator fun times(d: Number): Quaternion {
        val q = Quaternion(this)
        q.multiply(d.toFloat())
        return q
    }

    operator fun plus(q1: Quaternion): Quaternion {
        val q0 = Quaternion(this)
        q0.add(q1)
        return q0
    }

    fun add(q: Quaternion) {
        quat[0] += q.quat[0]
        quat[1] += q.quat[1]
        quat[2] += q.quat[2]
        quat[3] += q.quat[3]
    }

    fun subtract(q: Quaternion) {
        quat[0] -= q.quat[0]
        quat[1] -= q.quat[1]
        quat[2] -= q.quat[2]
        quat[3] -= q.quat[3]
    }

    fun multiply(s: Float) {
        quat[0] *= s
        quat[1] *= s
        quat[2] *= s
        quat[3] *= s
    }

    fun multiply(q: Quaternion): Quaternion {
        val y0 =
            quat[0] * q.quat[0] - quat[1] * q.quat[1] - quat[2] * q.quat[2] - quat[3] * q.quat[3]
        val y1 =
            quat[0] * q.quat[1] + quat[1] * q.quat[0] + quat[2] * q.quat[3] - quat[3] * q.quat[2]
        val y2 =
            quat[0] * q.quat[2] - quat[1] * q.quat[3] + quat[2] * q.quat[0] + quat[3] * q.quat[1]
        val y3 =
            quat[0] * q.quat[3] + quat[1] * q.quat[2] - quat[2] * q.quat[1] + quat[3] * q.quat[0]
        return Quaternion(y0, y1, y2, y3)
    }

    //float[4]
    fun multiply(a: FloatArray): FloatArray {
        return floatArrayOf(
            a[0] * quat[0] - a[1] * quat[1] - a[2] * quat[2] - a[3] * quat[3],
            a[0] * quat[1] + a[1] * quat[0] - a[2] * quat[3] + a[3] * quat[2],
            a[0] * quat[2] + a[1] * quat[3] + a[2] * quat[0] - a[3] * quat[1],
            a[0] * quat[3] - a[1] * quat[2] + a[2] * quat[1] + a[3] * quat[0]
        )
    }

    companion object {
        fun makeXRotation(ang: Double): Quaternion {
            val r = Quaternion()
            val h = 0.5 * ang * Constants.CS175_PI / 180
            r.quat[1] = Math.sin(h).toFloat()
            r.quat[0] = Math.cos(h).toFloat()
            return r
        }

        fun makeYRotation(ang: Double): Quaternion {
            val r = Quaternion()
            val h = 0.5 * ang * Constants.CS175_PI / 180
            r.quat[2] = Math.sin(h).toFloat()
            r.quat[0] = Math.cos(h).toFloat()
            return r
        }

        fun makeZRotation(ang: Double): Quaternion {
            val r = Quaternion()
            val h = 0.5 * ang * Constants.CS175_PI / 180
            r.quat[3] = Math.sin(h).toFloat()
            r.quat[0] = Math.cos(h).toFloat()
            return r
        }

        fun dot(q1: Quaternion, q2: Quaternion): Float {
            var s = 0f
            for (i in 0..3) {
                s += q1.quat[i] * q2.quat[i]
            }
            return s
        }

        fun getQuat(rad: Float, k: Vector3): Quaternion {
            return Quaternion(cos(rad/2f), k * sin(rad/2f))
        }

        fun norm2(q1: Quaternion): Float {
            return dot(q1, q1)
        }

        fun inv(q: Quaternion): Quaternion {
            val n = norm2(q)
            assert(n > Constants.CS175_EPS2)
            return Quaternion(q.quat[0], -q.quat[1], -q.quat[2], (-q.quat[3] * (1.0 / n)).toFloat())
        }

        fun normalize(q: Quaternion): Quaternion {
            val temp = Math.sqrt(norm2(q).toDouble()).toFloat()
            q.multiply(1.0f / temp)
            return Quaternion(q)
        }

        fun quatToMat(q: Quaternion): FloatArray {
            val temp = FloatArray(16)
            for (i in 0..15) {
                temp[i] = 0f
            }
            val n = norm2(q)
            if (n < Constants.CS175_EPS2) {
                return temp
            }
            Matrix.setIdentityM(temp, 0)
            val two_over_n = 2 / n
            temp[0] -= (q[2] * q[2] + q[3] * q[3]) * two_over_n
            temp[1] += (q[1] * q[2] + q[0] * q[3]) * two_over_n
            temp[2] += (q[1] * q[3] - q[2] * q[0]) * two_over_n
            temp[4] += (q[1] * q[2] - q[0] * q[3]) * two_over_n
            temp[5] -= (q[1] * q[1] + q[3] * q[3]) * two_over_n
            temp[6] += (q[2] * q[3] + q[1] * q[0]) * two_over_n
            temp[8] += (q[1] * q[3] + q[2] * q[0]) * two_over_n
            temp[9] += (q[2] * q[3] - q[1] * q[0]) * two_over_n
            temp[10] -= (q[1] * q[1] + q[2] * q[2]) * two_over_n
            //assert (isAffine(temp));
            return temp
        }

        /** Returns a Quaternion using input affine Matrix
         * This function is from
         * https://www.euclideanspace.com/maths/geometry/rotations/conversions/matrixToQuaternion/ */
        fun matToQuat(m: FloatArray): Quaternion {
            val w = (sqrt(1.0 + m[0] + m[5] + m[10]) / 2.0).toFloat()
            val w4 = 4.0f * w
            val x = (m[6] - m[9]) / w4
            val y = (m[8] - m[2]) / w4
            val z = (m[1] - m[4]) / w4
            val q = Quaternion(w, x, y, z)
            return q
        }
    }
}