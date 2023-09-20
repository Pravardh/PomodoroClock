package com.learning.pomodoroclock

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.pomodoroclock.databinding.ActivitySettingBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    companion object {

        private var studyTimeInMilliseconds: Long = 1500000
        private var shortBreakTimeInMilliseconds: Long = 300000
        private var longBreakTimeInMilliseconds: Long = 600000

        fun setValues(
            studyTimeInMilliseconds: Long,
            shortBreakTimeInMilliseconds: Long,
            longBreakTimeInMilliseconds: Long
        ) {
            this.studyTimeInMilliseconds = studyTimeInMilliseconds
            this.shortBreakTimeInMilliseconds = shortBreakTimeInMilliseconds
            this.longBreakTimeInMilliseconds = longBreakTimeInMilliseconds
        }

        fun getValue(timerMode: TimerMode): Long = when (timerMode) {
            TimerMode.STUDY -> studyTimeInMilliseconds
            TimerMode.SHORT_BREAK -> shortBreakTimeInMilliseconds
            TimerMode.LONG_BREAK -> longBreakTimeInMilliseconds
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val btnBack = binding.btnGoBack

        val etStudy = binding.etStudyTimer
        val etShort = binding.etShortBreakTimer
        val etLong = binding.etLongBreakTimer


        btnBack.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            val studyTimeInMilliseconds = etStudy.text.toString().toLong() * 60000
            val shortBreakTimeInMilliseconds = etShort.text.toString().toLong() * 60000
            val longBreakTimeInMilliseconds = etLong.text.toString().toLong() * 60000

            setValues(
                studyTimeInMilliseconds,
                shortBreakTimeInMilliseconds,
                longBreakTimeInMilliseconds
            )

            startActivity(intent)
            finish()

        }

    }
}
