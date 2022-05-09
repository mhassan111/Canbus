package com.screening.app.featureCallScreening.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.screening.app.featureCallScreening.data.util.Converters
import com.screening.app.featureCallScreening.domain.model.PhoneNumber
import com.screening.app.featureSmsScreening.domain.model.Conversation
import com.screening.app.featureSmsScreening.domain.model.Message

@Database(
    entities = [PhoneNumber::class, Message::class, Conversation::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val phoneNumberDao: PhoneNumberDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}