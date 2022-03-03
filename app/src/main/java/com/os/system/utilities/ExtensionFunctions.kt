package com.os.system.utilities

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.os.system.featureInstallation.presentation.permissions.AnnotatedText
import com.os.system.ui.sizes
import com.os.system.ui.theme.BlackColor
import com.os.system.ui.theme.WhiteColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

val String.formatCode
    get() = if (!startsWith("#") || !startsWith("##"))
        "##$this".trim() else this.trim()

@ExperimentalPermissionsApi
fun PermissionState.isPermanentlyDenied(): Boolean {
    return !this.hasPermission && !this.shouldShowRationale
}

fun Int.incrementOne() = this + 1

fun Context.checkPermissionGranted(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun List<AnnotatedText>.buildAnnotatedText(
    spanStyle: SpanStyle = SpanStyle(
        color = WhiteColor,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
): AnnotatedString {
    val texts = this
    val annotatedString = buildAnnotatedString {
        texts.forEach { annotatedText ->
            if (annotatedText.annotate) {
                withStyle(style = spanStyle) {
                    append(annotatedText.text)
                }
            } else {
                append(annotatedText.text)
            }
        }
    }
    return annotatedString
}

fun <T> ComponentActivity.collectLatestLifeCycleFlow(
    flow: Flow<T>,
    collect: suspend (value: T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}

fun <T> LifecycleOwner.collectWhenStarted(
    flow: Flow<T>,
    firstTimeDelay: Long = 0L,
    action: suspend (value: T) -> Unit
) {
    lifecycleScope.launch {
        delay(firstTimeDelay)
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(action)
        }
    }
}
