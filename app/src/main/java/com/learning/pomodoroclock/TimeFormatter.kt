package com.learning.pomodoroclock

class TimeFormatter : (Long) -> String {
    override fun invoke(millis: Long): String {
        val startMinutes = ((millis / 1000) / 60)
        val startSeconds = ((millis / 1000) % 60)

        return String.format("%02d:%02d", startMinutes, startSeconds)
    }
}
