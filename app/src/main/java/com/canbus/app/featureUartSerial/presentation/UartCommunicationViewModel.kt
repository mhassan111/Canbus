package com.canbus.app.featureUartSerial.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canbus.app.featureUartSerial.domain.model.CanbusData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class UartCommunicationViewModel @Inject constructor() : ViewModel() {

    private val _messageState = mutableStateOf(TextFieldState("", hint = "Send message..."))
    val messageState: MutableState<TextFieldState> = _messageState

    private val _uartSerialCommunicationState = mutableStateOf(UartCommunicationState())
    val uartSerialCommunicationState = _uartSerialCommunicationState

    init {
        viewModelScope.launch {
//            delay(1000)
//            setDataPacket("@@@@V1/80/V2/80/V3/60/V4/80/V5/10/V6/100/V7/20/V8/100/V9/25/V10/123/V11/123/V12/123/####")
//            _uartSerialCommunicationState.value = uartSerialCommunicationState.value.copy(
//                speed = CanbusData(tag = Canbus.Speed.name, data = "100")
//            )
//            _uartSerialCommunicationState.value
        }
    }

    fun setDataPacket(data: String) {
        if (data.startsWith("@@@@") && data.endsWith("####")) {
            val dataPacket = data.replace("@@@@", "").replace("/####", "")
            val packets = dataPacket.split("/")
            val dataList = mutableListOf<String>()
            for (i in 1 until packets.size step 2) {
                println("packet = ${packets[i]}")
                dataList.add(packets[i])
            }

            val state = UartCommunicationState(dataPacket = dataPacket, dataList = dataList)
            _uartSerialCommunicationState.value = state
        } else {
            println("Not a valid data packet")
        }
    }

    fun onEvent(event: UartCommunicationEvent) {
        when (event) {
            is UartCommunicationEvent.ChangeUartMessageFocus -> {
                _messageState.value = messageState.value.copy(
                    isHintVisible = messageState.value.text.isBlank()
                            && !event.focusState.isFocused
                )
            }
            is UartCommunicationEvent.EnteredUartMessage -> {
                _messageState.value = messageState.value.copy(text = event.value)
            }
        }
    }

}