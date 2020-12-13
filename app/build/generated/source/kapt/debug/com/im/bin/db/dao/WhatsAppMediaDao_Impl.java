package com.im.bin.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.im.bin.db.entities.WhatsAppMedia;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WhatsAppMediaDao_Impl implements WhatsAppMediaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WhatsAppMedia> __insertionAdapterOfWhatsAppMedia;

  private final SharedSQLiteStatement __preparedStmtOfUpdate;

  private final SharedSQLiteStatement __preparedStmtOfDeleteMedia;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public WhatsAppMediaDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWhatsAppMedia = new EntityInsertionAdapter<WhatsAppMedia>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `whats_app_media_table` (`media_id`,`media_name`,`media_path`,`media_date`,`media_type`,`media_time_stamp`,`media_direction`,`is_deleted`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WhatsAppMedia value) {
        if (value.getMediaId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getMediaId());
        }
        if (value.getMediaName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMediaName());
        }
        if (value.getMediaPath() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMediaPath());
        }
        if (value.getMediaDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMediaDate());
        }
        if (value.getMediaType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMediaType());
        }
        if (value.getMediaTimeStamp() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getMediaTimeStamp());
        }
        if (value.getMediaDirection() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMediaDirection());
        }
        if (value.isDeleted() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.isDeleted());
        }
      }
    };
    this.__preparedStmtOfUpdate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update whats_app_media_table set is_deleted=? where media_id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteMedia = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from whats_app_media_table where media_id =?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update whats_app_media_table set is_deleted=? where media_type=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final WhatsAppMedia whatsAppMedia) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWhatsAppMedia.insert(whatsAppMedia);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final List<WhatsAppMedia> whatsAppMediaList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWhatsAppMedia.insert(whatsAppMediaList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final String media_id, final String is_deleted) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdate.acquire();
    int _argIndex = 1;
    if (is_deleted == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, is_deleted);
    }
    _argIndex = 2;
    if (media_id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, media_id);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdate.release(_stmt);
    }
  }

  @Override
  public void deleteMedia(final String media_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMedia.acquire();
    int _argIndex = 1;
    if (media_id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, media_id);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteMedia.release(_stmt);
    }
  }

  @Override
  public void deleteAll(final String media_type, final String is_deleted) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    int _argIndex = 1;
    if (is_deleted == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, is_deleted);
    }
    _argIndex = 2;
    if (media_type == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, media_type);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public String checkIfAlreadyExists(final String media_id) {
    final String _sql = "Select media_id from whats_app_media_table where media_id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (media_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, media_id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<WhatsAppMedia>> selectAllWhatsAppMedia(final String media_type) {
    final String _sql = "Select * from whats_app_media_table where media_type=? AND is_deleted=0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (media_type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, media_type);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"whats_app_media_table"}, false, new Callable<List<WhatsAppMedia>>() {
      @Override
      public List<WhatsAppMedia> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMediaId = CursorUtil.getColumnIndexOrThrow(_cursor, "media_id");
          final int _cursorIndexOfMediaName = CursorUtil.getColumnIndexOrThrow(_cursor, "media_name");
          final int _cursorIndexOfMediaPath = CursorUtil.getColumnIndexOrThrow(_cursor, "media_path");
          final int _cursorIndexOfMediaDate = CursorUtil.getColumnIndexOrThrow(_cursor, "media_date");
          final int _cursorIndexOfMediaType = CursorUtil.getColumnIndexOrThrow(_cursor, "media_type");
          final int _cursorIndexOfMediaTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "media_time_stamp");
          final int _cursorIndexOfMediaDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "media_direction");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_deleted");
          final List<WhatsAppMedia> _result = new ArrayList<WhatsAppMedia>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final WhatsAppMedia _item;
            final String _tmpMediaId;
            _tmpMediaId = _cursor.getString(_cursorIndexOfMediaId);
            final String _tmpMediaName;
            _tmpMediaName = _cursor.getString(_cursorIndexOfMediaName);
            final String _tmpMediaPath;
            _tmpMediaPath = _cursor.getString(_cursorIndexOfMediaPath);
            final String _tmpMediaDate;
            _tmpMediaDate = _cursor.getString(_cursorIndexOfMediaDate);
            final String _tmpMediaType;
            _tmpMediaType = _cursor.getString(_cursorIndexOfMediaType);
            final Long _tmpMediaTimeStamp;
            if (_cursor.isNull(_cursorIndexOfMediaTimeStamp)) {
              _tmpMediaTimeStamp = null;
            } else {
              _tmpMediaTimeStamp = _cursor.getLong(_cursorIndexOfMediaTimeStamp);
            }
            final String _tmpMediaDirection;
            _tmpMediaDirection = _cursor.getString(_cursorIndexOfMediaDirection);
            final String _tmpIsDeleted;
            _tmpIsDeleted = _cursor.getString(_cursorIndexOfIsDeleted);
            _item = new WhatsAppMedia(_tmpMediaId,_tmpMediaName,_tmpMediaPath,_tmpMediaDate,_tmpMediaType,_tmpMediaTimeStamp,_tmpMediaDirection,_tmpIsDeleted);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
