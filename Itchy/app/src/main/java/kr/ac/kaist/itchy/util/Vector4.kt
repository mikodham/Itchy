package kr.ac.kaist.itchy.util

/** A class representing a 4-dimensional vector */
class Vector4: Vector {
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
    var w: Float
        get() = vec[3]
        set(value) {
            vec[3] = value
        }

    constructor(): super(4) {
        vec[0] = 0f
        vec[1] = 0f
        vec[2] = 0f
        vec[3] = 0f
    }

    constructor(x: Float, y: Float, z: Float, w: Float): super(4) {
        vec[0] = x
        vec[1] = y
        vec[2] = z
        vec[3] = w
    }

    constructor(vec4: FloatArray): super(4) {
        vec[0] = vec4[0]
        vec[1] = vec4[1]
        vec[2] = vec4[2]
        vec[3] = vec4[3]
    }

    constructor(vec4: Vector4): super(4) {
        vec[0] = vec4[0]
        vec[1] = vec4[1]
        vec[2] = vec4[2]
        vec[3] = vec4[3]
    }

    constructor(vec: Vector): super(4) {
        vec[0] = vec[0]
        vec[1] = vec[1]
        vec[2] = vec[2]
        vec[3] = vec[3]
    }

    constructor(vec3: Vector3): super(4) {
        vec[0] = vec3[0]
        vec[1] = vec3[1]
        vec[2] = vec3[2]
        vec[3] = 0f
    }

    constructor(vec3: Vector3, w: Float): super(4) {
        vec[0] = vec3[0]
        vec[1] = vec3[1]
        vec[2] = vec3[2]
        vec[3] = w
    }

    constructor(vec3: FloatArray, w: Float): super(4) {
        vec[0] = vec3[0]
        vec[1] = vec3[1]
        vec[2] = vec3[2]
        vec[3] = w
    }

    fun add(dx: Float, dy: Float, dz: Float, dw: Float) {
        x += dx
        y += dy
        z += dz
        w += dw
    }

    fun add(dx: Float, dy: Float, dz: Float) {
        add(dx, dy, dz, 0f)
    }

}