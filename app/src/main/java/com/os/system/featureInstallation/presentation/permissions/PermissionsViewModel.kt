package com.os.system.featureInstallation.presentation.permissions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PermissionsViewModel @Inject constructor() : ViewModel() {

    /** Shared flow to receive the permissions launch events **/
    private val _permissionEventSharedFlow = MutableSharedFlow<PermissionEvent>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val permissionEventSharedFlow: SharedFlow<PermissionEvent> = _permissionEventSharedFlow

    /** permission ui event flow **/
    private val _permissionUiEventFlow = MutableSharedFlow<PermissionUiEvent>()
    val permissionUiEventFlow: SharedFlow<PermissionUiEvent> = _permissionUiEventFlow

    /** Represents the state of the current permission screen **/
    private val _permissionEventState = MutableStateFlow<PermissionEvent>(NoEvent)
    val permissionEventState: StateFlow<PermissionEvent> = _permissionEventState.asStateFlow()

    /**
     * Called When a specific permission event [PermissionEvent] is fired,
     * to launch the Request for granting that permission
     */
    fun callPermissionEvent(event: PermissionEvent) {
        viewModelScope.launch {
            _permissionEventSharedFlow.emit(event)
        }
    }

    fun firePermissionUiEvent(event: PermissionUiEvent): Unit {
        viewModelScope.launch {
            _permissionUiEventFlow.emit(event)
        }
    }

    /**
     * Sets the State of the Current Permission State [PermissionEvent],
     * to launch the current permission screen
     */
    fun setPermissionState(event: PermissionEvent): Unit {
        _permissionEventState.value = event
    }
}

sealed class PermissionUiEvent {
    object skipPermission : PermissionUiEvent()
}
