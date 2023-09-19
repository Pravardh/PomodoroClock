package com.learning.pomodoroclock

import android.app.AlarmManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import java.sql.Time

enum class TimerMode(timerInMilliseconds: Long) {
    STUDY_TIMER(1500000),
    SHORT_BREAK_TIMER(300000),
    LONG_BREAK_TIMER(600000)
}

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

        val alarmManager: AlarmManager

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

        val studyTimer: Int? = timerMode[selectItem]
        var minutes: Int
        var seconds: Int
        var time: String

        if(studyTimer != null){

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
