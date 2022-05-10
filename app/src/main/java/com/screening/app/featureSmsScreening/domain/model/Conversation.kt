package com.screening.app.featureSmsScreening.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey
    val id: String,
    val name: String,
    val phoneNumber: String,
    val message: Message,
    val participants: List<Participant>
)