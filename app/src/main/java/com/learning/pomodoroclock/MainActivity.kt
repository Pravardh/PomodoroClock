package com.learning.pomodoroclock

import android.app.AlarmManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.view.View
import android.widget.*
import java.sql.Time

class MainActivity : AppCompatActivity() {

    var timerMode = mapOf("Study" to 1500000, "Short Break" to 300000, "Long Break" to 600000)

    lateinit var timer: CountDownTimer

    var is_timer_running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val modes = resources.getStringArray(R.array.modes)

        val tv_clock: TextView = findViewById(R.id.tv_clock)
        val spinner = findViewById<Spinner>(R.id.mode_spinner)

        val start_button: Button = findViewById(R.id.start_btn)
        val reset_button: Button = findViewById(R.id.reset_btn)
        val settings_button: Button = findViewById(R.id.settings_btn)

        settings_button.setOnClickListener  {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


        start_button.setOnClickListener {

            is_timer_running = true
            timer.start()

        }

        reset_button.setOnClickListener {

            timer.cancel()
            tv_clock.text = "00:00"
            is_timer_running = false

        }

        if (spinner != null) {

            var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, modes)
            spinner.adapter = adapter

            if (spinner != null) {
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

                        selectItem(modes[position], tv_clock)

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("NOT IMPLEMENTED")
                    }

                }

            }

        }

    }
    fun selectItem(selectItem: String, tvClock: TextView) {

        var studyTimer: Int? = timerMode[selectItem]
        var minutes: Int
        var seconds: Int
        var time: String

        if(studyTimer != null){
            val defaultValue: Int? = timerMode[selectItem]

            if(defaultValue != null)
                studyTimer = intent.getIntExtra(selectItem,  defaultValue)

            else{
                Toast.makeText(this, "ERROR: Default value is null!", Toast.LENGTH_LONG)
            }

            val startMinutes = (studyTimer/1000 / 60)
            val startSeconds = (studyTimer/1000 % 60)

            time = String.format("%02d:%02d", startMinutes,startSeconds);
            tvClock.text = time


            timer = object : CountDownTimer(studyTimer.toLong(), 59) {

                override fun onTick(millisUntilFinished: Long) {

                    minutes = ((millisUntilFinished / 1000) / 60).toInt()
                    seconds = ((millisUntilFinished / 1000) % 60).toInt()

                    time = String.format("%02d:%02d", minutes, seconds);

                    tvClock.text = time
                }

                override fun onFinish() {
                    tvClock.text = "00:00"
                    is_timer_running = false
                }
            }
        }

    }

    }
