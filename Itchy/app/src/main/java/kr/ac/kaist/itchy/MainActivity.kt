package kr.ac.kaist.itchy

//import android.app.AlertDialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.google.gson.Gson
import kr.ac.kaist.itchy.managers.GameManager
import kr.ac.kaist.itchy.managers.Managers
import kr.ac.kaist.itchy.util.PreferenceUtil
import kr.ac.kaist.itchy.util.playerData


/**
 * Created by sjjeon on 16. 9. 20.
 * Reissued for Itchy on 27. 9. 22
 */
class MainActivity : Activity() {
    private var mGLView: MyGLSurfaceView? = null
//    private var mainArray = BooleanArray(3) {false}
    var scoreView: TextView? = null
    var text321: TextView? = null
    var dialog321: AlertDialog? = null
    var dialogcombo: AlertDialog? = null
    var gameLayout: LinearLayout? = null
    lateinit var heartLayout: LinearLayout
    var level: Int = 5
    var selectNum: Int = 0
    var gyroProgress: Int = 0
    var speedProgress: Int = 0
    var speedBoostProgress: Int = 0
    var playerName: String = "Itchy"

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("InflateParams", "SetTextI18n", "ResourceAsColor")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = applicationContext
        prefs = PreferenceUtil(applicationContext)
        playerName = prefs.getString("CurPlayerName", "Itchy")
        level = prefs.getString("CurLevel", "5").toInt()
        gyroProgress = prefs.getString("Gyro", "0").toInt()
        Managers.scene.startStage(level)
        Managers.game.setStage(level)
        // TODO: Change Main UI and Implement Main Screen Here
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = MyGLSurfaceView(this)

        val menu_layout = R.layout.menu

        setContentView(menu_layout)
        val playButton: Button = findViewById(R.id.playButton)
        val leaderButton: Button = findViewById(R.id.leaderButton)
        val settingsButton: Button = findViewById(R.id.settingsButton)
//        val menu_buttons = arrayOf(playButton, leaderButton, settingsButton)
        val editText: EditText = findViewById(R.id.idInput)
//        editText.hint = if(Managers.game.playerName.isNotEmpty()) Managers.game.playerName else playerName
        editText.hint = prefs.getString("CurPlayerName", "Itchy")
        editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // Place the logic here for your output edittext
                val text = editText.text
                playerName = text.toString()
                prefs.setString("CurPlayerName", playerName)
            }
        })

        playButton.setOnClickListener {
            level = prefs.getString("CurLevel", "5").toInt()
            Managers.scene.startStage(level)
            Managers.game.setStage(level)
            val gyroval: Float = (gyroProgress/10).toFloat()
            Managers.input.changeSense(gyroval)
            val speedval: Float = (speedProgress/10).toFloat()
            Managers.game.setDefaultSpeed(speedval)
            val boostval: Float = (speedBoostProgress/10).toFloat()
            Managers.game.setBoostSpeed(boostval)

            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            val buttonLayout = LinearLayout(this)
            buttonLayout.orientation = LinearLayout.HORIZONTAL
            val resetButton = Button(this)
            val stopButton = Button(this)

            stopButton.text = "Stop"
            resetButton.text = "Reset"
            buttonLayout.addView(resetButton)
            buttonLayout.addView(stopButton)

            heartLayout = LinearLayout(this)
            heartLayout.orientation = LinearLayout.HORIZONTAL
            heartLayout.gravity = Gravity.RIGHT
            val heartParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            heartParams.weight = 1f
            for(i in 1..Managers.game.currentHp) {
                val heartImage = ImageView(this)
                heartImage.setImageResource(R.drawable.heart)
                heartLayout.addView(heartImage)
            }
            Managers.game.context = context
            scoreView = TextView(this)
            scoreView!!.text = Managers.game.currentScore.toString()
            Managers.game.playerName = playerName
            scoreView!!.textSize = 20f
            buttonLayout.addView(scoreView)
            buttonLayout.addView(heartLayout, heartParams)

            stopButton.setOnClickListener { _ ->
                Managers.game.clearData()
                Managers.scene.currentScene!!.reset()
//                this.recreate()
                finish()
                startActivity(getIntent())
                overridePendingTransition(0, 0)
            }
            resetButton.setOnClickListener { _ ->
                mGLView!!.resetRenderer()
            }
            val glParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            glParams.weight = 1f
            val buttonParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layout.addView(buttonLayout, buttonParams)
//            layout.addView(heartLayout, heartParams)
            layout.addView(mGLView, glParams)
            gameLayout = layout
            setContentView(layout)
        }

        val directClickPlay = prefs.getString("PlayReset", "0")
        if (directClickPlay == "1"){
            prefs.setString("PlayReset", "0")
            playButton.performClick();
        }

        settingsButton.setOnClickListener {
            val settingLayout = R.layout.setting
            setContentView(settingLayout)
            val btn_number_select: Button = findViewById(R.id.btn_number_select)
            level = prefs.getString("CurLevel", "5").toInt()
            btn_number_select.text = level.toString()
            btn_number_select.setOnClickListener {
                val layout = layoutInflater.inflate(R.layout.dialog_num_select, null)
                val build = AlertDialog.Builder(this).apply {
                    setView(layout)
                }
                val dialog = build.create()
//                dialog.getWindow()?.setDimAmount(0f);
//                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);

                dialog.show()
                val numberPicker : NumberPicker = layout.findViewById(R.id.numberPicker)
                numberPicker.minValue = 0
                numberPicker.maxValue = 10
                if(level != 0) numberPicker.value = level
                val btn_cancel: Button = layout.findViewById(R.id.btn_cancel)
                val btn_ok: Button = layout.findViewById(R.id.btn_ok)
                btn_cancel.setOnClickListener {
                    dialog.dismiss()
                }
                btn_ok.setOnClickListener { // Enter Button
                    selectNum = numberPicker.value
                    btn_number_select.text = "Level: $selectNum"
                    level = selectNum
                    Managers.scene.startStage(level)
                    Managers.game.setStage(level)
                    prefs.setString("CurLevel", level.toString())
                    dialog.dismiss()
                }
            }
//            level = 1 // * 4*5 = 20 laser

            val backButton: Button = findViewById(R.id.backButton)
            backButton.setOnClickListener {
//                this.recreate()
                finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }

            val seekSense: SeekBar = findViewById(R.id.seekSense)
            gyroProgress = prefs.getString("Gyro", "0").toInt()
            seekSense.setMin(-10); seekSense.setMax(10)
            seekSense.setProgress(gyroProgress, true)
            seekSense.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
                    gyroProgress = progress
                }
                override fun onStartTrackingTouch(seek: SeekBar){}
                override fun onStopTrackingTouch(seek: SeekBar){
                    prefs.setString("Gyro", gyroProgress.toString())
                }
            })
            val seekDefSpeed: SeekBar = findViewById(R.id.seekDefaultSpeed)
            speedProgress = prefs.getString("DefSpeed", "0").toInt()
            seekDefSpeed.setMin(-10); seekDefSpeed.setMax(10)
            seekDefSpeed.setProgress(speedProgress, true)
            seekDefSpeed.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
                    speedProgress = progress
                }
                override fun onStartTrackingTouch(seek: SeekBar){}
                override fun onStopTrackingTouch(seek: SeekBar){
                    prefs.setString("DefSpeed", speedProgress.toString())
                }
            })
            val seekBoost: SeekBar = findViewById(R.id.seekBoost)
            speedBoostProgress = prefs.getString("Boost", "0").toInt()
            seekBoost.setMin(-10); seekBoost.setMax(10)
            seekBoost.setProgress(speedBoostProgress, true)
            seekBoost.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
                    speedBoostProgress = progress
                }
                override fun onStartTrackingTouch(seek: SeekBar){}
                override fun onStopTrackingTouch(seek: SeekBar){
                    prefs.setString("Boost", speedBoostProgress.toString())
                }
            })
        }


        leaderButton.setOnClickListener {
            val leaderLayout = R.layout.leaderboard
            setContentView(leaderLayout)

            // Content of LeaderBoard
            val table: TableLayout = findViewById(R.id.table)

            // TODO: ADD ROW DYNAMICALLY
            val json: String = prefs.getString("Best5", "None")

            if (json != "None"){
                val players = Gson().fromJson(json, Array<playerData>::class.java).toMutableList()
                for (idx in 0 until players.size){
                    val row1 = TableRow(this)
                    val rank = TextView(this)
                    val player = TextView(this)
                    val time = TextView(this)
                    val points = TextView(this)
                    val duration = TextView(this)
                    val param = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 1F
                    )

                    val player1 = players[idx]
                    rank.setText(" " + (idx+1).toString())
                    player.setText(player1.playername)
                    time.setText(player1.time.toString())
                    points.setText(player1.score.toString())
                    duration.setText(player1.duration.toString())

                    rank.setLayoutParams(param)
                    param.weight = 0.3f; row1.addView(rank, param)
                    param.weight = 0.5f; row1.addView(player, param)
                    param.weight = 1f; row1.addView(time, param)
                    row1.addView(points, param)
                    row1.addView(duration, param)
                    row1.setBackgroundColor(0xFFF0F7F7.toInt()) // R.color.colorForRow F0F7F7 is
                    row1.setPadding(0, 5, 0, 5)
                    table.addView(row1)
                }
            }

            val backButton: Button = findViewById(R.id.backButton)
            backButton.setOnClickListener {
//                this.recreate()
                finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }

            val resetLeaderboards: Button = findViewById(R.id.resetLeaderboards)
            resetLeaderboards.setOnClickListener {
                prefs.setString("Best5", "None")
                leaderButton.performClick()
            }

        }

        Managers.game.mainActivity = this
    }

    private fun setButtonText(button: ToggleButton, text: String) {
        button.text = text
        button.textOn = text
        button.textOff = text
    }

    override fun onPause() {
        super.onPause()
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView!!.onPause()
    }

    override fun onResume() {
        super.onResume()
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView!!.onResume()
    }

    fun updateScore(score: Long, level:Int, time:Float) {
        scoreView?.text =  "Score: " + score.toString() +
                " || Level: " +level.toString() + " || Time: " + String.format("%.3f", time)+ "s"
    }

    fun show321(){
        this.runOnUiThread {
            val layout = layoutInflater.inflate(R.layout.three21, null)
            val build = AlertDialog.Builder(this).apply {
                setView(layout)
            }

            dialog321 = build.create()
            dialog321?.getWindow()?.setDimAmount(0f)
            dialog321?.window?.setBackgroundDrawableResource(android.R.color.transparent);
            dialog321?.show()
            text321 = layout.findViewById(R.id.text321)
            text321?.text = "3"
        }
    }

    fun update321(time: Int){
        text321?.text = time.toString()
    }

    fun showCombo(combonus: Int){
        this.runOnUiThread {
            val layout = layoutInflater.inflate(R.layout.three21, null)
            val build = AlertDialog.Builder(this).apply {
                setView(layout)
            }
            dialogcombo = build.create()
            dialogcombo?.getWindow()?.setDimAmount(0f)
            dialogcombo?.window?.setBackgroundDrawableResource(android.R.color.transparent);
            dialogcombo?.show()
            val textcombo: TextView = layout.findViewById(R.id.text321)
            textcombo.text = "Combo +" + combonus.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun resetPlayView(){

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateHp(){
        heartLayout.removeAllViews()
        for(i in 1..Managers.game.currentHp) {
            val heartImage = ImageView(this)
            heartImage.setImageResource(R.drawable.heart)
            heartLayout.addView(heartImage)
        }
        if (Managers.game.currentHp < 1) {
            Managers.game.finishGame()
//            mGLView!!.onPause()
            val layout = layoutInflater.inflate(R.layout.clash, null)
            val build = AlertDialog.Builder(this).apply {
                setView(layout)
            }
            val dialog1 = build.create()
            dialog1.show()
            layout.setOnClickListener { // click inside the box
                dialog1.dismiss()
                Managers.game.clearData()
                Managers.game.isLaugh = false
                Managers.scene.currentScene!!.reset()
                finish()
                startActivity(getIntent())
                overridePendingTransition(0, 0)
            }
            dialog1.setOnCancelListener { // click elsewhere
                dialog1.dismiss()
                Managers.game.clearData()
                Managers.scene.currentScene!!.reset()
                Managers.game.isLaugh = false
//                mGLView!!.resetRenderer()
//                mGLView!!.onResume()
//                Managers.obj.clear()
                prefs.setString("PlayReset", "1") // 1 Means
                finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }

        }
    }

    companion object {
        var context: Context? = null
        lateinit var prefs: PreferenceUtil
    }
}


