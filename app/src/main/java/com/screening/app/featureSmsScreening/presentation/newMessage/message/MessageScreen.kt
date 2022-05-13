package com.screening.app.featureSmsScreening.presentation.newMessage.message

enum class MessageScreen {
    SelectNewContact,
    NewMessage;

    companion object {
        fun fromRoute(route: String?): MessageScreen {
            return when (route?.substringBefore("/")) {
                SelectNewContact.name -> SelectNewContact
                NewMessage.name -> NewMessage
                null -> SelectNewContact
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }
}