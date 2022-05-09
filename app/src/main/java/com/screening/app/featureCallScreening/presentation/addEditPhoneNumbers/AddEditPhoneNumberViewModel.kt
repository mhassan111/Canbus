package com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screening.app.featureCallScreening.domain.model.InvalidPhoneNumberException
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureCallScreening.domain.useCase.PhoneNumberUseCases
import com.screening.app.utilities.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditPhoneNumberViewModel @Inject constructor(
    private val phoneNumberUseCases: PhoneNumberUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _phoneNumberState = mutableStateOf(TextFieldState(hint = "Enter Phone Number..."))
    val state: State<TextFieldState> = _phoneNumberState

    private var _contactNameState =
        mutableStateOf<TextFieldState>(TextFieldState(hint = "Enter Name..."))
    val contactNameState: MutableState<TextFieldState> = _contactNameState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPhoneNumberId: Int? = null

    init {
        savedStateHandle.get<String>("phoneNumber")?.let { phoneNumber ->
            if (phoneNumber.isNotBlank()) {
                viewModelScope.launch {
                    phoneNumberUseCases.getPhoneNumberByPhoneNumber(phoneNumber)
                        ?.also { phoneNumber ->
                            currentPhoneNumberId = phoneNumber.id
                            _phoneNumberState.value = state.value.copy(
                                text = phoneNumber.phoneNumber,
                                isHintVisible = false
                            )
                        }
                }
            }
        }
    }

    fun onEvent(event: AddEditPhoneNumberEvent) {
        when (event) {
            is AddEditPhoneNumberEvent.ChangePhoneNumberFocus -> {
                _phoneNumberState.value = state.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            state.value.text.isBlank()
                )
            }

            is AddEditPhoneNumberEvent.EnteredPhoneNumber -> {
                _phoneNumberState.value = state.value.copy(
                    text = event.value
                )
            }

            is AddEditPhoneNumberEvent.ChangeContactNameFocus -> {
                _contactNameState.value = _contactNameState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            contactNameState.value.text.isBlank()
                )
            }
            is AddEditPhoneNumberEvent.EnteredContactName -> {
                _contactNameState.value = contactNameState.value.copy(
                    text = event.value
                )
            }

            AddEditPhoneNumberEvent.SavePhoneNumber -> {
                viewModelScope.launch {
                    try {
                        phoneNumberUseCases.insertPhoneNumberUseCase(
                            PhoneNumber(
                                phoneNumber = Util.parsePhoneNumber(state.value.text),
                                contactId = Util.randomUUID(),
                                contactName = contactNameState.value.text,
                                date = Date(),
                                id = currentPhoneNumberId
                            )
                        )
                        _eventFlow.emit(UiEvent.SavePhoneNumber)
                    } catch (e: InvalidPhoneNumberException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save phone number"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SavePhoneNumber : UiEvent()
    }
}
