package com.screening.app.featureCallScreening.presentation.phoneNumbers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.useCase.PhoneNumberUseCases
import com.screening.app.featureCallScreening.domain.util.OrderType
import com.screening.app.featureCallScreening.domain.util.PhoneNumberOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneNumberViewModel @Inject constructor(private val phoneNumberUseCases: PhoneNumberUseCases) :
    ViewModel() {

    private val _state = mutableStateOf(PhoneNumberState())
    val state: State<PhoneNumberState> = _state

    private var recentlyDeletedPhoneNumber: PhoneNumber? = null
    private var getPhoneNumbersJob: Job? = null

    init {
        getPhoneNumbers(phoneNumberOrder = PhoneNumberOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: PhoneNumberEvent) {
        when (event) {
            is PhoneNumberEvent.DeletePhoneNumber -> {
                viewModelScope.launch {
                    phoneNumberUseCases.deletePhoneNumberUseCase(event.phoneNumber)
                    recentlyDeletedPhoneNumber = event.phoneNumber
                }
            }
            is PhoneNumberEvent.Order -> {
                if (state.value.phoneNumberOrder::class == event.phoneNumberOrder::class
                    && state.value.phoneNumberOrder.orderType == event.phoneNumberOrder.orderType
                ) {
                    return
                }
                getPhoneNumbers(event.phoneNumberOrder)
            }
            PhoneNumberEvent.RestorePhoneNumber -> {
                viewModelScope.launch {
                    phoneNumberUseCases.insertPhoneNumberUseCase(
                        recentlyDeletedPhoneNumber ?: return@launch
                    )
                    recentlyDeletedPhoneNumber = null
                }
            }
            PhoneNumberEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getPhoneNumbers(phoneNumberOrder: PhoneNumberOrder) {
        getPhoneNumbersJob?.cancel()
        getPhoneNumbersJob = phoneNumberUseCases.getPhoneNumbersUseCase(phoneNumberOrder)
            .onEach { phoneNumbers ->
                _state.value = state.value.copy(
                    numbers = phoneNumbers,
                    phoneNumberOrder = phoneNumberOrder
                )
            }.launchIn(viewModelScope)
    }
}