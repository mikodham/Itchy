package kr.ac.kaist.itchy.util

/** A class representing a 2-dimensional vector */
class Vector2: Vector {
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

    constructor(): super(2) {
        vec[0] = 0f
        vec[1] = 0f
    }

    constructor(x: Float, y: Float): super(2) {
        vec[0] = x
        vec[1] = y
    }

    constructor(vec2: FloatArray): super(2) {
        vec[0] = vec2[0]
        vec[1] = vec2[1]
    }

    constructor(vec2: Vector2): super(2) {
        vec[0] = vec2[0]
        vec[1] = vec2[1]
    }

    constructor(vec: Vector): super(2) {
        vec[0] = vec[0]
        vec[1] = vec[1]
    }

    fun add(dx: Float, dy: Float) {
        x += dx
        y += dy
    }
}