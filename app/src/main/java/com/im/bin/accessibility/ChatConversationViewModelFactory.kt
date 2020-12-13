package com.im.bin.accessibility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.im.bin.viewModel.ChatConversationViewModel
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ChatConversationViewModelFactory(val provider: Provider<ChatConversationViewModel>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provider.get() as T // Delegate call to provider
    }
}