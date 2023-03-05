#  Itchy
### CS482 Interative Computer Graphics  - Fall 2022 - KAIST
- Jinhyeok Choi (최진혁)   [@cjh0507](https://www.github.com/cjh0507)
- Dhammiko Arya Gandamana  
- Mathichai Dechdee  [@mdechdee](https://www.github.com/mdechdee)
- Elli Jacqueline Haehnel [@elli-hae](https://www.github.com/elli-hae)


### Development
1. Check app/src/main/java where we import kr.ac.kaist.itchy
2. OOP Design Choice
```
Main Activity - UI 
│   Surface View 
│   │   MyGLRenderer 
│   │   Game Manager(arg: MyGLRenderer)
│   │   │   All Objects: Boxes, Cube, Sphere, etc
│   │   │   CollisionClass
```

```
GameObject
|   id: UInt
│   transform: Transform
│   │   pos: Vector3
│   │   quat: Quaternion
│   mRenderObject: RenderObject?
│   │   draw()
```

3. Codes Directory Structure
```
kr.ac.kaist.itchy
└───collisionCheck (need to be re-described by Elli)
|   |   AABB
|   |   Collidable
|   |   Collision(?)
|   |   SphereWrapper
└───gameEngine
|   |   GameObject    : A Class representing a object in game
|   |   RenderObject  : An abstract Class containing rendering information of a object
|   |   Transform     : The class containing spatial information (position, quaternion, scale) of object containing it.
└───gameObjects       : Classes that inherits GameObject
|   |   Camera        : A class representing a camera
|   |   CubeObject
|   |   Indicator     : An indicator that indicates the location of target in game.
|   |   SphereObject
|   |   SquareObject
└───managers
|   |   GameManager   : A Manager class that manage game-logic things.
|   |   InputManager  : A Manager class that manage user input.
|   |   Managers      : A class containing all managers for the game program. Singleton pattern.
|   |   ObjectManager : A Manager class that manage game (Game)Objects in game.
|   |   SceneManager  : A Manager class that manage scenes.
|   |   TimeManager   : A Manager class that manage time in game.
└───renderObjects     : Classes that inherits RenderObject
|   |   CubeModel
|   |   IndicatorModel
|   |   SphereModel
|   |   SquareModel
└───scenes
|   |   BaseScene     : An Abstract class for all scenes.
|   |   GameScene     : Scene for in-game situation. Inherits BaseScene.
└───util
|   |   Constants     : Constants should be located here
|   |   Extension.kt  : Some general useful Extensions and Functions (ex. Math functions) should be located here
|   |   Quaternion    : A class representing a quaternion
|   |   Vector        : A class representing a multi-dimensional vector
|   |   Vector2       : A class representing a 2-dimensional vector. Inherits Vector.
|   |   Vector3       : A class representing a 3-dimensional vector. Inherits Vector.
|   |   Vector4       : A class representing a 4-dimensional vector. Inherits Vector.
|   Collision(?)
|   MainActivity
|   MyGLRenderer
|   MyGLSurfaceView
```

5. Known Issues

- Indicator is overlapped by other game objects in the game, if player goes too close other objects like walls. We need to make indicator visible always.
- UI, depending on android version, time-sign overlay may be duplicated

## Wiki for This Project
https://github.com/KAIST-VCLAB/cs482-2022-team3/wiki

## Files
[Final Presentation Slide](https://docs.google.com/presentation/d/1SegkADEcF9UqeykoZFeFhRZjSjTmzfU5I71eLY57Yxw/edit?usp=sharing)


## References
- How to limit framerate when using Android's GLSurfaceView.RENDERMODE_CONTINUOUSLY? :
    https://stackoverflow.com/questions/4772693/how-to-limit-framerate-when-using-androids-glsurfaceview-rendermode-continuousl
