package com.im.bin.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.im.bin.db.dao.HikeDao;
import com.im.bin.db.dao.HikeDao_Impl;
import com.im.bin.db.dao.IMODao;
import com.im.bin.db.dao.IMODao_Impl;
import com.im.bin.db.dao.InstagramDao;
import com.im.bin.db.dao.InstagramDao_Impl;
import com.im.bin.db.dao.LineDao;
import com.im.bin.db.dao.LineDao_Impl;
import com.im.bin.db.dao.SnapChatDao;
import com.im.bin.db.dao.SnapChatDao_Impl;
import com.im.bin.db.dao.ViberDao;
import com.im.bin.db.dao.ViberDao_Impl;
import com.im.bin.db.dao.VoipCallDao;
import com.im.bin.db.dao.VoipCallDao_Impl;
import com.im.bin.db.dao.WhatsAppDao;
import com.im.bin.db.dao.WhatsAppDao_Impl;
import com.im.bin.db.dao.WhatsAppMediaDao;
import com.im.bin.db.dao.WhatsAppMediaDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile WhatsAppDao _whatsAppDao;

  private volatile WhatsAppMediaDao _whatsAppMediaDao;

  private volatile SnapChatDao _snapChatDao;

  private volatile InstagramDao _instagramDao;

  private volatile ViberDao _viberDao;

  private volatile LineDao _lineDao;

  private volatile IMODao _iMODao;

  private volatile HikeDao _hikeDao;

  private volatile VoipCallDao _voipCallDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(7) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `whats_app_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message_id` TEXT, `conversation_id` TEXT, `conversation_name` TEXT, `sender_name` TEXT, `message` TEXT, `type` TEXT, `message_datetime` TEXT, `timeStamp` INTEGER, `date` TEXT DEFAULT '2020-03-12 17:08:36', `isDeleted` TEXT, `status` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `whats_app_media_table` (`media_id` TEXT NOT NULL, `media_name` TEXT, `media_path` TEXT, `media_date` TEXT, `media_type` TEXT, `media_time_stamp` INTEGER, `media_direction` TEXT, `is_deleted` TEXT, PRIMARY KEY(`media_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `snap_chat_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message_id` TEXT, `conversation_id` TEXT, `conversation_name` TEXT, `sender_name` TEXT, `message` TEXT, `type` TEXT, `message_datetime` TEXT, `timeStamp` INTEGER, `isDeleted` TEXT, `date` TEXT DEFAULT '2020-03-12 17:08:36', `status` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `instagram_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message_id` TEXT, `conversation_id` TEXT, `conversation_name` TEXT, `sender_name` TEXT, `message` TEXT, `type` TEXT, `message_datetime` TEXT, `timeStamp` INTEGER, `isDeleted` TEXT, `date` TEXT DEFAULT '2020-03-12 17:08:36', `status` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `line_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message_id` TEXT, `conversation_id` TEXT, `conversation_name` TEXT, `sender_name` TEXT, `message` TEXT, `type` TEXT, `message_datetime` TEXT, `timeStamp` INTEGER, `isDeleted` TEXT, `date` TEXT DEFAULT '2020-03-12 17:08:36', `status` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `imo_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message_id` TEXT, `conversation_id` TEXT, `conversation_name` TEXT, `sender_name` TEXT, `message` TEXT, `type` TEXT, `message_datetime` TEXT, `timeStamp` INTEGER, `isDeleted` TEXT, `date` TEXT DEFAULT '2020-03-12 17:08:36', `status` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `viber_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message_id` TEXT, `conversation_id` TEXT, `conversation_name` TEXT, `sender_name` TEXT, `message` TEXT, `type` TEXT, `message_datetime` TEXT, `timeStamp` INTEGER, `isDeleted` TEXT, `date` TEXT DEFAULT '2020-03-12 17:08:36', `status` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `hike_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message_id` TEXT, `conversation_id` TEXT, `conversation_name` TEXT, `sender_name` TEXT, `message` TEXT, `type` TEXT, `message_datetime` TEXT, `timeStamp` INTEGER, `isDeleted` TEXT, `date` TEXT DEFAULT '2020-03-12 17:08:36', `status` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `voip_call_table` (`voipId` TEXT NOT NULL, `file` TEXT NOT NULL, `voipMessenger` TEXT NOT NULL, `voipName` TEXT NOT NULL, `voipNumber` TEXT NOT NULL, `voipDirection` TEXT NOT NULL, `voipType` TEXT NOT NULL, `voipDuration` INTEGER NOT NULL, `voipDateTime` TEXT NOT NULL, `date` TEXT NOT NULL, `status` INTEGER NOT NULL, PRIMARY KEY(`voipId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9db4fd268db0a0ff61fcbbf3e67394d3')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `whats_app_table`");
        _db.execSQL("DROP TABLE IF EXISTS `whats_app_media_table`");
        _db.execSQL("DROP TABLE IF EXISTS `snap_chat_table`");
        _db.execSQL("DROP TABLE IF EXISTS `instagram_table`");
        _db.execSQL("DROP TABLE IF EXISTS `line_table`");
        _db.execSQL("DROP TABLE IF EXISTS `imo_table`");
        _db.execSQL("DROP TABLE IF EXISTS `viber_table`");
        _db.execSQL("DROP TABLE IF EXISTS `hike_table`");
        _db.execSQL("DROP TABLE IF EXISTS `voip_call_table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsWhatsAppTable = new HashMap<String, TableInfo.Column>(12);
        _columnsWhatsAppTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("message_id", new TableInfo.Column("message_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("conversation_id", new TableInfo.Column("conversation_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("conversation_name", new TableInfo.Column("conversation_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("sender_name", new TableInfo.Column("sender_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("message_datetime", new TableInfo.Column("message_datetime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("date", new TableInfo.Column("date", "TEXT", false, 0, "'2020-03-12 17:08:36'", TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("isDeleted", new TableInfo.Column("isDeleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppTable.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWhatsAppTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWhatsAppTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWhatsAppTable = new TableInfo("whats_app_table", _columnsWhatsAppTable, _foreignKeysWhatsAppTable, _indicesWhatsAppTable);
        final TableInfo _existingWhatsAppTable = TableInfo.read(_db, "whats_app_table");
        if (! _infoWhatsAppTable.equals(_existingWhatsAppTable)) {
          return new RoomOpenHelper.ValidationResult(false, "whats_app_table(com.im.bin.db.entities.WhatsApp).\n"
                  + " Expected:\n" + _infoWhatsAppTable + "\n"
                  + " Found:\n" + _existingWhatsAppTable);
        }
        final HashMap<String, TableInfo.Column> _columnsWhatsAppMediaTable = new HashMap<String, TableInfo.Column>(8);
        _columnsWhatsAppMediaTable.put("media_id", new TableInfo.Column("media_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppMediaTable.put("media_name", new TableInfo.Column("media_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppMediaTable.put("media_path", new TableInfo.Column("media_path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppMediaTable.put("media_date", new TableInfo.Column("media_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppMediaTable.put("media_type", new TableInfo.Column("media_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppMediaTable.put("media_time_stamp", new TableInfo.Column("media_time_stamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppMediaTable.put("media_direction", new TableInfo.Column("media_direction", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhatsAppMediaTable.put("is_deleted", new TableInfo.Column("is_deleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWhatsAppMediaTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWhatsAppMediaTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWhatsAppMediaTable = new TableInfo("whats_app_media_table", _columnsWhatsAppMediaTable, _foreignKeysWhatsAppMediaTable, _indicesWhatsAppMediaTable);
        final TableInfo _existingWhatsAppMediaTable = TableInfo.read(_db, "whats_app_media_table");
        if (! _infoWhatsAppMediaTable.equals(_existingWhatsAppMediaTable)) {
          return new RoomOpenHelper.ValidationResult(false, "whats_app_media_table(com.im.bin.db.entities.WhatsAppMedia).\n"
                  + " Expected:\n" + _infoWhatsAppMediaTable + "\n"
                  + " Found:\n" + _existingWhatsAppMediaTable);
        }
        final HashMap<String, TableInfo.Column> _columnsSnapChatTable = new HashMap<String, TableInfo.Column>(12);
        _columnsSnapChatTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("message_id", new TableInfo.Column("message_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("conversation_id", new TableInfo.Column("conversation_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("conversation_name", new TableInfo.Column("conversation_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("sender_name", new TableInfo.Column("sender_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("message_datetime", new TableInfo.Column("message_datetime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("isDeleted", new TableInfo.Column("isDeleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("date", new TableInfo.Column("date", "TEXT", false, 0, "'2020-03-12 17:08:36'", TableInfo.CREATED_FROM_ENTITY));
        _columnsSnapChatTable.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSnapChatTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSnapChatTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSnapChatTable = new TableInfo("snap_chat_table", _columnsSnapChatTable, _foreignKeysSnapChatTable, _indicesSnapChatTable);
        final TableInfo _existingSnapChatTable = TableInfo.read(_db, "snap_chat_table");
        if (! _infoSnapChatTable.equals(_existingSnapChatTable)) {
          return new RoomOpenHelper.ValidationResult(false, "snap_chat_table(com.im.bin.db.entities.SnapChat).\n"
                  + " Expected:\n" + _infoSnapChatTable + "\n"
                  + " Found:\n" + _existingSnapChatTable);
        }
        final HashMap<String, TableInfo.Column> _columnsInstagramTable = new HashMap<String, TableInfo.Column>(12);
        _columnsInstagramTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("message_id", new TableInfo.Column("message_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("conversation_id", new TableInfo.Column("conversation_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("conversation_name", new TableInfo.Column("conversation_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("sender_name", new TableInfo.Column("sender_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("message_datetime", new TableInfo.Column("message_datetime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("isDeleted", new TableInfo.Column("isDeleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("date", new TableInfo.Column("date", "TEXT", false, 0, "'2020-03-12 17:08:36'", TableInfo.CREATED_FROM_ENTITY));
        _columnsInstagramTable.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInstagramTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInstagramTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInstagramTable = new TableInfo("instagram_table", _columnsInstagramTable, _foreignKeysInstagramTable, _indicesInstagramTable);
        final TableInfo _existingInstagramTable = TableInfo.read(_db, "instagram_table");
        if (! _infoInstagramTable.equals(_existingInstagramTable)) {
          return new RoomOpenHelper.ValidationResult(false, "instagram_table(com.im.bin.db.entities.Instagram).\n"
                  + " Expected:\n" + _infoInstagramTable + "\n"
                  + " Found:\n" + _existingInstagramTable);
        }
        final HashMap<String, TableInfo.Column> _columnsLineTable = new HashMap<String, TableInfo.Column>(12);
        _columnsLineTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("message_id", new TableInfo.Column("message_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("conversation_id", new TableInfo.Column("conversation_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("conversation_name", new TableInfo.Column("conversation_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("sender_name", new TableInfo.Column("sender_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("message_datetime", new TableInfo.Column("message_datetime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("isDeleted", new TableInfo.Column("isDeleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("date", new TableInfo.Column("date", "TEXT", false, 0, "'2020-03-12 17:08:36'", TableInfo.CREATED_FROM_ENTITY));
        _columnsLineTable.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLineTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLineTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLineTable = new TableInfo("line_table", _columnsLineTable, _foreignKeysLineTable, _indicesLineTable);
        final TableInfo _existingLineTable = TableInfo.read(_db, "line_table");
        if (! _infoLineTable.equals(_existingLineTable)) {
          return new RoomOpenHelper.ValidationResult(false, "line_table(com.im.bin.db.entities.Line).\n"
                  + " Expected:\n" + _infoLineTable + "\n"
                  + " Found:\n" + _existingLineTable);
        }
        final HashMap<String, TableInfo.Column> _columnsImoTable = new HashMap<String, TableInfo.Column>(12);
        _columnsImoTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("message_id", new TableInfo.Column("message_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("conversation_id", new TableInfo.Column("conversation_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("conversation_name", new TableInfo.Column("conversation_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("sender_name", new TableInfo.Column("sender_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("message_datetime", new TableInfo.Column("message_datetime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("isDeleted", new TableInfo.Column("isDeleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("date", new TableInfo.Column("date", "TEXT", false, 0, "'2020-03-12 17:08:36'", TableInfo.CREATED_FROM_ENTITY));
        _columnsImoTable.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysImoTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesImoTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoImoTable = new TableInfo("imo_table", _columnsImoTable, _foreignKeysImoTable, _indicesImoTable);
        final TableInfo _existingImoTable = TableInfo.read(_db, "imo_table");
        if (! _infoImoTable.equals(_existingImoTable)) {
          return new RoomOpenHelper.ValidationResult(false, "imo_table(com.im.bin.db.entities.IMO).\n"
                  + " Expected:\n" + _infoImoTable + "\n"
                  + " Found:\n" + _existingImoTable);
        }
        final HashMap<String, TableInfo.Column> _columnsViberTable = new HashMap<String, TableInfo.Column>(12);
        _columnsViberTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("message_id", new TableInfo.Column("message_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("conversation_id", new TableInfo.Column("conversation_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("conversation_name", new TableInfo.Column("conversation_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("sender_name", new TableInfo.Column("sender_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("message_datetime", new TableInfo.Column("message_datetime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("isDeleted", new TableInfo.Column("isDeleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("date", new TableInfo.Column("date", "TEXT", false, 0, "'2020-03-12 17:08:36'", TableInfo.CREATED_FROM_ENTITY));
        _columnsViberTable.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysViberTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesViberTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoViberTable = new TableInfo("viber_table", _columnsViberTable, _foreignKeysViberTable, _indicesViberTable);
        final TableInfo _existingViberTable = TableInfo.read(_db, "viber_table");
        if (! _infoViberTable.equals(_existingViberTable)) {
          return new RoomOpenHelper.ValidationResult(false, "viber_table(com.im.bin.db.entities.Viber).\n"
                  + " Expected:\n" + _infoViberTable + "\n"
                  + " Found:\n" + _existingViberTable);
        }
        final HashMap<String, TableInfo.Column> _columnsHikeTable = new HashMap<String, TableInfo.Column>(12);
        _columnsHikeTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("message_id", new TableInfo.Column("message_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("conversation_id", new TableInfo.Column("conversation_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("conversation_name", new TableInfo.Column("conversation_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("sender_name", new TableInfo.Column("sender_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("message_datetime", new TableInfo.Column("message_datetime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("isDeleted", new TableInfo.Column("isDeleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("date", new TableInfo.Column("date", "TEXT", false, 0, "'2020-03-12 17:08:36'", TableInfo.CREATED_FROM_ENTITY));
        _columnsHikeTable.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHikeTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHikeTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHikeTable = new TableInfo("hike_table", _columnsHikeTable, _foreignKeysHikeTable, _indicesHikeTable);
        final TableInfo _existingHikeTable = TableInfo.read(_db, "hike_table");
        if (! _infoHikeTable.equals(_existingHikeTable)) {
          return new RoomOpenHelper.ValidationResult(false, "hike_table(com.im.bin.db.entities.Hike).\n"
                  + " Expected:\n" + _infoHikeTable + "\n"
                  + " Found:\n" + _existingHikeTable);
        }
        final HashMap<String, TableInfo.Column> _columnsVoipCallTable = new HashMap<String, TableInfo.Column>(11);
        _columnsVoipCallTable.put("voipId", new TableInfo.Column("voipId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("file", new TableInfo.Column("file", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("voipMessenger", new TableInfo.Column("voipMessenger", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("voipName", new TableInfo.Column("voipName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("voipNumber", new TableInfo.Column("voipNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("voipDirection", new TableInfo.Column("voipDirection", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("voipType", new TableInfo.Column("voipType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("voipDuration", new TableInfo.Column("voipDuration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("voipDateTime", new TableInfo.Column("voipDateTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVoipCallTable.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVoipCallTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVoipCallTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVoipCallTable = new TableInfo("voip_call_table", _columnsVoipCallTable, _foreignKeysVoipCallTable, _indicesVoipCallTable);
        final TableInfo _existingVoipCallTable = TableInfo.read(_db, "voip_call_table");
        if (! _infoVoipCallTable.equals(_existingVoipCallTable)) {
          return new RoomOpenHelper.ValidationResult(false, "voip_call_table(com.im.bin.db.entities.VoipCall).\n"
                  + " Expected:\n" + _infoVoipCallTable + "\n"
                  + " Found:\n" + _existingVoipCallTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9db4fd268db0a0ff61fcbbf3e67394d3", "fd45898f296b4fe81d77dbed77c0e390");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "whats_app_table","whats_app_media_table","snap_chat_table","instagram_table","line_table","imo_table","viber_table","hike_table","voip_call_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `whats_app_table`");
      _db.execSQL("DELETE FROM `whats_app_media_table`");
      _db.execSQL("DELETE FROM `snap_chat_table`");
      _db.execSQL("DELETE FROM `instagram_table`");
      _db.execSQL("DELETE FROM `line_table`");
      _db.execSQL("DELETE FROM `imo_table`");
      _db.execSQL("DELETE FROM `viber_table`");
      _db.execSQL("DELETE FROM `hike_table`");
      _db.execSQL("DELETE FROM `voip_call_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public WhatsAppDao whatsAppDao() {
    if (_whatsAppDao != null) {
      return _whatsAppDao;
    } else {
      synchronized(this) {
        if(_whatsAppDao == null) {
          _whatsAppDao = new WhatsAppDao_Impl(this);
        }
        return _whatsAppDao;
      }
    }
  }

  @Override
  public WhatsAppMediaDao whatsAppMediaDao() {
    if (_whatsAppMediaDao != null) {
      return _whatsAppMediaDao;
    } else {
      synchronized(this) {
        if(_whatsAppMediaDao == null) {
          _whatsAppMediaDao = new WhatsAppMediaDao_Impl(this);
        }
        return _whatsAppMediaDao;
      }
    }
  }

  @Override
  public SnapChatDao snapChatDao() {
    if (_snapChatDao != null) {
      return _snapChatDao;
    } else {
      synchronized(this) {
        if(_snapChatDao == null) {
          _snapChatDao = new SnapChatDao_Impl(this);
        }
        return _snapChatDao;
      }
    }
  }

  @Override
  public InstagramDao instagramDao() {
    if (_instagramDao != null) {
      return _instagramDao;
    } else {
      synchronized(this) {
        if(_instagramDao == null) {
          _instagramDao = new InstagramDao_Impl(this);
        }
        return _instagramDao;
      }
    }
  }

  @Override
  public ViberDao viberDao() {
    if (_viberDao != null) {
      return _viberDao;
    } else {
      synchronized(this) {
        if(_viberDao == null) {
          _viberDao = new ViberDao_Impl(this);
        }
        return _viberDao;
      }
    }
  }

  @Override
  public LineDao lineDao() {
    if (_lineDao != null) {
      return _lineDao;
    } else {
      synchronized(this) {
        if(_lineDao == null) {
          _lineDao = new LineDao_Impl(this);
        }
        return _lineDao;
      }
    }
  }

  @Override
  public IMODao imoDao() {
    if (_iMODao != null) {
      return _iMODao;
    } else {
      synchronized(this) {
        if(_iMODao == null) {
          _iMODao = new IMODao_Impl(this);
        }
        return _iMODao;
      }
    }
  }

  @Override
  public HikeDao hikeDao() {
    if (_hikeDao != null) {
      return _hikeDao;
    } else {
      synchronized(this) {
        if(_hikeDao == null) {
          _hikeDao = new HikeDao_Impl(this);
        }
        return _hikeDao;
      }
    }
  }

  @Override
  public VoipCallDao voipCallDao() {
    if (_voipCallDao != null) {
      return _voipCallDao;
    } else {
      synchronized(this) {
        if(_voipCallDao == null) {
          _voipCallDao = new VoipCallDao_Impl(this);
        }
        return _voipCallDao;
      }
    }
  }
}
