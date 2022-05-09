package com.screening.app.featureSmsScreening

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.screening.app.featureSmsScreening.presentation.conversations.ConversationsScreen
import com.screening.app.featureSmsScreening.presentation.conversations.componets.ConversationsTopBar
import com.screening.app.ui.theme.ScreeningAppTheme
import com.screening.app.utilities.logVerbose

class SmsScreeningActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmsScreeningApp()
        }
    }
}

@Composable
fun SmsScreeningApp() {
    ScreeningAppTheme {
        val context = LocalContext.current
        Scaffold(
            topBar = {
                ConversationsTopBar(onSelected = {
                    context.logVerbose("on selected")
                }, onSettingTapped = {
                    context.logVerbose("on settings tabled")
                })
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                ConversationsScreen()
            }
        }
    }
}