package com.learning.pomodoroclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlin.system.measureTimeMillis

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val btnBack = findViewById<Button>(R.id.btn_goBack)

        val etStudy = findViewById<EditText>(R.id.et_studyTimer)
        val etShort = findViewById<EditText>(R.id.et_shortBreakTimer)
        val etLong = findViewById<EditText>(R.id.et_longBreakTimer)

        btnBack.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            val studyTimeInMilliseconds = etStudy.text.toString().toInt() * 60000
            val shortBreakTimeInMilliseconds = etShort.text.toString().toInt() * 60000
            val longBreakTimeInMilliseconds = etLong.text.toString().toInt() * 60000

            intent.putExtra("Study", studyTimeInMilliseconds )
            intent.putExtra("Short Break", shortBreakTimeInMilliseconds )
            intent.putExtra("Long Break", longBreakTimeInMilliseconds )

            startActivity(intent)

        }

    }
}