package com.learning.pomodoroclock

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.learning.pomodoroclock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TimerView {

    private val presenter: TimerPresenter = TimerPresenter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bind()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun navigateToSettingsScreen() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    override fun setTimerValue(text: String) {
        binding.tvClock.text = text
    }

    override fun setTimerValue(stringResId: Int) {
        val text = getString(stringResId)
        return setTimerValue(text)
    }

    private fun bind() {
        with(binding) {
            settingsBtn.setOnClickListener { presenter.onSettingsButtonClicked() }
            startBtn.setOnClickListener { presenter.onStartButtonClicked() }
            resetBtn.setOnClickListener { presenter.onResetButtonClicked() }

            val modes = resources.getStringArray(R.array.modes)
            modeSpinner.adapter =
                ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, modes)
            modeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) = presenter.onTimerModeSelected(position)

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
        }
    }
}
