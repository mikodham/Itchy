package kr.ac.kaist.itchy.gameObjects

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.renderObjects.CubeModel
import kr.ac.kaist.itchy.renderObjects.NeopjookModel
import kr.ac.kaist.itchy.util.Constants
import kr.ac.kaist.itchy.util.Vector3
import kotlin.math.abs

class ComplexObject: GameObject(NeopjookModel()) {
    val bodyModel: NeopjookModel
        get() = mRenderObject as NeopjookModel
    val child0: CubeObject
        get() = transform.getChild(0).gameObject as CubeObject
    val child1: CubeObject
        get() = transform.getChild(1).gameObject as CubeObject
    ////////////////////// FOR ANIMATION //////////////////////
    val maxAngle = 50f
    val maxDisp = 0.2f
    var angSgn = 1f
    var dispSgn = 1f
    var curAngle = 0f
    var curDisp = 0f
    ///////////////////////////////////////////////////////////

    override fun initGameObject() {
        super.initGameObject()
        val ch0 = CubeObject()
        ch0.cubeModel.color = floatArrayOf(1f, 0.2f, 0.1f)
        ch0.cubeModel.setAnchor(0f, 0f, 1f)
        ch0.transform.setScale(Vector3(0.05f, 0.05f, 0.3f))
        transform.addChild(ch0.transform)

        val ch00 = CubeObject()
        ch00.cubeModel.color = floatArrayOf(1f, 0.2f, 0.3f)
        ch00.cubeModel.setAnchor(0f, 0f, 1f)
        ch00.transform.setScale(Vector3(0.05f, 0.05f, 0.3f))
        val ch01 = CubeObject()
        ch01.cubeModel.color = floatArrayOf(1f, 0.2f, 0.3f)
        ch01.cubeModel.setAnchor(0f, 0f, 1f)
        ch01.transform.setScale(Vector3(0.05f, 0.05f, 0.3f))
        val ch02 = CubeObject()
        ch02.cubeModel.color = floatArrayOf(1f, 0.2f, 0.3f)
        ch02.cubeModel.setAnchor(0f, 0f, 1f)
        ch02.transform.setScale(Vector3(0.05f, 0.05f, 0.3f))
        val ch03 = CubeObject()
        ch03.cubeModel.color = floatArrayOf(1f, 0.2f, 0.3f)
        ch03.cubeModel.setAnchor(0f, 0f, 1f)
        ch03.transform.setScale(Vector3(0.05f, 0.05f, 0.3f))
        val ch04 = CubeObject()
        ch04.cubeModel.color = floatArrayOf(1f, 0.2f, 0.3f)
        ch04.cubeModel.setAnchor(0f, 0f, 1f)
        ch04.transform.setScale(Vector3(0.05f, 0.05f, 0.3f))
        ch0.transform.addChild(ch00.transform)
        ch0.transform.addChild(ch01.transform)
        ch0.transform.addChild(ch02.transform)
        ch0.transform.addChild(ch03.transform)
        ch0.transform.addChild(ch04.transform)
        ch0.transform.goUp(0.7f)
        ch0.transform.goBack(0.1f)
        ch0.transform.goLeft(0.15f)
        ch00.transform.goBack(0.55f)
        ch00.transform.rotate(60f, 1f, 0f, 0f)
        ch01.transform.goBack(0.55f)
        ch01.transform.rotate(30f, 1f, 0f, 0f)
        ch02.transform.goBack(0.55f)
        ch03.transform.goBack(0.55f)
        ch03.transform.rotate(-30f, 1f, 0f, 0f)
        ch04.transform.goBack(0.55f)
        ch04.transform.rotate(-60f, 1f, 0f, 0f)


        val ch1 = CubeObject()
        ch1.cubeModel.color = floatArrayOf(1f, 0.2f, 0.1f)
        ch1.cubeModel.setAnchor(0f, 0f, -1f)
        ch1.transform.setScale(Vector3(0.05f, 0.05f, 0.3f))
        transform.addChild(ch1.transform)
        ch1.transform.goUp(0.7f)
        ch1.transform.goForward(0.1f)
        ch1.transform.goLeft(0.15f)

//        // ! NEED TO BE DELETED LATER
//        println("parent pos: ${transform.pos}")
//        println("parent localPos: ${transform.localPos}")
//        for ((i, tr) in transform.children.withIndex()) {
//            println(tr.gameObject)
//            println("child " + i + " pos: " + tr.pos)
//            println("child " + i + " localPos: " + tr.localPos)
//        }
    }

    fun doAnimation(dt: Long) {
        var angleDelta = angSgn * 500f * dt * Constants.MILLI
        if (abs(curAngle + angleDelta) >= maxAngle) {
            angSgn *= -1
            angleDelta *= -1
        }
        curAngle += angleDelta
        transform.rotate(30f * dt * Constants.MILLI, 0f, 1f, 0f)
        child0.transform.rotate(angleDelta, 1f, 0f, 0f)
        for (ch in child0.transform.children)
            ch.rotate(200f * dt * Constants.MILLI, 0f, 1f, 0f)
        child1.transform.rotate(-angleDelta, 1f, 0f, 0f)

        var disp = dispSgn * 0.25f * dt * Constants.MILLI
        if (abs(curDisp + disp) >= maxDisp) {
            dispSgn *= -1
            disp *= -1
        }
        curDisp += disp
        transform.translate(0f, disp, 0f)
    }
}