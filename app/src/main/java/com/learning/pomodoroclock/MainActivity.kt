package com.learning.pomodoroclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import com.learning.pomodoroclock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var timer: CountDownTimer

    var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        //Getting all the values from the layout

        val modes = resources.getStringArray(R.array.modes)

        val tvClock: TextView = binding.tvClock
        val spinner = binding.modeSpinner

        val startButton: Button = binding.startBtn
        val resetButton: Button = binding.resetBtn
        val settingsButton: Button = binding.settingsBtn

        settingsButton.setOnClickListener  {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


        startButton.setOnClickListener {

            //Set is_timer_running to true and start the timer which is assigned in the selectOption selectItem function
            isTimerRunning = true
            timer.start()

        }

        resetButton.setOnClickListener {

            timer.cancel()
            tvClock.text = getString(R.string.reset_text)
            isTimerRunning = false

        }

        //Mostly boiler plate stuff

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, modes)
        spinner.adapter = adapter

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, modes
        )
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                //Ensuring timer isn't running so that the values don't change. Although there's a bug: Value changes on the spinner obviously. SHOULD FIX
                if(!isTimerRunning)
                    selectItem(modes[position], tvClock)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("NOT IMPLEMENTED")
            }

        }

    }
    fun selectItem(selectItem: String, tvClock: TextView) {

        var minutes: Int
        var seconds: Int
        var time: String

        val studyTimer: Int = SettingsActivity.SettingsSingleton.getValue(selectItem)

       val startMinutes = ((studyTimer / 1000) / 60)
       val startSeconds = ((studyTimer / 1000) % 60)

       time = String.format("%02d:%02d", startMinutes, startSeconds)

       tvClock.text = time

       timer = object : CountDownTimer(studyTimer.toLong(), 59) {

       override fun onTick(millisUntilFinished: Long) {

            minutes = ((millisUntilFinished / 1000) / 60).toInt()
           seconds = ((millisUntilFinished / 1000) % 60).toInt()

           time = String.format("%02d:%02d", minutes, seconds)

           tvClock.text = time

       }

        override fun onFinish() {
            tvClock.text = getString(R.string.reset_text)
            isTimerRunning = false

        }

       }

    }

    }

