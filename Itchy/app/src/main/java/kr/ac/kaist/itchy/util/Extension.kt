package kr.ac.kaist.itchy.util

import android.opengl.Matrix
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

private val temp = FloatArray(16)

// Some general useful Extensions and Functions (ex. Math functions) and Classes should be located here
/////////// EXTENSIONS FOR ARITHMETIC OPERATIONS ///////////
fun clamp(value: Double, min: Double, max: Double): Double = Math.max(min, Math.min(value, max))
fun clamp(value: Float, min: Float, max: Float): Float = Math.max(min, Math.min(value, max))

fun lerp(x0: Float, x1: Float, slope: Float): Float { return x0 + slope * (x1 - x0) }
/////////////////////////////////////////////

/////////// EXTENSIONS FOR VECTOR ///////////
operator fun <T:Number> T.times(v1: Vector): Vector {
    return v1 * this
}

/** Returns a normalized direction vector for given model matrix */
fun getObjDirVec(modelMatrix: FloatArray, dir: Vector3): Vector3 {
    Matrix.setIdentityM(temp, 0)
    translateM(temp, 0, dir)
    val p = getTransVec(doMtoWwrtA(temp, modelMatrix, modelMatrix))
    p.subtract(Vector3(modelMatrix[12], modelMatrix[13], modelMatrix[14]))
    return p // This vector is already normalized, since we translated using length 1 Vector.
}

/** Returns a normalized forward vector for given model matrix */
fun getForwardVec(modelMatrix: FloatArray): Vector3 {
    return getObjDirVec(modelMatrix, Vector3.forward)
}

/** Returns a normalized backward vector for given model matrix */
fun getBackwardVec(modelMatrix: FloatArray): Vector3 {
    return getObjDirVec(modelMatrix, Vector3.backward)
}

/** Returns a normalized up vector for given model matrix */
fun getUpVec(modelMatrix: FloatArray): Vector3 {
    return getObjDirVec(modelMatrix, Vector3.up)
}

/** Returns a normalized down vector for given model matrix */
fun getDownVec(modelMatrix: FloatArray): Vector3 {
    return getObjDirVec(modelMatrix, Vector3.down)
}

/** Returns a normalized left vector for given model matrix */
fun getLeftVec(modelMatrix: FloatArray): Vector3 {
    return getObjDirVec(modelMatrix, Vector3.left)
}

/** Returns a normalized right vector for given model matrix */
fun getRightVec(modelMatrix: FloatArray): Vector3 {
    return getObjDirVec(modelMatrix, Vector3.right)
}

/** Returns a rotation matrix(4x4) making 2 times of v1 -> v2. (from Arcball) */
fun getArcballRotMat(v1: Vector3, v2: Vector3): FloatArray {
    return Quaternion.quatToMat(getArcballQuat(v1, v2))
}

/** Returns a normalized Quaternion making 2 times of v1 -> v2. (from Arcball) */
fun getArcballQuat(v1: Vector3, v2: Vector3): Quaternion {
    val normalizedV1 = Vector3(Vector.normalize(v1))
    val normalizedV2 = Vector3(Vector.normalize(v2))
    val k = Vector3()
    Vector.cross(normalizedV1, normalizedV2, k)
    return Quaternion(Vector.dot(normalizedV1, normalizedV2), k)
}

/** Returns a rotation matrix(4x4) making v1 -> v2. (from Trackball) */
fun getTrackballRotMat(v1: Vector3, v2: Vector3): FloatArray {
    val normalizedV1 = Vector3(Vector.normalize(v1))
    val normalizedV2 = Vector3(Vector.normalize(v2))
    val dotProduct = clamp(Vector.dot(normalizedV1, normalizedV2).toDouble(),
        -Constants.DOTPRODUCT_BOUNDARY,
        Constants.DOTPRODUCT_BOUNDARY)
    val angle = acos(dotProduct)
    val degree = Math.toDegrees(angle.toDouble()).toFloat()
    val k = Vector3()
    Vector.cross(normalizedV1, normalizedV2, k)
    val rot = FloatArray(16)
    Matrix.setRotateM(rot, 0, degree, k[0], k[1], k[2])
    return rot
}

/** Returns a normalized Quaternion making v1 -> v2. (from Trackball) */
fun getTrackballQuat(v1: Vector3, v2: Vector3): Quaternion {
    val normalizedV1 = Vector3(Vector.normalize(v1))
    val normalizedV2 = Vector3(Vector.normalize(v2))
    val dotProduct = clamp(Vector.dot(normalizedV1, normalizedV2).toDouble(),
        -Constants.DOTPRODUCT_BOUNDARY,
        Constants.DOTPRODUCT_BOUNDARY)
    val angle = acos(dotProduct).toFloat()
    val k = Vector3()
    Vector.cross(normalizedV1, normalizedV2, k)
    val halfAngle = angle/2f
    return Quaternion(cos(halfAngle), Vector3(k*sin(halfAngle)))
}
/////////////////////////////////////////////

///////// EXTENSIONS FOR Quaternion /////////
operator fun <T:Number> T.times(q1: Quaternion): Quaternion {
    return q1 * this
}

fun slerp(q0: Quaternion, q1: Quaternion, alpha: Float): Quaternion {
    val omega =
        acos(
            clamp(Quaternion.dot(q0, q1),
            Constants.EPSILON.toFloat(),
            1f-Constants.EPSILON.toFloat())
        )
    val coeff0 = sin((1-alpha)*omega) / sin(omega)
    val coeff1 = sin(alpha*omega) / sin(omega)
    return q0*coeff0 + q1*coeff1
}
/////////////////////////////////////////////

/////////// EXTENSIONS FOR MATRIX ///////////
fun translateM(m: FloatArray, mOffset: Int, vec: Vector3) {
    Matrix.translateM(m, mOffset, vec[0], vec[1], vec[2])
}

fun translateM(m: FloatArray, mOffset: Int, vec: FloatArray) {
    Matrix.translateM(m, mOffset, vec[0], vec[1], vec[2])
}

fun setRotateM(rm: FloatArray, rmOffset: Int, a: Float, vec: Vector3) {
    Matrix.setRotateM(rm, rmOffset, a, vec[0], vec[1], vec[2])
}

fun setRotateM(rm: FloatArray, rmOffset: Int, a: Float, vec: FloatArray) {
    Matrix.setRotateM(rm, rmOffset, a, vec[0], vec[1], vec[2])
}

fun rotateM(m: FloatArray, mOffset: Int, a: Float, vec: Vector3) {
    Matrix.rotateM(m, mOffset, a, vec[0], vec[1], vec[2])
}

fun rotateM(m: FloatArray, mOffset: Int, a: Float, vec: FloatArray) {
    Matrix.rotateM(m, mOffset, a, vec[0], vec[1], vec[2])
}

fun setLookAtM(rm:FloatArray, rmOffset: Int,
               eyePos: Vector3,
               lookPoint: Vector3,
               upVec: Vector3) {
    Matrix.setLookAtM(rm, rmOffset,
        eyePos[0], eyePos[1], eyePos[2],
        lookPoint[0], lookPoint[1], lookPoint[2],
        upVec[0], upVec[1], upVec[2])
}

fun setLookAtM(rm:FloatArray, rmOffset: Int,
               eyePos: FloatArray,
               lookPoint: FloatArray,
               upVec: FloatArray) {
    Matrix.setLookAtM(rm, rmOffset,
        eyePos[0], eyePos[1], eyePos[2],
        lookPoint[0], lookPoint[1], lookPoint[2],
        upVec[0], upVec[1], upVec[2])
}

fun scaleM(m: FloatArray, mOffset: Int, vec: Vector3) {
    Matrix.scaleM(m, mOffset, vec[0], vec[1], vec[2])
}

fun scaleM(m: FloatArray, mOffset: Int, vec: FloatArray) {
    Matrix.scaleM(m, mOffset, vec[0], vec[1], vec[2])
}

fun normalMatrix(dst: FloatArray, dstOffset: Int, src: FloatArray?, srcOffset: Int) {
    Matrix.invertM(dst, dstOffset, src, srcOffset)
    dst[12] = 0F
    dst[13] = 0F
    dst[14] = 0F
    val temp = dst.copyOf(16)
    Matrix.transposeM(dst, dstOffset, temp, 0)
}

fun transFact(m: FloatArray): FloatArray {
    val trans = FloatArray(16)
    Matrix.setIdentityM(trans, 0)
    System.arraycopy(m,12,trans,12,3); // extract element 12,13,14
    return trans
}

/** Returns a translation factor of affine matrix m as a Vector3 */
fun getTransVec(m: FloatArray): Vector3 {
    return Vector3(m[12], m[13], m[14])
}

fun linFact(m: FloatArray): FloatArray { // get linearFact
    val rot = FloatArray(16)
    Matrix.setIdentityM(rot, 0)
    //assume last column always 0001
//        System.arraycopy(m,0,rot,0,12); // extract element 0,..,11
    //if not assume
    System.arraycopy(m, 0, rot, 0, 3)
    System.arraycopy(m, 4, rot, 4, 3)
    System.arraycopy(m, 8, rot, 8, 3)
    return rot
}

fun makeMixedFrame(objRbt:FloatArray, eyeRbt:FloatArray): FloatArray {
    val fr = FloatArray(16)
    Matrix.setIdentityM(fr, 0)
    Matrix.multiplyMM(fr, 0, transFact(objRbt), 0, linFact(eyeRbt), 0)
    return fr // return O.t * E.r
}

fun doMtoWwrtA(M: FloatArray, O: FloatArray, A:FloatArray): FloatArray {
    val tmp = FloatArray(16)
    val res = FloatArray(16)
    val invA = FloatArray(16)
    Matrix.invertM(invA, 0, A,0)
    // res = invA*O
    Matrix.setIdentityM(tmp, 0)
    Matrix.multiplyMM(tmp, 0, invA, 0, O, 0)
    System.arraycopy(tmp,0,res,0,16)
    // res = M * invA * O
    Matrix.multiplyMM(tmp, 0, M, 0, res, 0)
    System.arraycopy(tmp, 0, res, 0, 16)
    // Return  A*M*invA*0
    Matrix.multiplyMM(tmp, 0, A, 0, res, 0)
    System.arraycopy(tmp, 0, res, 0, 16)
    return res
}

fun getEyeCoordVec(viewMatrix: FloatArray?, vec: Vector4): FloatArray {
    return getEyeCoordVec(viewMatrix, vec.toFloatArray())
}

fun getEyeCoordVec(viewMatrix: FloatArray?, vec: FloatArray): FloatArray {
    val eyeVec = FloatArray(4)
    Matrix.multiplyMV(eyeVec, 0, viewMatrix, 0, vec, 0)
    return eyeVec
}

fun getMVM(viewMatrix: FloatArray?, modelMatrix: FloatArray?): FloatArray {
    val modelViewMatrix = FloatArray(16)
    Matrix.multiplyMM(modelViewMatrix, 0, viewMatrix, 0, modelMatrix, 0)
    return modelViewMatrix
}

/////////////////////////////////////////////