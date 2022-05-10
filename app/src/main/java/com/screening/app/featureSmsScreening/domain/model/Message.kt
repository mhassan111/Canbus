package com.screening.app.featureSmsScreening.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey val id: String,
    val body: String,
    val type: String,
    val date: Date
)