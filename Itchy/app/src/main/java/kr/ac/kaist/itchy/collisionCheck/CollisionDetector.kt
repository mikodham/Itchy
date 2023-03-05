package kr.ac.kaist.itchy.collisionCheck

import kr.ac.kaist.itchy.gameEngine.GameObject
import kr.ac.kaist.itchy.gameObjects.Neopjook2Object
import kr.ac.kaist.itchy.renderObjects.MosquitoModel
import kr.ac.kaist.itchy.renderObjects.RoomModel

class CollisionDetector {
    private var colliders: MutableSet<GameObject> = mutableSetOf()
    private lateinit var room: AABB
    private lateinit var playerObject: AABB

    fun addObject(c: GameObject?) {
        update(c)
        if (c!!.mRenderObject is RoomModel) {
            room = (c.collisionObject as AABB?)!!
        } else if (c.mRenderObject is MosquitoModel) {
            this.playerObject = (c.collisionObject as AABB)
        } else {
            colliders.add(c)
            // add children if it has them
            for (child in c.transform.children) {
                colliders.add(child!!.gameObject)
            }
            // add target if its Neopjook

            if (c is Neopjook2Object) {
                colliders.add(c.target)
            }
        }
    }

    fun addObject(l: List<GameObject>?) {
        l!!.iterator().forEach { x ->
            addObject(x)
        }
    }

//    // Remove single objects from Collision List: not needed right now
//    //only removes single object, if needed possible to extend
//    fun remObject(c: GameObject) {
//        if (colliders.contains(c)) {
//            colliders.remove(c)
//        }
//    }

    // update game Object's hitbox and that of its dependants, preorder traversal
    fun update(c: GameObject?) {
        c!!.collisionObject!!.update(
            c.mRenderObject!!.vertices!!,
            c.transform
        )
        for (child in c.transform.children) {
            child!!.gameObject.collisionObject!!.update(
                child.gameObject.mRenderObject!!.vertices!!,
                child
            )
        }
        if (c is Neopjook2Object) {
            c.target.collisionObject!!.update(
                c.target.mRenderObject!!.vertices!!,
                c.transform
            )
        }
    }

    fun update(l: List<GameObject>?) {
        l!!.iterator().forEach { x ->
            update(x)
        }
    }

    fun clear() {
        colliders = mutableSetOf()
    }

    // iterates through all the objects and returns object colliding with the player object
    fun checkCollision(): GameObject? {
        for (obj2 in colliders) {
            if (playerObject != obj2.collisionObject) {
                if (obj2.collisionObject is BoundingSphere) {
                    if (playerObject.intersects(obj2.collisionObject!! as BoundingSphere)) {
                        return obj2
                    }
                } else {
                    if (playerObject.intersects(obj2.collisionObject as AABB)) {
                        return obj2
                    }
                }
            }
        }
        return null
    }

    fun playerIsInsideRoom(): Boolean {
        return room.maxX >= playerObject.maxX
                && room.maxY >= playerObject.maxY
                && room.maxZ >= playerObject.maxZ
                && room.minX <= playerObject.minX
                && room.minY <= playerObject.minY
                && room.minZ <= playerObject.minZ
    }


}