package com.screening.app.featureSmsScreening.domain.useCase

data class ContactUseCases(
    val getContactUseCase: GetContactUseCase,
    val insertContactUseCase: InsertContactUseCase,
    val insertContactsUseCase: InsertContactsUseCase,
    val deleteContactUseCase: DeleteContactUseCase,
    val selectContactUseCase: SelectContactUseCase
)
