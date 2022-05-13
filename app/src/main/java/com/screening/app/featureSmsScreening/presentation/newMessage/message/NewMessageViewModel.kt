package com.screening.app.featureSmsScreening.presentation.newMessage.message

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers.TextFieldState
import com.screening.app.featureImportContacts.domain.model.Contact
import com.screening.app.featureSmsScreening.domain.useCase.ContactUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewMessageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val contactUseCases: ContactUseCases
) : ViewModel() {

    private var _contact: MutableState<Contact> = mutableStateOf(Contact())
    val contact = _contact
    var selectedContact: Contact? = null

    private var _messageState = mutableStateOf(TextFieldState())
    val messageState = _messageState

    init {
        savedStateHandle.get<String>("id")?.let { contactId ->
            viewModelScope.launch(Dispatchers.IO) {
                val contact = contactUseCases.selectContactUseCase(contactId)
                selectedContact = contact
            }
        }
    }

    fun onEvent(event: NewMessageEvent) {
        when (event) {
            is NewMessageEvent.EnteredMessage -> {
                _messageState.value = messageState.value.copy(
                    text = event.text
                )
            }
            is NewMessageEvent.EnteredMessageFocusStateChange -> {
                _messageState.value = messageState.value.copy(
                    isHintVisible = messageState.value.text.isNotBlank()
                            && !event.focusState.isFocused
                )
            }
            NewMessageEvent.SendMessage -> {
                // TODO: send the message to the selected contact
            }
            NewMessageEvent.StartPhoneCall -> {
                // TODO: start phone call with the selected contact
            }
        }
    }
}