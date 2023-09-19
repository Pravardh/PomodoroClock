package com.learning.pomodoroclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.pomodoroclock.databinding.ActivitySettingBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    object SettingsSingleton {

        private var studyTimeInMilliseconds: Int = 1500000
        private var shortBreakTimeInMilliseconds: Int = 300000
        private var longBreakTimeInMilliseconds: Int = 600000

        fun setValues(studyTimeInMilliseconds: Int, shortBreakTimeInMilliseconds: Int, longBreakTimeInMilliseconds: Int)
        {
            this.studyTimeInMilliseconds = studyTimeInMilliseconds
            this.shortBreakTimeInMilliseconds = shortBreakTimeInMilliseconds
            this.longBreakTimeInMilliseconds = longBreakTimeInMilliseconds
        }

        fun getValue(valueToGet: String) : Int{
                when(valueToGet){
                    "Study" -> return studyTimeInMilliseconds
                    "Short Break" -> return shortBreakTimeInMilliseconds
                    "Long Break" -> return longBreakTimeInMilliseconds

                }
            return 0
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

            val studyTimeInMilliseconds = etStudy.text.toString().toInt() * 60000
            val shortBreakTimeInMilliseconds = etShort.text.toString().toInt() * 60000
            val longBreakTimeInMilliseconds = etLong.text.toString().toInt() * 60000

            SettingsSingleton.setValues(studyTimeInMilliseconds, shortBreakTimeInMilliseconds, longBreakTimeInMilliseconds)

            startActivity(intent)
            finish()

        }

    }
}