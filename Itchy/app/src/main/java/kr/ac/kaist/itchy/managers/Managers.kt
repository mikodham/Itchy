package kr.ac.kaist.itchy.managers

/** A class containing all managers for the game program.
 *
 * <Singleton Pattern> */
class Managers private constructor() {
    private val _time: TimeManager = TimeManager()
    private val _scene: SceneManager = SceneManager()
    private val _obj: ObjectManager = ObjectManager()
    private val _game: GameManager = GameManager()
    private val _input: InputManager = InputManager()

    /** Singleton Pattern: There is only one instance of Managers during runtime.
     * Access to that unique instance by calling getInstance() */
    companion object {
        private var s_instance: Managers? = null

        val time: TimeManager
            get() = getInstance()._time
        val scene: SceneManager
            get() = getInstance()._scene
        val obj: ObjectManager
            get() = getInstance()._obj
        val game: GameManager
            get() = getInstance()._game
        val input: InputManager
            get() = getInstance()._input

        /** Returns a unique existing instance of Managers. */
        fun getInstance(): Managers {
            return s_instance ?: synchronized(this) {
                s_instance ?: Managers().also { s_instance = it }
            }
        }

        fun clear() { s_instance?.clear() }
    }

    fun clear() {
        _time.clear()
        _input.clear()
        _game.clear()
        _obj.clear()
        _scene.clear()
    }
}