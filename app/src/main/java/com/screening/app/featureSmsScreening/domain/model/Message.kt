package com.screening.app.featureSmsScreening.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey val messageId: String,
    val messageBody: String,
    val messageType: String,
    val date: Date
)