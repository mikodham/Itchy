package kr.ac.kaist.itchy.gameEngine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLUtils
import kr.ac.kaist.itchy.util.Constants
import android.opengl.Matrix
import android.util.Log
import kr.ac.kaist.itchy.MainActivity
import kr.ac.kaist.itchy.MyGLRenderer
import kr.ac.kaist.itchy.MyGLRenderer.Companion.loadImage
import kr.ac.kaist.itchy.util.Vector3
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/** A Class containing rendering information of an object */
open class RenderObject {
    private lateinit var mVertexBuffer: FloatBuffer
    private lateinit var mNormalBuffer: FloatBuffer
    private lateinit var mTexCoordBuffer: FloatBuffer

    val anchor = Vector3(0f, 0f, 0f)
    var vertices: FloatArray? = null
    var normals: FloatArray? = null
    var texCoord: FloatArray? = null

    open var color = floatArrayOf(0.2f, 0.709803922f, 0.898039216f)
    open var shaderIndex: Int = 0

    var textureUnit: IntArray = intArrayOf(-9999,-99999)

    constructor()
    constructor(renderObject: RenderObject) {
        mNormalBuffer = renderObject.mNormalBuffer!!.duplicate()
        mVertexBuffer = renderObject.mVertexBuffer!!.duplicate()
        mTexCoordBuffer = renderObject.mTexCoordBuffer!!.duplicate()

        vertices = renderObject.vertices?.let { FloatArray(it.size) }
        normals = renderObject.normals?.let { FloatArray(it.size) }
        texCoord = renderObject.texCoord?.let { FloatArray(it.size) }

        vertices?.let { System.arraycopy(vertices, 0, renderObject.vertices, 0, it.size) }
        normals?.let { System.arraycopy(normals, 0, renderObject.normals, 0, it.size) }
        texCoord?.let { System.arraycopy(texCoord, 0, renderObject.texCoord, 0, it.size) }
    }

    fun draw(curSS: ShaderState) {
        draw(curSS, GLES20.GL_TRIANGLES)
    }

    fun draw(curSS: ShaderState, drawMode: Int) {
        // Enable and bind the attributes used by our shader
        if(curSS.mPositionHandle != -1) {
            GLES20.glEnableVertexAttribArray(curSS.mPositionHandle)
            GLES20.glVertexAttribPointer(
                curSS.mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                VERTEX_STRIDE, mVertexBuffer
            )
            MyGLRenderer.checkGlError("glEnableVertexAttribArray")
            MyGLRenderer.checkGlError("glVertexAttribPointer")
        }
        if(curSS.mNormalHandle != -1) {
            GLES20.glEnableVertexAttribArray(curSS.mNormalHandle)
            GLES20.glVertexAttribPointer(
                curSS.mNormalHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                VERTEX_STRIDE, mNormalBuffer
            )
            MyGLRenderer.checkGlError("glEnableVertexAttribArray")
            MyGLRenderer.checkGlError("glVertexAttribPointer")
        }
        if(curSS.mTexCoordHandle != -1) {
            GLES20.glEnableVertexAttribArray(curSS.mTexCoordHandle)
            GLES20.glVertexAttribPointer(
                curSS.mTexCoordHandle, 2,
                GLES20.GL_FLOAT, false,
                8, mTexCoordBuffer
            )
            MyGLRenderer.checkGlError("glEnableVertexAttribArray")
            MyGLRenderer.checkGlError("glVertexAttribPointer")
        }

        // Texturing
////    loading texture0
        GLES20.glUniform1i(curSS.mTexture0Handle, textureUnit[0])
        MyGLRenderer.checkGlError("glUniform1i")

////    loading texture1
        GLES20.glUniform1i(curSS.mTexture1Handle, textureUnit[1])
        MyGLRenderer.checkGlError("glUniform1i")

        // Draw the geometry with triangles
        GLES20.glDrawArrays(drawMode, 0, vertices!!.size / 3)

        if(curSS.mNormalHandle != -1)
            GLES20.glDisableVertexAttribArray(curSS.mPositionHandle)
        if(curSS.mNormalHandle != -1)
            GLES20.glDisableVertexAttribArray(curSS.mNormalHandle)
        if(curSS.mTexCoordHandle != -1)
            GLES20.glDisableVertexAttribArray(curSS.mTexCoordHandle)
        MyGLRenderer.checkGlError("glDisableVertexAttribArray")
    }

    @JvmName("setVertices1")
    fun setVertices(vert: FloatArray) {
        vertices = FloatArray(vert.size)
        System.arraycopy(vert, 0, vertices, 0, vert.size)
    }

    @JvmName("setTexCoord1")
    fun setTexture(texts: FloatArray, texName0: String, texName1: String){
        texCoord = FloatArray(texts.size)
        System.arraycopy(texts, 0, texCoord, 0, texts.size)

        GLES20.glGenTextures(2, textureUnit, 0)
        // Texture1
        if(texName0 != "") {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + textureUnit[0])
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureUnit[0])

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, loadImage(texName0), 0)
            GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D)

            MyGLRenderer.checkGlError("glActiveTexture")
            MyGLRenderer.checkGlError("glBindTexture")
            MyGLRenderer.checkGlError("glGenerateMipmap")
        }

        // Texture2
        if(texName1 != "") {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + textureUnit[1])
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureUnit[1])

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, loadImage(texName1), 0)
            GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D)

            MyGLRenderer.checkGlError("glActiveTexture")
            MyGLRenderer.checkGlError("glBindTexture")
            MyGLRenderer.checkGlError("glGenerateMipmap")
        }
    }

    fun setAnchor(vec: Vector3) {
        setAnchor(vec.x, vec.y, vec.z)
    }

    fun setAnchor(x: Float, y: Float, z: Float) {
        val prevAnchor = Vector3(anchor)
        anchor.x = x
        anchor.y = y
        anchor.z = z
        for (i in vertices!!.indices step COORDS_PER_VERTEX) {
            vertices!![i] += x - prevAnchor.x
            vertices!![i + 1] += y - prevAnchor.y
            vertices!![i + 2] += z - prevAnchor.z
        }
        setBuffer()
    }

    @JvmName("setNormals1")
    fun setNormals(norm: FloatArray) {
        normals = FloatArray(norm.size)
        System.arraycopy(norm, 0, normals, 0, norm.size)
    }

    fun setBuffer() {
        var byteBuf1 = ByteBuffer.allocateDirect(vertices!!.size * 4)
        byteBuf1.order(ByteOrder.nativeOrder())
        mVertexBuffer = byteBuf1.asFloatBuffer()
        mVertexBuffer.put(vertices)
        mVertexBuffer.position(0)

        if(texCoord != null && texCoord!!.size > 0) {
            var byteBuf2 = ByteBuffer.allocateDirect(texCoord!!.size * 4)
            byteBuf2.order(ByteOrder.nativeOrder())
            mTexCoordBuffer = byteBuf2.asFloatBuffer()
            mTexCoordBuffer.put(texCoord)
            mTexCoordBuffer.position(0)
        }

        var byteBuf3 = ByteBuffer.allocateDirect(normals!!.size * 4)
        byteBuf3.order(ByteOrder.nativeOrder())
        mNormalBuffer = byteBuf3.asFloatBuffer()
        mNormalBuffer.put(normals)
        mNormalBuffer.position(0)
    }

    fun setColor(r: Float, g: Float, b: Float) {
        color[0] = r
        color[1] = g
        color[2] = b
    }

    @JvmName("setColor1")
    fun setColor(color: FloatArray) {
        setColor(color[0], color[1], color[2])
    }

    companion object {
        const val COORDS_PER_VERTEX = 3
        const val VERTEX_STRIDE = COORDS_PER_VERTEX * 4
    }
}