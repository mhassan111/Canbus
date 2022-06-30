package com.canbus.app.utilities

import android.content.Context
import android.content.pm.PackageManager
import android.util.Patterns
import android.view.View
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun String.isValidPhoneNumber(): Boolean {
    return !isNullOrEmpty() && Patterns.PHONE.matcher(this).matches()
}

@ExperimentalPermissionsApi
fun PermissionState.isPermanentlyDenied(): Boolean {
    return !this.hasPermission && !this.shouldShowRationale
}

fun Int.incrementOne() = this + 1

fun Context.checkPermissionGranted(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
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
//            flow.collect(action)
        }
    }
}

fun View.showSnackBar(
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: (View) -> Unit,
) {
    val snackbar = Snackbar.make(this, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(this)
        }
    }
    snackbar.show()
}

fun AppCompatActivity.shouldShowRequestPermissionRationaleCompat(permissions: Array<String>): Boolean {
    for (i in permissions.indices) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]))
            return true
    }
    return false
}
