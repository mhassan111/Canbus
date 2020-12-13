package com.im.bin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.im.bin.appUtils.DateConverter
import com.im.bin.db.dao.*
import com.im.bin.db.entities.*

@Database(
    entities = [WhatsApp::class, WhatsAppMedia::class, SnapChat::class, Instagram::class, Line::class, IMO::class, Viber::class, Hike::class, VoipCall::class],
    version = 7,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun whatsAppDao(): WhatsAppDao
    abstract fun whatsAppMediaDao(): WhatsAppMediaDao
    abstract fun snapChatDao(): SnapChatDao
    abstract fun instagramDao(): InstagramDao
    abstract fun viberDao(): ViberDao
    abstract fun lineDao(): LineDao
    abstract fun imoDao(): IMODao
    abstract fun hikeDao(): HikeDao
    abstract fun voipCallDao(): VoipCallDao

    companion object {

        private const val DB_NAME = "im_bin_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Get Instance of Room Database
         * @param context Context
         * @return instance
         */
        @JvmStatic
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .addMigrations(MIGRATION_5_6)
                            .addMigrations(MIGRATION_6_7)
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        private val MIGRATION_5_6: Migration = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'whats_app_table' ADD COLUMN date TEXT DEFAULT '2020-03-12 17:08:36'")
                database.execSQL("ALTER TABLE 'hike_table' ADD COLUMN date TEXT DEFAULT '2020-03-12 17:08:36'")
                database.execSQL("ALTER TABLE 'imo_table' ADD COLUMN date TEXT DEFAULT '2020-03-12 17:08:36'")
                database.execSQL("ALTER TABLE 'instagram_table' ADD COLUMN date TEXT DEFAULT '2020-03-12 17:08:36'")
                database.execSQL("ALTER TABLE 'line_table' ADD COLUMN date TEXT DEFAULT '2020-03-12 17:08:36'")
                database.execSQL("ALTER TABLE 'viber_table' ADD COLUMN date TEXT DEFAULT '2020-03-12 17:08:36'")
                database.execSQL("ALTER TABLE 'snap_chat_table' ADD COLUMN date TEXT DEFAULT '2020-03-12 17:08:36'")
            }
        }

        private val MIGRATION_6_7: Migration = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE 'voip_call_table' (voipId TEXT PRIMARY KEY NOT NULL, file TEXT NOT NULL, voipMessenger TEXT NOT NULL,voipName TEXT NOT NULL,voipNumber TEXT NOT NULL,voipDirection TEXT NOT NULL,voipType TEXT NOT NULL,voipDuration INTEGER NOT NULL, voipDateTime TEXT NOT NULL,date TEXT NOT NULL,status INTEGER NOT NULL)")
            }
        }
    }

    init {
        if (INSTANCE != null) {
            throw RuntimeException("Use getAppDatabase(context) method to get the single instance of this class.")
        }
    }
}