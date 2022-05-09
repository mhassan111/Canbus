package com.screening.app.featureInstallation.presentation.activation

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screening.app.featureInstallation.domain.useCases.DeviceActivationUseCase
import com.screening.app.featureInstallation.domain.util.Resource
import com.screening.app.utilities.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class DeviceActivationViewModel @Inject constructor(
    private val deviceActivationUseCase: DeviceActivationUseCase
) : ViewModel() {

    private val _activationCodeTextFieldState = mutableStateOf(ActivationCodeTextFieldState())
    val activationCodeTextFieldState: State<ActivationCodeTextFieldState> =
        _activationCodeTextFieldState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    fun onEvent(event: ServiceActivationEvent) {
        when (event) {
            is ServiceActivationEvent.ChangeTextFocus -> {
                _activationCodeTextFieldState.value = activationCodeTextFieldState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            activationCodeTextFieldState.value.text.isBlank()
                )
            }

            is ServiceActivationEvent.EnteredActivationCode -> {
                _activationCodeTextFieldState.value = activationCodeTextFieldState.value.copy(
                    text = event.code
                )
            }

            ServiceActivationEvent.ActivateService -> {
                callDeviceActivation()
            }
        }
    }

    private fun callDeviceActivation() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _eventFlow.emit(UiEvent.ActivatingService)
                deviceActivationUseCase.invoke(activationCodeTextFieldState.value.text)
                    .collect { deviceActivationResponse ->
                        logVerbose(deviceActivationResponse.toString())
                        when (deviceActivationResponse) {
                            is Resource.Error -> {
                                logException(
                                    message = "${AppConstants.DEVICE_ACTIVATION_TYPE} Exception = ${deviceActivationResponse.message}",
                                    throwable = deviceActivationResponse.exception
                                )
                                _eventFlow.emit(
                                    UiEvent.ShowSnackBar(
                                        deviceActivationResponse.message
                                            ?: "Error While Activating Device"
                                    )
                                )
                            }
                            is Resource.Success -> {
                                when (deviceActivationResponse.data?.statusCode) {
                                    "200" -> {
                                        _eventFlow.emit(UiEvent.DeviceActivationSuccess)
                                    }
                                    else -> {
                                        _eventFlow.emit(UiEvent.ShowSnackBar("Unable to Activate Service. Retry"))
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object DeviceActivationSuccess : UiEvent()
        object ActivatingService : UiEvent()
    }
}
