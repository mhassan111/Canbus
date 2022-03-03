package com.os.system.featureInstallation.presentation.activation

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.os.system.featureInstallation.domain.models.ActivateDevice
import com.os.system.featureInstallation.domain.useCases.ActivateServiceUseCase
import com.os.system.featureInstallation.domain.util.ActivateServiceApiResponse
import com.os.system.utilities.AppConstants
import com.os.system.utilities.DeviceInformationUtil
import com.os.system.utilities.formatCode
import com.os.system.utilities.logVerbose
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ServiceActivationViewModel @Inject constructor(
    @ApplicationContext val applicationContext: Context,
    private val activateServiceUseCase: ActivateServiceUseCase
) : ViewModel() {

    /** Activation Code TextField State */
    private val _activationCodeTextFieldState = mutableStateOf(ActivationCodeTextFieldState())
    val activationCodeTextFieldState: State<ActivationCodeTextFieldState> =
        _activationCodeTextFieldState

    /** Mutable Shared Flow UiEvent For dispatching the UI Related events Like display a Message in SnackBar */
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    /**
     *  Handles the Service activation Events [ServiceActivationEvent]
     *  [ServiceActivationEvent.ChangeTextFocus] This is called If the Activation Code TextField focus changes
     *  [ServiceActivationEvent.EnteredActivationCode] Called when a Code is entered
     *  [ServiceActivationEvent.ActivateService] Initiates the Service activation process
     */
    fun onEvent(event: ServiceActivationEvent) {
        when (event) {
            // Make Hint to disappear w.r.t to Focus change
            is ServiceActivationEvent.ChangeTextFocus -> {
                _activationCodeTextFieldState.value = activationCodeTextFieldState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            activationCodeTextFieldState.value.text.isBlank()
                )
            }

            // Gets the Updated Activation Code and saves
            is ServiceActivationEvent.EnteredActivationCode -> {
                _activationCodeTextFieldState.value = activationCodeTextFieldState.value.copy(
                    text = event.code
                )
            }

            // Starts the Service activation process
            ServiceActivationEvent.ActivateService -> {
                activateService()
            }
        }
    }

    private fun activateService(): Unit {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val activateService = getServiceActivation()
                logVerbose("${AppConstants.SERVICE_ACTIVATION_TYPE} activate service = $activateService")
                _eventFlow.emit(UiEvent.ActivatingService)
                when (val activateServiceApiResponse =
                    activateServiceUseCase.invoke(activateService)) {
                    is ActivateServiceApiResponse.ActivationServiceError -> {
                        logVerbose("${AppConstants.SERVICE_ACTIVATION_TYPE} Api Error = ${activateServiceApiResponse.error}")
                        _eventFlow.emit(UiEvent.ShowSnackBar("Error While Activating Service. Retry"))
                    }
                    is ActivateServiceApiResponse.ActivationServiceSuccess -> {
                        val activateServiceResponse =
                            activateServiceApiResponse.activateServiceResponse
                        logVerbose("${AppConstants.SERVICE_ACTIVATION_TYPE} Api Success = $activateServiceResponse")
                        when (activateServiceResponse.statusCode) {
                            "200" -> {
                                _eventFlow.emit(UiEvent.ShowSnackBar("test"))
                            }
                            "403" -> {
                                _eventFlow.emit(UiEvent.ShowSnackBar("Service is Already Activated. De-Activate it"))
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

    private fun getServiceActivation(): ActivateDevice {
        val activateService = ActivateDevice()
        activateService.apply {
            this.phoneServiceCode = activationCodeTextFieldState.value.text.formatCode
            this.carrierName = DeviceInformationUtil.getNetworkOperator(applicationContext)
            this.mccMnc = DeviceInformationUtil.getMccMnc(applicationContext)
            this.phoneServiceDevice = DeviceInformationUtil.PHONE_SERVICE_DEVICE
            this.phoneServiceImeiNo =
                DeviceInformationUtil.getIMEINumber(applicationContext)
            this.phoneServiceModel = DeviceInformationUtil.deviceModel
            this.phoneServiceOs = DeviceInformationUtil.deviceOS
            this.phoneServiceSimId = DeviceInformationUtil.getSimId(applicationContext)
            this.phoneServiceVersion =
                "${DeviceInformationUtil.versionName}, Code = ${DeviceInformationUtil.versionCode}"
        }
        return activateService
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object ServiceActivationSuccess : UiEvent()
        object ActivatingService : UiEvent()
    }
}
