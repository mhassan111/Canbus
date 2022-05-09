package com.screening.app.featureCallScreening.util

import java.lang.IllegalStateException

enum class CallScreen {

    PhoneNumber,
    AddEditPhoneNumber;

    companion object {
        fun fromRoute(route: String?): CallScreen =
            when (route?.substringBefore("/")) {
                PhoneNumber.name -> PhoneNumber
                AddEditPhoneNumber.name -> AddEditPhoneNumber
                null -> PhoneNumber
                else -> throw IllegalStateException("Route $route is not recognized")
            }
    }
}