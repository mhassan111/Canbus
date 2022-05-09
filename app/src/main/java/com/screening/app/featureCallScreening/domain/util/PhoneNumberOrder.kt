package com.screening.app.featureCallScreening.domain.util

sealed class PhoneNumberOrder(val orderType: OrderType) {

    class Number(orderType: OrderType) : PhoneNumberOrder(orderType)
    class Date(orderType: OrderType) : PhoneNumberOrder(orderType)

    fun copy(orderType: OrderType): PhoneNumberOrder {
        return when (this) {
            is Number -> Number(orderType)
            is Date -> Date(orderType)
        }
    }
}