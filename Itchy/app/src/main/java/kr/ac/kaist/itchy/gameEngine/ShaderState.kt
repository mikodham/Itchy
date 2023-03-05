package kr.ac.kaist.itchy.gameEngine

import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLUtils
import kr.ac.kaist.itchy.MyGLRenderer
import kr.ac.kaist.itchy.util.normalMatrix

/**
 * Created by PCPC on 2016-10-05.
 */
class ShaderState(vertexShader: Int, fragmentShader: Int) {
    var mProgram: Int

    // Handles to uniform variables
    var mLightHandle: Int
    var mLight2Handle: Int
    var mProjMatrixHandle: Int
    var mModelViewMatrixHandle: Int
    var mNormalMatrixHandle: Int
    var mColorHandle: Int
    var mTime: Int
    var mTexture0Handle: Int
    var mTexture1Handle: Int
    var mIsInvincible: Int

    // Handles to vertex attributes
    var mPositionHandle: Int
    var mNormalHandle: Int
    var mTexCoordHandle: Int

    init {
        // prepare shaders and OpenGL program
        mProgram = GLES20.glCreateProgram() // create empty OpenGL Program
        GLES20.glAttachShader(mProgram, vertexShader) // add the vertex shader to program
        GLES20.glAttachShader(mProgram, fragmentShader) // add the fragment shader to program
        GLES20.glLinkProgram(mProgram) // create OpenGL program executables
        GLES20.glUseProgram(mProgram)
        MyGLRenderer.checkGlError("glCreateProgram")
        MyGLRenderer.checkGlError("glAttachShader")
        MyGLRenderer.checkGlError("glLinkProgram")
        MyGLRenderer.checkGlError("glUseProgram")

        // uniforms
        mProjMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uProjMatrix")
        mModelViewMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uModelViewMatrix")
        mNormalMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uNormalMatrix")
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "uColor")
        mLightHandle = GLES20.glGetUniformLocation(mProgram, "uLight")
        mLight2Handle = GLES20.glGetUniformLocation(mProgram, "uLight2")
        mTime = GLES20.glGetUniformLocation(mProgram, "uTime")
        mTexture0Handle = GLES20.glGetUniformLocation(mProgram, "uTexture0")
        mTexture1Handle = GLES20.glGetUniformLocation(mProgram, "uTexture1")

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition")
        mNormalHandle = GLES20.glGetAttribLocation(mProgram, "aNormal")
        mTexCoordHandle = GLES20.glGetAttribLocation(mProgram, "aTexCoord")
        MyGLRenderer.checkGlError("glGetAttribLocation")

        // etc. (uniform variable specific for some objects)
        // Not a good design, but will do for this game
        mIsInvincible = GLES20.glGetUniformLocation(mProgram, "uIsInvincible")

    }

    companion object {
        fun sendModelViewNormalMatrix(ss: ShaderState, MVM: FloatArray?) {
            val NM = FloatArray(16)
            normalMatrix(NM, 0, MVM, 0)
            GLES20.glUniformMatrix4fv(ss.mModelViewMatrixHandle, 1, false, MVM, 0)
            GLES20.glUniformMatrix4fv(ss.mNormalMatrixHandle, 1, false, NM, 0)
        }
    }
}