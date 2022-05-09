package com.screening.app.featureInstallation.presentation.permissions

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screening.app.featureInstallation.WholeSpyScreen
import com.screening.app.featureInstallation.presentation.util.AppPermissionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class MainViewModel @Inject constructor(@ApplicationContext private val applicationContext: Context) :
    ViewModel() {

    // Shared flow to receive the permissions launch events
    private val _permissionEventSharedFlow = MutableSharedFlow<Screen>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val screenSharedFlow: SharedFlow<Screen> = _permissionEventSharedFlow

    // permission ui event flow
    private val _permissionUiEventFlow = MutableSharedFlow<PermissionUiEvent>()
    val permissionUiEventFlow: SharedFlow<PermissionUiEvent> = _permissionUiEventFlow

    // State of the Current Screen
    private val _currentScreen =
        MutableStateFlow<WholeSpyScreen>(WholeSpyScreen.DeviceActivationScreen)
    val currentScreen: StateFlow<WholeSpyScreen> = _currentScreen.asStateFlow()
    val currentScreenState = mutableStateOf(WholeSpyScreen.DeviceActivationScreen)

    init {
        val wholeSpyScreen = getWholeSpyScreen()
        currentScreenState.value = wholeSpyScreen
        _currentScreen.value = wholeSpyScreen
    }

    fun setCurrentScreen(screen: WholeSpyScreen) {
        _currentScreen.value = screen
//        currentScreenState.value = screen
    }

    private fun getWholeSpyScreen(): WholeSpyScreen {
        val activeScreen = AppPermissionUtil.getCurrentWholeSpyScreen(applicationContext)
        setCurrentScreen(activeScreen)
        return activeScreen
    }

    // Called When a specific permission event [Screen] is fired,
    // to launch the Request for granting that permission
    fun callPermissionEvent(event: Screen) {
        viewModelScope.launch {
            _permissionEventSharedFlow.emit(event)
        }
    }

    fun firePermissionUiEvent(event: PermissionUiEvent): Unit {
        viewModelScope.launch {
            _permissionUiEventFlow.emit(event)
        }
    }
}

sealed class PermissionUiEvent {
    object skipPermission : PermissionUiEvent()
}
