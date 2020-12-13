package com.im.bin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.im.bin.appUtils.Util
import com.im.bin.services.ForegroundService

class StartServiceActivity : AppCompatActivity() {

    companion object {
        const val SERVICE_TYPE = "SERVICE_TYPE"
        const val FOREGROUND_SERVICE_TYPE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let {
            when (intent.getIntExtra(SERVICE_TYPE, 0)) {
                0 -> {
                    startForegroundService()
                }
                else -> {
                    startForegroundService()
                }
            }
        }
        finish()
    }

    private fun startForegroundService() {
        Util.startService(this, Intent(this, ForegroundService::class.java))
    }
}