package kr.ac.kaist.itchy

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import kr.ac.kaist.itchy.gameEngine.ShaderState
import kr.ac.kaist.itchy.managers.Managers
import kr.ac.kaist.itchy.scenes.BaseScene
import org.apache.commons.io.IOUtils
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import kr.ac.kaist.itchy.util.*
import java.util.Vector

/**
 * Created by sjjeon on 16. 9. 20.
 *  * Reissued for Itchy on 27. 9. 22
 */
class MyGLRenderer : GLSurfaceView.Renderer {
    var shaderStates: Vector<ShaderState>? = null

    private var startTime: Long = 0
    private var endTime: Long = 0
    val scene: BaseScene?
        get() = Managers.scene.currentScene

    /** Reset the MyGLRenderer. */
    fun reset() {
        Managers.scene.currentScene!!.reset()
    }

    fun initShaders() {
        shaderStates = Vector()
        // prepare shaders and OpenGL program
// Room
        val roomTextureShader = loadShaderFromFile(
            GLES20.GL_VERTEX_SHADER, "room-gl2.vshader"
        )
        val roomFragmentShader = loadShaderFromFile(
            GLES20.GL_FRAGMENT_SHADER, "room-gl2.fshader"
        )
// Mosquito
        val mosquitoVertexShader = loadShaderFromFile(
            GLES20.GL_VERTEX_SHADER, "mosquito-gl2.vshader"
        )
        val mosquitoFragmentShader = loadShaderFromFile(
            GLES20.GL_FRAGMENT_SHADER, "mosquito-gl2.fshader"
        )
// Laser
        val laserVertexShader = loadShaderFromFile(
            GLES20.GL_VERTEX_SHADER, "laser-gl2.vshader"
        )
        val laserFragmentShader = loadShaderFromFile(
            GLES20.GL_FRAGMENT_SHADER, "laser-gl2.fshader"
        )
        // Default
        val vertexShader = loadShaderFromFile(
            GLES20.GL_VERTEX_SHADER, "basic-gl2.vshader"
        )
        val fragmentShader = loadShaderFromFile(
            GLES20.GL_FRAGMENT_SHADER, "diffuse-gl2.fshader"
        )

        shaderStates!!.add(ShaderState(vertexShader, fragmentShader))
        shaderStates!!.add(ShaderState(laserVertexShader, laserFragmentShader))
        shaderStates!!.add(ShaderState(mosquitoVertexShader, mosquitoFragmentShader))
        shaderStates!!.add(ShaderState(roomTextureShader, roomFragmentShader))
    }

    override fun onSurfaceCreated(gl10: GL10, config: EGLConfig) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        GLES20.glEnable(GLES20.GL_CULL_FACE)
        GLES20.glEnable(GLES20.GL_DEPTH_TEST)
        GLES20.glEnable(GLES20.GL_BLEND)
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        initShaders()
        scene!!.onSurfaceCreated(gl10, config)
        startTime = System.currentTimeMillis()
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height)
        for(shader in shaderStates!!) {
            GLES20.glUseProgram(shader.mProgram)
            scene!!.setCamera(width, height, shader)
        }
    }

    /** This function will be called per frame continuously (about 60 times per second)*/
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDrawFrame(gl: GL10) {
        ////////// Limit FPS (Frames per second) //////////
        endTime = System.currentTimeMillis()
        Managers.time.deltaTime = endTime - startTime
        if (Managers.time.deltaTime < Managers.time.targetDeltaTime)
            Thread.sleep(Constants.PERIOD_60FPS - Managers.time.deltaTime)
        Managers.time.deltaTime = System.currentTimeMillis() - startTime
        startTime = System.currentTimeMillis()
        ///////////////////////////////////////////////////
        UpdateGame(Managers.time.deltaTime)

        // ~~ COllision Detection~~

        RenderGame(gl)
    }

    /** Update a game for one frame
     *
     * dt is in milliseconds */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun UpdateGame(dt: Long) {
        Managers.game.onUpdate(dt)
        Managers.scene.currentScene!!.onUpdate(dt)
        Managers.time.totalTime = Managers.time.totalTime + dt * Constants.MILLI
    }

    /** Render a game for one frame */
    private fun RenderGame(gl: GL10) {
        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)
        // Draw all GameObjects
        for(shader in shaderStates!!) {
            GLES20.glUseProgram(shader.mProgram)
            GLES20.glUniform1f(shader.mTime, Managers.time.totalTime.toFloat())
        }
        Managers.scene.drawScene(shaderStates!!)
    }

    companion object {
        private const val TAG = "MyGLRenderer"

        // Utility method for compiling a OpenGL shader.
        fun loadShader(type: Int, shaderCode: String?): Int {

            // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
            // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
            val shader = GLES20.glCreateShader(type)
            checkGlError("glCreateShader")
            // add the source code to the shader and compile it
            GLES20.glShaderSource(shader, shaderCode)
            checkGlError("glShaderSource")
            GLES20.glCompileShader(shader)
            checkGlError("glCompileShader")
            return shader
        }

        fun loadShader(type: Int, shaderFile: InputStream?): Int {
            var shaderCode: String? = null
            try {
                shaderCode = IOUtils.toString(shaderFile, Charset.defaultCharset())
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return loadShader(type, shaderCode)
        }

        fun loadShaderFromFile(type: Int, fileName: String?): Int {
            try {
                return loadShader(
                    type, MainActivity.Companion.context!!.getAssets().open(
                        fileName!!
                    )
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return -1
        }

        fun loadImage(fileName: String?): Bitmap? {
            try {
                val tmp = BitmapFactory.decodeStream(
                    MainActivity.Companion.context!!.getAssets().open(
                        fileName!!
                    )
                )
                val matrix = android.graphics.Matrix()
                matrix.preScale(1.0f, -1.0f)
                val image = Bitmap.createBitmap(tmp, 0, 0, tmp.width, tmp.height, matrix, true)
                tmp.recycle()
                return image
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }

        /**
         * Utility method for debugging OpenGL calls. Provide the name of the call
         * just after making it:
         *
         * <pre>
         * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
         * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
         *
         * If the operation is not successful, the check throws an error.
         *
         * @param glOperation - Name of the OpenGL call to check.
         */
        fun checkGlError(glOperation: String) {
            var error: Int
            while (GLES20.glGetError().also { error = it } != GLES20.GL_NO_ERROR) {
                Log.e(TAG, "$glOperation: glError $error")
                throw RuntimeException("$glOperation: glError $error")
            }
        }
    }
}