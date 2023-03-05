package kr.ac.kaist.itchy.renderObjects

/**
 * Created by PCPC on 2016-10-09.
 */
object GeometrySet {
    val cubeVertices = floatArrayOf( // Front face
        -1.0f, 1.0f, 1.0f,
        -1.0f, -1.0f, 1.0f,
        1.0f, 1.0f, 1.0f,
        -1.0f, -1.0f, 1.0f,
        1.0f, -1.0f, 1.0f,
        1.0f, 1.0f, 1.0f,  // Right face
        1.0f, 1.0f, 1.0f,
        1.0f, -1.0f, 1.0f,
        1.0f, 1.0f, -1.0f,
        1.0f, -1.0f, 1.0f,
        1.0f, -1.0f, -1.0f,
        1.0f, 1.0f, -1.0f,  // Back face
        1.0f, 1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        -1.0f, 1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        -1.0f, -1.0f, -1.0f,
        -1.0f, 1.0f, -1.0f,  // Left face
        -1.0f, 1.0f, -1.0f,
        -1.0f, -1.0f, -1.0f,
        -1.0f, 1.0f, 1.0f,
        -1.0f, -1.0f, -1.0f,
        -1.0f, -1.0f, 1.0f,
        -1.0f, 1.0f, 1.0f,  // Top face
        -1.0f, 1.0f, -1.0f,
        -1.0f, 1.0f, 1.0f,
        1.0f, 1.0f, -1.0f,
        -1.0f, 1.0f, 1.0f,
        1.0f, 1.0f, 1.0f,
        1.0f, 1.0f, -1.0f,  // Bottom face
        1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, 1.0f,
        -1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, 1.0f,
        -1.0f, -1.0f, 1.0f,
        -1.0f, -1.0f, -1.0f
    )
    val cubeNormals = floatArrayOf( // Front face
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,  // Right face
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,  // Back face
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, -1.0f,  // Left face
        -1.0f, 0.0f, 0.0f,
        -1.0f, 0.0f, 0.0f,
        -1.0f, 0.0f, 0.0f,
        -1.0f, 0.0f, 0.0f,
        -1.0f, 0.0f, 0.0f,
        -1.0f, 0.0f, 0.0f,  // Top face
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,  // Bottom face
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f
    )
    val cylinderVertices = floatArrayOf(
            0.0f, 1.0f, -1.0f,
            0.7f, -1.0f, -0.7f,
            0.0f, -1.0f, -1.0f,
            0.7f, 1.0f, -0.7f,
            1.0f, -1.0f, 0.0f,
            0.7f, -1.0f, -0.7f,
            1.0f, 1.0f, 0.0f,
            0.7f, -1.0f, 0.7f,
            1.0f, -1.0f, 0.0f,
            0.7f, 1.0f, 0.7f,
            0.0f, -1.0f, 1.0f,
            0.7f, -1.0f, 0.7f,
            0.0f, 1.0f, 1.0f,
            -0.7f, -1.0f, 0.7f,
            0.0f, -1.0f, 1.0f,
            -0.7f, 1.0f, 0.7f,
            -1.0f, -1.0f, 0.0f,
            -0.7f, -1.0f, 0.7f,
            -1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 0.0f,
            -1.0f, 1.0f, 0.0f,
            -0.7f, -1.0f, -0.7f,
            -1.0f, -1.0f, 0.0f,
            -0.7f, 1.0f, -0.7f,
            0.0f, -1.0f, -1.0f,
            -0.7f, -1.0f, -0.7f,
            0.7f, -1.0f, 0.7f,
            -0.7f, -1.0f, 0.7f,
            -0.7f, -1.0f, -0.7f,
            0.0f, 1.0f, -1.0f,
            0.7f, 1.0f, -0.7f,
            0.7f, -1.0f, -0.7f,
            0.7f, 1.0f, -0.7f,
            1.0f, 1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            0.7f, 1.0f, 0.7f,
            0.7f, -1.0f, 0.7f,
            0.7f, 1.0f, 0.7f,
            0.0f, 1.0f, 1.0f,
            0.0f, -1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            -0.7f, 1.0f, 0.7f,
            -0.7f, -1.0f, 0.7f,
            -0.7f, 1.0f, 0.7f,
            -1.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            0.7f, 1.0f, -0.7f,
            0.0f, 1.0f, -1.0f,
            0.0f, 1.0f, -1.0f,
            -0.7f, 1.0f, -0.7f,
            -1.0f, 1.0f, 0.0f,
            -1.0f, 1.0f, 0.0f,
            -0.7f, 1.0f, 0.7f,
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            0.7f, 1.0f, 0.7f,
            1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, 0.0f,
            -1.0f, 1.0f, 0.0f,
            -0.7f, 1.0f, -0.7f,
            -0.7f, -1.0f, -0.7f,
            -0.7f, 1.0f, -0.7f,
            0.0f, 1.0f, -1.0f,
            0.0f, -1.0f, -1.0f,
            -0.7f, -1.0f, -0.7f,
            0.0f, -1.0f, -1.0f,
            0.7f, -1.0f, -0.7f,
            0.7f, -1.0f, -0.7f,
            1.0f, -1.0f, 0.0f,
            0.7f, -1.0f, 0.7f,
            0.7f, -1.0f, 0.7f,
            0.0f, -1.0f, 1.0f,
            -0.7f, -1.0f, 0.7f,
            -0.7f, -1.0f, 0.7f,
            -1.0f, -1.0f, 0.0f,
            -0.7f, -1.0f, -0.7f,
            -0.7f, -1.0f, -0.7f,
            0.7f, -1.0f, -0.7f,
            0.7f, -1.0f, 0.7f,
    )
    val cylinderNormals = floatArrayOf(
            0.38f, -0.0f, -0.9f,
            0.38f, -0.0f, -0.9f,
            0.38f, -0.0f, -0.9f,
            0.92f, -0.0f, -0.3f,
            0.92f, -0.0f, -0.3f,
            0.92f, -0.0f, -0.3f,
            0.92f, -0.0f, 0.38f,
            0.92f, -0.0f, 0.38f,
            0.92f, -0.0f, 0.38f,
            0.38f, -0.0f, 0.92f,
            0.38f, -0.0f, 0.92f,
            0.38f, -0.0f, 0.92f,
            -0.3f, -0.0f, 0.92f,
            -0.3f, -0.0f, 0.92f,
            -0.3f, -0.0f, 0.92f,
            -0.9f, -0.0f, 0.38f,
            -0.9f, -0.0f, 0.38f,
            -0.9f, -0.0f, 0.38f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.9f, -0.0f, -0.3f,
            -0.9f, -0.0f, -0.3f,
            -0.9f, -0.0f, -0.3f,
            -0.3f, -0.0f, -0.9f,
            -0.3f, -0.0f, -0.9f,
            -0.3f, -0.0f, -0.9f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            0.38f, -0.0f, -0.9f,
            0.38f, -0.0f, -0.9f,
            0.38f, -0.0f, -0.9f,
            0.92f, -0.0f, -0.3f,
            0.92f, -0.0f, -0.3f,
            0.92f, -0.0f, -0.3f,
            0.92f, -0.0f, 0.38f,
            0.92f, -0.0f, 0.38f,
            0.92f, -0.0f, 0.38f,
            0.38f, -0.0f, 0.92f,
            0.38f, -0.0f, 0.92f,
            0.38f, -0.0f, 0.92f,
            -0.3f, -0.0f, 0.92f,
            -0.3f, -0.0f, 0.92f,
            -0.3f, -0.0f, 0.92f,
            -0.9f, -0.0f, 0.38f,
            -0.9f, -0.0f, 0.38f,
            -0.9f, -0.0f, 0.38f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.9f, -0.0f, -0.3f,
            -0.9f, -0.0f, -0.3f,
            -0.9f, -0.0f, -0.3f,
            -0.3f, -0.0f, -0.9f,
            -0.3f, -0.0f, -0.9f,
            -0.3f, -0.0f, -0.9f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
    )
    val squareVertices = floatArrayOf(
        -1.0f, 1.0f, 0.0f,
        -1.0f, -1.0f, 0.0f,
        1.0f, 1.0f, 0.0f,
        -1.0f, -1.0f, 0.0f,
        1.0f, -1.0f, 0.0f,
        1.0f, 1.0f, 0.0f
    )
    val squareNormals = floatArrayOf(
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f
    )
    val sphereVertices = floatArrayOf(
            0.0f, 0.866f, -0.5f,
            0.612f, 0.5f, -0.61f,
            0.0f, 0.5f, -0.86f,
            0.0f, -0.5f, -0.86f,
            0.353f, -0.86f, -0.35f,
            0.0f, -0.86f, -0.5f,
            0.0f, 0.5f, -0.86f,
            0.707f, 0.0f, -0.7f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.866f, -0.5f,
            0.0f, 1.0f, 0.0f,
            0.353f, 0.866f, -0.35f,
            0.0f, -1.0f, 0.0f,
            0.0f, -0.86f, -0.5f,
            0.353f, -0.86f, -0.35f,
            0.0f, 0.0f, -1.0f,
            0.612f, -0.5f, -0.61f,
            0.0f, -0.5f, -0.86f,
            0.612f, -0.5f, -0.61f,
            0.5f, -0.86f, 0.0f,
            0.353f, -0.86f, -0.35f,
            0.612f, 0.5f, -0.61f,
            1.0f, 0.0f, 0.0f,
            0.707f, 0.0f, -0.7f,
            0.353f, 0.866f, -0.35f,
            0.0f, 1.0f, 0.0f,
            0.5f, 0.866f, 0.0f,
            0.0f, -1.0f, 0.0f,
            0.353f, -0.86f, -0.35f,
            0.5f, -0.86f, 0.0f,
            0.707f, 0.0f, -0.7f,
            0.866f, -0.5f, 0.0f,
            0.612f, -0.5f, -0.61f,
            0.353f, 0.866f, -0.35f,
            0.866f, 0.5f, 0.0f,
            0.612f, 0.5f, -0.61f,
            0.866f, -0.5f, 0.0f,
            0.353f, -0.86f, 0.353f,
            0.5f, -0.86f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.612f, 0.5f, 0.612f,
            0.707f, 0.0f, 0.707f,
            0.5f, 0.866f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.353f, 0.866f, 0.353f,
            0.0f, -1.0f, 0.0f,
            0.5f, -0.86f, 0.0f,
            0.353f, -0.86f, 0.353f,
            0.866f, -0.5f, 0.0f,
            0.707f, 0.0f, 0.707f,
            0.612f, -0.5f, 0.612f,
            0.5f, 0.866f, 0.0f,
            0.612f, 0.5f, 0.612f,
            0.866f, 0.5f, 0.0f,
            0.612f, 0.5f, 0.612f,
            0.0f, 0.0f, 1.0f,
            0.707f, 0.0f, 0.707f,
            0.353f, 0.866f, 0.353f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.866f, 0.5f,
            0.0f, -1.0f, 0.0f,
            0.353f, -0.86f, 0.353f,
            0.0f, -0.86f, 0.5f,
            0.707f, 0.0f, 0.707f,
            0.0f, -0.5f, 0.866f,
            0.612f, -0.5f, 0.612f,
            0.353f, 0.866f, 0.353f,
            0.0f, 0.5f, 0.866f,
            0.612f, 0.5f, 0.612f,
            0.612f, -0.5f, 0.612f,
            0.0f, -0.86f, 0.5f,
            0.353f, -0.86f, 0.353f,
            0.0f, 0.866f, 0.5f,
            0.0f, 1.0f, 0.0f,
            -0.35f, 0.866f, 0.353f,
            0.0f, -1.0f, 0.0f,
            0.0f, -0.86f, 0.5f,
            -0.35f, -0.86f, 0.353f,
            0.0f, 0.0f, 1.0f,
            -0.61f, -0.5f, 0.612f,
            0.0f, -0.5f, 0.866f,
            0.0f, 0.866f, 0.5f,
            -0.61f, 0.5f, 0.612f,
            0.0f, 0.5f, 0.866f,
            0.0f, -0.5f, 0.866f,
            -0.35f, -0.86f, 0.353f,
            0.0f, -0.86f, 0.5f,
            0.0f, 0.5f, 0.866f,
            -0.7f, 0.0f, 0.707f,
            0.0f, 0.0f, 1.0f,
            0.0f, -1.0f, 0.0f,
            -0.35f, -0.86f, 0.353f,
            -0.5f, -0.86f, 0.0f,
            -0.7f, 0.0f, 0.707f,
            -0.86f, -0.5f, 0.0f,
            -0.61f, -0.5f, 0.612f,
            -0.35f, 0.866f, 0.353f,
            -0.86f, 0.5f, 0.0f,
            -0.61f, 0.5f, 0.612f,
            -0.61f, -0.5f, 0.612f,
            -0.5f, -0.86f, 0.0f,
            -0.35f, -0.86f, 0.353f,
            -0.61f, 0.5f, 0.612f,
            -1.0f, 0.0f, 0.0f,
            -0.7f, 0.0f, 0.707f,
            -0.35f, 0.866f, 0.353f,
            0.0f, 1.0f, 0.0f,
            -0.5f, 0.866f, 0.0f,
            0.0f, -1.0f, 0.0f,
            -0.5f, -0.86f, 0.0f,
            -0.35f, -0.86f, -0.35f,
            -1.0f, 0.0f, 0.0f,
            -0.61f, -0.5f, -0.61f,
            -0.86f, -0.5f, 0.0f,
            -0.5f, 0.866f, 0.0f,
            -0.61f, 0.5f, -0.61f,
            -0.86f, 0.5f, 0.0f,
            -0.86f, -0.5f, 0.0f,
            -0.35f, -0.86f, -0.35f,
            -0.5f, -0.86f, 0.0f,
            -0.86f, 0.5f, 0.0f,
            -0.7f, 0.0f, -0.7f,
            -1.0f, 0.0f, 0.0f,
            -0.5f, 0.866f, 0.0f,
            0.0f, 1.0f, 0.0f,
            -0.35f, 0.866f, -0.35f,
            0.0f, -1.0f, 0.0f,
            -0.35f, -0.86f, -0.35f,
            0.0f, -0.86f, -0.5f,
            -0.7f, 0.0f, -0.7f,
            0.0f, -0.5f, -0.86f,
            -0.61f, -0.5f, -0.61f,
            -0.35f, 0.866f, -0.35f,
            0.0f, 0.5f, -0.86f,
            -0.61f, 0.5f, -0.61f,
            -0.61f, -0.5f, -0.61f,
            0.0f, -0.86f, -0.5f,
            -0.35f, -0.86f, -0.35f,
            -0.61f, 0.5f, -0.61f,
            0.0f, 0.0f, -1.0f,
            -0.7f, 0.0f, -0.7f,
            -0.35f, 0.866f, -0.35f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.866f, -0.5f,
            0.0f, 0.866f, -0.5f,
            0.353f, 0.866f, -0.35f,
            0.612f, 0.5f, -0.61f,
            0.0f, -0.5f, -0.86f,
            0.612f, -0.5f, -0.61f,
            0.353f, -0.86f, -0.35f,
            0.0f, 0.5f, -0.86f,
            0.612f, 0.5f, -0.61f,
            0.707f, 0.0f, -0.7f,
            0.0f, 0.0f, -1.0f,
            0.707f, 0.0f, -0.7f,
            0.612f, -0.5f, -0.61f,
            0.612f, -0.5f, -0.61f,
            0.866f, -0.5f, 0.0f,
            0.5f, -0.86f, 0.0f,
            0.612f, 0.5f, -0.61f,
            0.866f, 0.5f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.707f, 0.0f, -0.7f,
            1.0f, 0.0f, 0.0f,
            0.866f, -0.5f, 0.0f,
            0.353f, 0.866f, -0.35f,
            0.5f, 0.866f, 0.0f,
            0.866f, 0.5f, 0.0f,
            0.866f, -0.5f, 0.0f,
            0.612f, -0.5f, 0.612f,
            0.353f, -0.86f, 0.353f,
            1.0f, 0.0f, 0.0f,
            0.866f, 0.5f, 0.0f,
            0.612f, 0.5f, 0.612f,
            0.866f, -0.5f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.707f, 0.0f, 0.707f,
            0.5f, 0.866f, 0.0f,
            0.353f, 0.866f, 0.353f,
            0.612f, 0.5f, 0.612f,
            0.612f, 0.5f, 0.612f,
            0.0f, 0.5f, 0.866f,
            0.0f, 0.0f, 1.0f,
            0.707f, 0.0f, 0.707f,
            0.0f, 0.0f, 1.0f,
            0.0f, -0.5f, 0.866f,
            0.353f, 0.866f, 0.353f,
            0.0f, 0.866f, 0.5f,
            0.0f, 0.5f, 0.866f,
            0.612f, -0.5f, 0.612f,
            0.0f, -0.5f, 0.866f,
            0.0f, -0.86f, 0.5f,
            0.0f, 0.0f, 1.0f,
            -0.7f, 0.0f, 0.707f,
            -0.61f, -0.5f, 0.612f,
            0.0f, 0.866f, 0.5f,
            -0.35f, 0.866f, 0.353f,
            -0.61f, 0.5f, 0.612f,
            0.0f, -0.5f, 0.866f,
            -0.61f, -0.5f, 0.612f,
            -0.35f, -0.86f, 0.353f,
            0.0f, 0.5f, 0.866f,
            -0.61f, 0.5f, 0.612f,
            -0.7f, 0.0f, 0.707f,
            -0.7f, 0.0f, 0.707f,
            -1.0f, 0.0f, 0.0f,
            -0.86f, -0.5f, 0.0f,
            -0.35f, 0.866f, 0.353f,
            -0.5f, 0.866f, 0.0f,
            -0.86f, 0.5f, 0.0f,
            -0.61f, -0.5f, 0.612f,
            -0.86f, -0.5f, 0.0f,
            -0.5f, -0.86f, 0.0f,
            -0.61f, 0.5f, 0.612f,
            -0.86f, 0.5f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -0.7f, 0.0f, -0.7f,
            -0.61f, -0.5f, -0.61f,
            -0.5f, 0.866f, 0.0f,
            -0.35f, 0.866f, -0.35f,
            -0.61f, 0.5f, -0.61f,
            -0.86f, -0.5f, 0.0f,
            -0.61f, -0.5f, -0.61f,
            -0.35f, -0.86f, -0.35f,
            -0.86f, 0.5f, 0.0f,
            -0.61f, 0.5f, -0.61f,
            -0.7f, 0.0f, -0.7f,
            -0.7f, 0.0f, -0.7f,
            0.0f, 0.0f, -1.0f,
            0.0f, -0.5f, -0.86f,
            -0.35f, 0.866f, -0.35f,
            0.0f, 0.866f, -0.5f,
            0.0f, 0.5f, -0.86f,
            -0.61f, -0.5f, -0.61f,
            0.0f, -0.5f, -0.86f,
            0.0f, -0.86f, -0.5f,
            -0.61f, 0.5f, -0.61f,
            0.0f, 0.5f, -0.86f,
            0.0f, 0.0f, -1.0f,
        )
    val sphereNormal = floatArrayOf(
        0.281f, 0.678f, -0.67f,
        0.281f, 0.678f, -0.67f,
        0.281f, 0.678f, -0.67f,
        0.281f, -0.67f, -0.67f,
        0.281f, -0.67f, -0.67f,
        0.281f, -0.67f, -0.67f,
        0.371f, 0.24f, -0.89f,
        0.371f, 0.24f, -0.89f,
        0.371f, 0.24f, -0.89f,
        0.106f, 0.96f, -0.25f,
        0.106f, 0.96f, -0.25f,
        0.106f, 0.96f, -0.25f,
        0.106f, -0.96f, -0.25f,
        0.106f, -0.96f, -0.25f,
        0.106f, -0.96f, -0.25f,
        0.371f, -0.24f, -0.89f,
        0.371f, -0.24f, -0.89f,
        0.371f, -0.24f, -0.89f,
        0.678f, -0.67f, -0.28f,
        0.678f, -0.67f, -0.28f,
        0.678f, -0.67f, -0.28f,
        0.896f, 0.24f, -0.37f,
        0.896f, 0.24f, -0.37f,
        0.896f, 0.24f, -0.37f,
        0.257f, 0.96f, -0.1f,
        0.257f, 0.96f, -0.1f,
        0.257f, 0.96f, -0.1f,
        0.257f, -0.96f, -0.1f,
        0.257f, -0.96f, -0.1f,
        0.257f, -0.96f, -0.1f,
        0.896f, -0.24f, -0.37f,
        0.896f, -0.24f, -0.37f,
        0.896f, -0.24f, -0.37f,
        0.678f, 0.678f, -0.28f,
        0.678f, 0.678f, -0.28f,
        0.678f, 0.678f, -0.28f,
        0.678f, -0.67f, 0.281f,
        0.678f, -0.67f, 0.281f,
        0.678f, -0.67f, 0.281f,
        0.896f, 0.24f, 0.371f,
        0.896f, 0.24f, 0.371f,
        0.896f, 0.24f, 0.371f,
        0.257f, 0.96f, 0.106f,
        0.257f, 0.96f, 0.106f,
        0.257f, 0.96f, 0.106f,
        0.257f, -0.96f, 0.106f,
        0.257f, -0.96f, 0.106f,
        0.257f, -0.96f, 0.106f,
        0.896f, -0.24f, 0.371f,
        0.896f, -0.24f, 0.371f,
        0.896f, -0.24f, 0.371f,
        0.678f, 0.678f, 0.281f,
        0.678f, 0.678f, 0.281f,
        0.678f, 0.678f, 0.281f,
        0.371f, 0.24f, 0.896f,
        0.371f, 0.24f, 0.896f,
        0.371f, 0.24f, 0.896f,
        0.106f, 0.96f, 0.257f,
        0.106f, 0.96f, 0.257f,
        0.106f, 0.96f, 0.257f,
        0.106f, -0.96f, 0.257f,
        0.106f, -0.96f, 0.257f,
        0.106f, -0.96f, 0.257f,
        0.371f, -0.24f, 0.896f,
        0.371f, -0.24f, 0.896f,
        0.371f, -0.24f, 0.896f,
        0.281f, 0.678f, 0.678f,
        0.281f, 0.678f, 0.678f,
        0.281f, 0.678f, 0.678f,
        0.281f, -0.67f, 0.678f,
        0.281f, -0.67f, 0.678f,
        0.281f, -0.67f, 0.678f,
        -0.1f, 0.96f, 0.257f,
        -0.1f, 0.96f, 0.257f,
        -0.1f, 0.96f, 0.257f,
        -0.1f, -0.96f, 0.257f,
        -0.1f, -0.96f, 0.257f,
        -0.1f, -0.96f, 0.257f,
        -0.37f, -0.24f, 0.896f,
        -0.37f, -0.24f, 0.896f,
        -0.37f, -0.24f, 0.896f,
        -0.28f, 0.678f, 0.678f,
        -0.28f, 0.678f, 0.678f,
        -0.28f, 0.678f, 0.678f,
        -0.28f, -0.67f, 0.678f,
        -0.28f, -0.67f, 0.678f,
        -0.28f, -0.67f, 0.678f,
        -0.37f, 0.24f, 0.896f,
        -0.37f, 0.24f, 0.896f,
        -0.37f, 0.24f, 0.896f,
        -0.25f, -0.96f, 0.106f,
        -0.25f, -0.96f, 0.106f,
        -0.25f, -0.96f, 0.106f,
        -0.89f, -0.24f, 0.371f,
        -0.89f, -0.24f, 0.371f,
        -0.89f, -0.24f, 0.371f,
        -0.67f, 0.678f, 0.281f,
        -0.67f, 0.678f, 0.281f,
        -0.67f, 0.678f, 0.281f,
        -0.67f, -0.67f, 0.281f,
        -0.67f, -0.67f, 0.281f,
        -0.67f, -0.67f, 0.281f,
        -0.89f, 0.24f, 0.371f,
        -0.89f, 0.24f, 0.371f,
        -0.89f, 0.24f, 0.371f,
        -0.25f, 0.96f, 0.106f,
        -0.25f, 0.96f, 0.106f,
        -0.25f, 0.96f, 0.106f,
        -0.25f, -0.96f, -0.1f,
        -0.25f, -0.96f, -0.1f,
        -0.25f, -0.96f, -0.1f,
        -0.89f, -0.24f, -0.37f,
        -0.89f, -0.24f, -0.37f,
        -0.89f, -0.24f, -0.37f,
        -0.67f, 0.678f, -0.28f,
        -0.67f, 0.678f, -0.28f,
        -0.67f, 0.678f, -0.28f,
        -0.67f, -0.67f, -0.28f,
        -0.67f, -0.67f, -0.28f,
        -0.67f, -0.67f, -0.28f,
        -0.89f, 0.24f, -0.37f,
        -0.89f, 0.24f, -0.37f,
        -0.89f, 0.24f, -0.37f,
        -0.25f, 0.96f, -0.1f,
        -0.25f, 0.96f, -0.1f,
        -0.25f, 0.96f, -0.1f,
        -0.1f, -0.96f, -0.25f,
        -0.1f, -0.96f, -0.25f,
        -0.1f, -0.96f, -0.25f,
        -0.37f, -0.24f, -0.89f,
        -0.37f, -0.24f, -0.89f,
        -0.37f, -0.24f, -0.89f,
        -0.28f, 0.678f, -0.67f,
        -0.28f, 0.678f, -0.67f,
        -0.28f, 0.678f, -0.67f,
        -0.28f, -0.67f, -0.67f,
        -0.28f, -0.67f, -0.67f,
        -0.28f, -0.67f, -0.67f,
        -0.37f, 0.24f, -0.89f,
        -0.37f, 0.24f, -0.89f,
        -0.37f, 0.24f, -0.89f,
        -0.1f, 0.96f, -0.25f,
        -0.1f, 0.96f, -0.25f,
        -0.1f, 0.96f, -0.25f,
        0.281f, 0.678f, -0.67f,
        0.281f, 0.678f, -0.67f,
        0.281f, 0.678f, -0.67f,
        0.281f, -0.67f, -0.67f,
        0.281f, -0.67f, -0.67f,
        0.281f, -0.67f, -0.67f,
        0.371f, 0.24f, -0.89f,
        0.371f, 0.24f, -0.89f,
        0.371f, 0.24f, -0.89f,
        0.371f, -0.24f, -0.89f,
        0.371f, -0.24f, -0.89f,
        0.371f, -0.24f, -0.89f,
        0.678f, -0.67f, -0.28f,
        0.678f, -0.67f, -0.28f,
        0.678f, -0.67f, -0.28f,
        0.896f, 0.24f, -0.37f,
        0.896f, 0.24f, -0.37f,
        0.896f, 0.24f, -0.37f,
        0.896f, -0.24f, -0.37f,
        0.896f, -0.24f, -0.37f,
        0.896f, -0.24f, -0.37f,
        0.678f, 0.678f, -0.28f,
        0.678f, 0.678f, -0.28f,
        0.678f, 0.678f, -0.28f,
        0.678f, -0.67f, 0.281f,
        0.678f, -0.67f, 0.281f,
        0.678f, -0.67f, 0.281f,
        0.896f, 0.24f, 0.371f,
        0.896f, 0.24f, 0.371f,
        0.896f, 0.24f, 0.371f,
        0.896f, -0.24f, 0.371f,
        0.896f, -0.24f, 0.371f,
        0.896f, -0.24f, 0.371f,
        0.678f, 0.678f, 0.281f,
        0.678f, 0.678f, 0.281f,
        0.678f, 0.678f, 0.281f,
        0.371f, 0.24f, 0.896f,
        0.371f, 0.24f, 0.896f,
        0.371f, 0.24f, 0.896f,
        0.371f, -0.24f, 0.896f,
        0.371f, -0.24f, 0.896f,
        0.371f, -0.24f, 0.896f,
        0.281f, 0.678f, 0.678f,
        0.281f, 0.678f, 0.678f,
        0.281f, 0.678f, 0.678f,
        0.281f, -0.67f, 0.678f,
        0.281f, -0.67f, 0.678f,
        0.281f, -0.67f, 0.678f,
        -0.37f, -0.24f, 0.896f,
        -0.37f, -0.24f, 0.896f,
        -0.37f, -0.24f, 0.896f,
        -0.28f, 0.678f, 0.678f,
        -0.28f, 0.678f, 0.678f,
        -0.28f, 0.678f, 0.678f,
        -0.28f, -0.67f, 0.678f,
        -0.28f, -0.67f, 0.678f,
        -0.28f, -0.67f, 0.678f,
        -0.37f, 0.24f, 0.896f,
        -0.37f, 0.24f, 0.896f,
        -0.37f, 0.24f, 0.896f,
        -0.89f, -0.24f, 0.371f,
        -0.89f, -0.24f, 0.371f,
        -0.89f, -0.24f, 0.371f,
        -0.67f, 0.678f, 0.281f,
        -0.67f, 0.678f, 0.281f,
        -0.67f, 0.678f, 0.281f,
        -0.67f, -0.67f, 0.281f,
        -0.67f, -0.67f, 0.281f,
        -0.67f, -0.67f, 0.281f,
        -0.89f, 0.24f, 0.371f,
        -0.89f, 0.24f, 0.371f,
        -0.89f, 0.24f, 0.371f,
        -0.89f, -0.24f, -0.37f,
        -0.89f, -0.24f, -0.37f,
        -0.89f, -0.24f, -0.37f,
        -0.67f, 0.678f, -0.28f,
        -0.67f, 0.678f, -0.28f,
        -0.67f, 0.678f, -0.28f,
        -0.67f, -0.67f, -0.28f,
        -0.67f, -0.67f, -0.28f,
        -0.67f, -0.67f, -0.28f,
        -0.89f, 0.24f, -0.37f,
        -0.89f, 0.24f, -0.37f,
        -0.89f, 0.24f, -0.37f,
        -0.37f, -0.24f, -0.89f,
        -0.37f, -0.24f, -0.89f,
        -0.37f, -0.24f, -0.89f,
        -0.28f, 0.678f, -0.67f,
        -0.28f, 0.678f, -0.67f,
        -0.28f, 0.678f, -0.67f,
        -0.28f, -0.67f, -0.67f,
        -0.28f, -0.67f, -0.67f,
        -0.28f, -0.67f, -0.67f,
        -0.37f, 0.24f, -0.89f,
        -0.37f, 0.24f, -0.89f,
        -0.37f, 0.24f, -0.89f,
    )
    val indicatorVertices = floatArrayOf( // Front face
        -1.0f, 0.5f, 1.0f,
        -1.0f, -0.5f, 1.0f,
        1.0f, 0.5f, 1.0f,
        -1.0f, -0.5f, 1.0f,
        1.0f, -0.5f, 1.0f,
        1.0f, 0.5f, 1.0f,  // Right face
        1.0f, 0.5f, 1.0f,
        1.0f, -0.5f, 1.0f,
        0.0f, 0.5f, -2.0f,
        1.0f, -0.5f, 1.0f,
        0.0f, -0.5f, -2.0f,
        0.0f, 0.5f, -2.0f,// Left face
        0.0f, 0.5f, -2.0f,
        0.0f, -0.5f, -2.0f,
        -1.0f, 0.5f, 1.0f,
        0.0f, -0.5f, -2.0f,
        -1.0f, -0.5f, 1.0f,
        -1.0f, 0.5f, 1.0f,  // Top face
        0.0f, 0.5f, -2.0f,
        -1.0f, 0.5f, 1.0f,
        1.0f, 0.5f, 1.0f,  // Bottom face
        0.0f, -0.5f, -2.0f,
        1.0f, -0.5f, 1.0f,
        -1.0f, -0.5f, 1.0f
    )
    val indicatorNormals = floatArrayOf( // Front face
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,  // Right face
        2.0f, 0.0f, -1.0f,
        2.0f, 0.0f, -1.0f,
        2.0f, 0.0f, -1.0f,
        2.0f, 0.0f, -1.0f,
        2.0f, 0.0f, -1.0f,
        2.0f, 0.0f, -1.0f,  // Left face
        -2.0f, 0.0f, -1.0f,
        -2.0f, 0.0f, -1.0f,
        -2.0f, 0.0f, -1.0f,
        -2.0f, 0.0f, -1.0f,
        -2.0f, 0.0f, -1.0f,
        -2.0f, 0.0f, -1.0f,  // Top face
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,  // Bottom face
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f,
        0.0f, -1.0f, 0.0f
    )
    val roomVertices = floatArrayOf(
        5.0f, 1.5f, 5.0f,
        -5.0f, 1.5f, -5.0f,
        5.0f, 1.5f, -5.0f,
        -5.0f, -1.5f, 5.0f,
        5.0f, 1.5f, 5.0f,
        5.0f, -1.5f, 5.0f,
        -5.0f, -1.5f, -5.0f,
        -5.0f, 1.5f, 5.0f,
        -5.0f, -1.5f, 5.0f,
        -5.0f, -1.5f, 5.0f,
        5.0f, -1.5f, -5.0f,
        -5.0f, -1.5f, -5.0f,
        5.0f, -1.5f, 5.0f,
        5.0f, 1.5f, -5.0f,
        5.0f, -1.5f, -5.0f,
        5.0f, -1.5f, -5.0f,
        -5.0f, 1.5f, -5.0f,
        -5.0f, -1.5f, -5.0f,
        5.0f, 1.5f, 5.0f,
        -5.0f, 1.5f, 5.0f,
        -5.0f, 1.5f, -5.0f,
        -5.0f, -1.5f, 5.0f,
        -5.0f, 1.5f, 5.0f,
        5.0f, 1.5f, 5.0f,
        -5.0f, -1.5f, -5.0f,
        -5.0f, 1.5f, -5.0f,
        -5.0f, 1.5f, 5.0f,
        -5.0f, -1.5f, 5.0f,
        5.0f, -1.5f, 5.0f,
        5.0f, -1.5f, -5.0f,
        5.0f, -1.5f, 5.0f,
        5.0f, 1.5f, 5.0f,
        5.0f, 1.5f, -5.0f,
        5.0f, -1.5f, -5.0f,
        5.0f, 1.5f, -5.0f,
        -5.0f, 1.5f, -5.0f,
    )
    val roomNormals = floatArrayOf(
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -0.0f, -1.0f,
            -0.0f, -0.0f, -1.0f,
            -0.0f, -0.0f, -1.0f,
            1.0f, -0.0f, -0.0f,
            1.0f, -0.0f, -0.0f,
            1.0f, -0.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -1.0f, -0.0f, -0.0f,
            -1.0f, -0.0f, -0.0f,
            -1.0f, -0.0f, -0.0f,
            -0.0f, -0.0f, 1.0f,
            -0.0f, -0.0f, 1.0f,
            -0.0f, -0.0f, 1.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -1.0f, -0.0f,
            -0.0f, -0.0f, -1.0f,
            -0.0f, -0.0f, -1.0f,
            -0.0f, -0.0f, -1.0f,
            1.0f, -0.0f, -0.0f,
            1.0f, -0.0f, -0.0f,
            1.0f, -0.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -0.0f, 1.0f, -0.0f,
            -1.0f, -0.0f, -0.0f,
            -1.0f, -0.0f, -0.0f,
            -1.0f, -0.0f, -0.0f,
            -0.0f, -0.0f, 1.0f,
            -0.0f, -0.0f, 1.0f,
            -0.0f, -0.0f, 1.0f,
    )
}