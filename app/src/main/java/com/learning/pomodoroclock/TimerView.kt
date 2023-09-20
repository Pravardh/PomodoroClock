package com.learning.pomodoroclock

import androidx.annotation.StringRes

interface TimerView {

    fun navigateToSettingsScreen()

    fun setTimerValue(text: String)

    fun setTimerValue(@StringRes stringResId: Int)
}
