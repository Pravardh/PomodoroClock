package com.learning.pomodoroclock

import android.os.CountDownTimer

class TimerPresenter(
    private val timerFormatter: TimeFormatter = TimeFormatter(),
) {

    private var view: TimerView? = null

    private var timerMode: TimerMode? = null

    private var timer: CountDownTimer? = null

    fun attachView(view: TimerView) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun onSettingsButtonClicked() {
        view?.navigateToSettingsScreen()
    }

    fun onStartButtonClicked() {
        timer?.start()
    }

    fun onResetButtonClicked() {
        timer?.cancel()
        view?.setTimerValue(R.string.reset_text)
    }

    fun onTimerModeSelected(position: Int) {
        val newTimerMode = when (position) {
            0 -> TimerMode.STUDY
            1 -> TimerMode.SHORT_BREAK
            2 -> TimerMode.LONG_BREAK
            else -> TimerMode.STUDY
        }
        if (timerMode == newTimerMode) return
        timerMode = newTimerMode
        val selectedTimeInMillis = SettingsActivity.getValue(newTimerMode)
        timer?.cancel()
        timer = createTimer(selectedTimeInMillis = selectedTimeInMillis)
        view?.setTimerValue(timerFormatter(selectedTimeInMillis))
    }

    private fun createTimer(selectedTimeInMillis: Long): CountDownTimer {
        return object : CountDownTimer(selectedTimeInMillis, COUNTDOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                view?.setTimerValue(timerFormatter(millisUntilFinished))
            }

            override fun onFinish() = Unit
        }
    }

    private companion object {
        const val COUNTDOWN_INTERVAL = 59L
    }
}
