package com.screening.app.featureCallScreening.data.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.screening.app.featureSmsScreening.domain.model.Message
import com.screening.app.featureSmsScreening.domain.model.Participant
import java.util.*

class Converters {

    private val gson = Gson()
    private val participantType = object : TypeToken<List<Participant>>() {}.type
    private val messageType = object : TypeToken<Message>() {}.type

    @TypeConverter
    fun jsonToParticipantList(value: String) =
        gson.fromJson<ArrayList<Participant>>(value, participantType)

    @TypeConverter
    fun participantListToJson(participants : List<Participant>) = gson.toJson(participants)

    @TypeConverter
    fun jsonToMessage(value : String) = gson.fromJson<Message>(value, messageType)

    @TypeConverter
    fun messageToJson(message: Message) = gson.toJson(message)

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}