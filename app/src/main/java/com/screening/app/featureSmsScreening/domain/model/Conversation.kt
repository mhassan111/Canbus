package com.screening.app.featureSmsScreening.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey
    val conversationId: String,
    val name: String,
    val phoneNumber: String,
    val participants: List<Participant>
)