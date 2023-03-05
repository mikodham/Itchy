package kr.ac.kaist.itchy.gameObjects

import android.opengl.GLES20
import android.opengl.Matrix
import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.gameEngine.RenderObject
import kr.ac.kaist.itchy.gameEngine.ShaderState
import kr.ac.kaist.itchy.managers.GameManager
import kr.ac.kaist.itchy.managers.Managers
import kr.ac.kaist.itchy.renderObjects.MosquitoModel
import kr.ac.kaist.itchy.renderObjects.NeopjookModel
import kr.ac.kaist.itchy.renderObjects.SquareModel
import kr.ac.kaist.itchy.util.getMVM
import kr.ac.kaist.itchy.util.normalMatrix
import java.util.*

/** Wrapper Class for GameObject whose RenderObject is CubSquareModel. */
class MosquitoObject: GameObject(MosquitoModel()) {
    val mosquitoModel: MosquitoModel
        get() = mRenderObject as MosquitoModel

    override fun draw(viewMatrix: FloatArray?, shaderStates: Vector<ShaderState>) {
        if(mRenderObject != null){
            val curSS = shaderStates[mRenderObject!!.shaderIndex]
            GLES20.glUseProgram(curSS.mProgram)
            GLES20.glUniform1i(curSS.mIsInvincible, if(Managers.game.invisibleFrames) 1 else 0)
        }
        super.draw(viewMatrix, shaderStates)
    }
}