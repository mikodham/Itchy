package kr.ac.kaist.itchy.managers

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.gameEngine.ShaderState
import java.util.*
import kotlin.Comparator
import kr.ac.kaist.itchy.gameObjects.RootGameObject

/** A Manager class that manage game (Game)Objects in game. */
class ObjectManager {
    /** A map (a dictionary where each key can have only one value) to store and manage GameObjects.
     * MutableMap<id, GameObject>
     * It has to be cleared at each end of the stage. */
    private val gameObjectMap: MutableMap<UInt, GameObject> = mutableMapOf()
    /** Root GameObject (located at origin with no rotation, no RenderObject) of all GameObjects. */
    lateinit var rootGameObject: RootGameObject

    ////////////////////// FUNCTIONS FOR ACCESSING MAP //////////////////////
    /** Returns a GameObject corresponding to the input id.
     * If there is no such GameObject, it returns null. */
    fun getGameObjectById(id: UInt): GameObject? {
        return gameObjectMap[id]
    }

    /** Assigns and returns an id for the input gameObject.
     * If there is too many gameObjects (UInt.MAX_VALUE), it raises IllegalStateException. */
    fun assignGameObjectId(gameObject: GameObject): UInt {
        // 1. Find available id
        var maxKey = ROOT_KEY
        if (gameObjectMap.isNotEmpty())
            maxKey = gameObjectMap.maxWith(Comparator { x, y -> x.key.compareTo(y.key) } ).key
        else {
            rootGameObject = RootGameObject()
            gameObjectMap[ROOT_KEY] = rootGameObject
        }
        // 1-1. If there is no available id, raise an IllegalStateException.
        // I Don't want to check the state of gameObject
        check(maxKey < UInt.MAX_VALUE) { "There is no available id in gameObjectMap (too many gameObjects)" }
        // 2. Assign it to gameObject and return that ID
        val key = maxKey + 1U
        gameObjectMap[key] = gameObject
        // println("The number of GameObjects: " + gameObjectMap.size) // ! for debugging
        return key
    }
    /////////////////////////////////////////////////////////////////////////
    fun drawAll(viewMatrix: FloatArray?, shaderStates: Vector<ShaderState>) {
        gameObjectMap.map { x -> x.value.draw(viewMatrix, shaderStates) }
    }

    fun drawVisit(parentVM: GameObject, go: GameObject) {

    }

    fun clear() {
        gameObjectMap.clear()
        rootGameObject = RootGameObject()
        gameObjectMap[ROOT_KEY] = rootGameObject
    }

    init {
        clear()
    }

    companion object {
        const val ROOT_KEY = 0U
    }
}