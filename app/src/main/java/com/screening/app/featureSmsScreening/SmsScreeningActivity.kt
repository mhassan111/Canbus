package com.screening.app.featureSmsScreening

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.screening.app.R
import com.screening.app.featureSmsScreening.presentation.conversations.ConversationsScreen
import com.screening.app.featureSmsScreening.presentation.conversations.componets.ConversationsTopBar
import com.screening.app.featureSmsScreening.presentation.conversations.componets.InitialImage
import com.screening.app.featureSmsScreening.presentation.newMessage.NewMessageActivity
import com.screening.app.ui.theme.ScreeningAppTheme
import com.screening.app.utilities.logVerbose
import com.screening.app.utilities.startActivityWithData

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
//        NameInitialsList()
        val context = LocalContext.current
        Scaffold(
            topBar = {
                ConversationsTopBar(onSelected = {
                    context.logVerbose("on selected")
                }, onSettingTapped = {
                    context.logVerbose("on settings tabled")
                })
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    startNewConversationActivity(context)
                }, backgroundColor = MaterialTheme.colors.primary) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Start New Conversation",
                        tint = Color.White
                    )
                }
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                ConversationsScreen()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NameInitialsList() {
    val alphabets = ('A'..'Z').toMutableList()
    Box(modifier = Modifier.offset(50.dp)) {
        InitialImage(text = "Aa")
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(alphabets) { initial ->
            Text(text = initial.toString(), modifier = Modifier.pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        logVerbose("")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        logVerbose("")
                    }
                    MotionEvent.ACTION_UP -> {
                        logVerbose("")
                    }
                    else -> false
                }
                true
            })
        }
    }
}

private fun startNewConversationActivity(context: Context) {
    context.startActivityWithData<NewMessageActivity>(
        listOf(
            Intent.FLAG_ACTIVITY_NEW_TASK,
            Intent.FLAG_ACTIVITY_CLEAR_TOP
        )
    )
}