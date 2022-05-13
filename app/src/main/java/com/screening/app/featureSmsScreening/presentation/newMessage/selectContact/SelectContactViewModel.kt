package com.screening.app.featureSmsScreening.presentation.newMessage.selectContact

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screening.app.featureCallScreening.presentation.addEditPhoneNumbers.TextFieldState
import com.screening.app.featureImportContacts.presentation.ImportContactState
import com.screening.app.featureSmsScreening.domain.useCase.ContactUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectContactViewModel @Inject constructor(
    private val contactUseCases: ContactUseCases
) : ViewModel() {

    private var _importContactState = mutableStateOf<ImportContactState>(ImportContactState())
    val importContactState: MutableState<ImportContactState> = _importContactState

    private var _startConversationState = mutableStateOf(TextFieldState(hint = "Search Contact..."))
    val startConversationState = _startConversationState

    init {
        importContacts()
    }

    fun onEvent(event: SelectContactEvent) {
        when (event) {
            is SelectContactEvent.ChangeSearchedContactFocusState -> {
                startConversationState.value = _startConversationState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            startConversationState.value.text.isBlank()
                )
            }
            is SelectContactEvent.SearchedContact -> {
                startConversationState.value = _startConversationState.value.copy(
                    text = event.value
                )

                val filteredContacts = importContactState.value.contactsList.filter {
                    it.contactFirstName.lowercase().startsWith(startConversationState.value.text.lowercase())
                }
                importContactState.value = _importContactState.value.copy(
                    filteredContactList = filteredContacts
                )
            }
        }
    }

    fun importContacts() {
        _importContactState.value = importContactState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            contactUseCases.getContactUseCase().collectLatest { contactList ->
                _importContactState.value = importContactState.value.copy(
                    isLoading = false,
                    contactsList = contactList,
                    filteredContactList = contactList
                )
            }
        }
    }
}