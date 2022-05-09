package com.screening.app.featureSmsScreening.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "participants")
data class Participant(
    @PrimaryKey
    val id: String,
    val name: String,
    val phoneNumber: String
)