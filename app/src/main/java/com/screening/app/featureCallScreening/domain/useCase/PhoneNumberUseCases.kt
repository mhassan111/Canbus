package com.screening.app.featureCallScreening.domain.useCase

data class PhoneNumberUseCases(
    val getPhoneNumbersUseCase: GetPhoneNumbersUseCase,
    val getPhoneNumberByPhoneNumber: GetPhoneNumberByPhoneNumber,
    val insertPhoneNumberUseCase: InsertPhoneNumberUseCase,
    val deletePhoneNumberUseCase: DeletePhoneNumberUseCase
)
