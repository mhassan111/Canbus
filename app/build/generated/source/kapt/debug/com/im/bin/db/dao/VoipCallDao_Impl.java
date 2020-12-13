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
import com.im.bin.appUtils.DateConverter;
import com.im.bin.db.entities.VoipCall;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class VoipCallDao_Impl implements VoipCallDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VoipCall> __insertionAdapterOfVoipCall;

  private final DateConverter __dateConverter = new DateConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteVoipCalls;

  private final SharedSQLiteStatement __preparedStmtOfDeleteVoipCall;

  private final SharedSQLiteStatement __preparedStmtOfUpdate;

  public VoipCallDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVoipCall = new EntityInsertionAdapter<VoipCall>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `voip_call_table` (`voipId`,`file`,`voipMessenger`,`voipName`,`voipNumber`,`voipDirection`,`voipType`,`voipDuration`,`voipDateTime`,`date`,`status`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, VoipCall value) {
        if (value.getVoipId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getVoipId());
        }
        if (value.getFile() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFile());
        }
        if (value.getVoipMessenger() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getVoipMessenger());
        }
        if (value.getVoipName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getVoipName());
        }
        if (value.getVoipNumber() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getVoipNumber());
        }
        if (value.getVoipDirection() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getVoipDirection());
        }
        if (value.getVoipType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getVoipType());
        }
        stmt.bindLong(8, value.getVoipDuration());
        if (value.getVoipDateTime() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getVoipDateTime());
        }
        final String _tmp;
        _tmp = __dateConverter.dateToTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, _tmp);
        }
        stmt.bindLong(11, value.getStatus());
      }
    };
    this.__preparedStmtOfDeleteVoipCalls = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from voip_call_table where voipMessenger=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteVoipCall = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from voip_call_table where voipId=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update voip_call_table set status=? where status=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final VoipCall snapChat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfVoipCall.insert(snapChat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteVoipCalls(final String voipMessenger) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteVoipCalls.acquire();
    int _argIndex = 1;
    if (voipMessenger == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, voipMessenger);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteVoipCalls.release(_stmt);
    }
  }

  @Override
  public void deleteVoipCall(final String voipId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteVoipCall.acquire();
    int _argIndex = 1;
    if (voipId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, voipId);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteVoipCall.release(_stmt);
    }
  }

  @Override
  public void update(final int status, final int updated_status) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdate.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, updated_status);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, status);
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
  public String checkIfAlreadyExist(final String voipId) {
    final String _sql = "Select voipId from voip_call_table where voipId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (voipId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, voipId);
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
  public List<VoipCall> getAllVoipCalls(final String voipMessenger) {
    final String _sql = "Select * from voip_call_table where voipMessenger=? order by date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (voipMessenger == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, voipMessenger);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfVoipId = CursorUtil.getColumnIndexOrThrow(_cursor, "voipId");
      final int _cursorIndexOfFile = CursorUtil.getColumnIndexOrThrow(_cursor, "file");
      final int _cursorIndexOfVoipMessenger = CursorUtil.getColumnIndexOrThrow(_cursor, "voipMessenger");
      final int _cursorIndexOfVoipName = CursorUtil.getColumnIndexOrThrow(_cursor, "voipName");
      final int _cursorIndexOfVoipNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "voipNumber");
      final int _cursorIndexOfVoipDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "voipDirection");
      final int _cursorIndexOfVoipType = CursorUtil.getColumnIndexOrThrow(_cursor, "voipType");
      final int _cursorIndexOfVoipDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "voipDuration");
      final int _cursorIndexOfVoipDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "voipDateTime");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final List<VoipCall> _result = new ArrayList<VoipCall>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final VoipCall _item;
        final String _tmpVoipId;
        _tmpVoipId = _cursor.getString(_cursorIndexOfVoipId);
        final String _tmpFile;
        _tmpFile = _cursor.getString(_cursorIndexOfFile);
        final String _tmpVoipMessenger;
        _tmpVoipMessenger = _cursor.getString(_cursorIndexOfVoipMessenger);
        final String _tmpVoipName;
        _tmpVoipName = _cursor.getString(_cursorIndexOfVoipName);
        final String _tmpVoipNumber;
        _tmpVoipNumber = _cursor.getString(_cursorIndexOfVoipNumber);
        final String _tmpVoipDirection;
        _tmpVoipDirection = _cursor.getString(_cursorIndexOfVoipDirection);
        final String _tmpVoipType;
        _tmpVoipType = _cursor.getString(_cursorIndexOfVoipType);
        final long _tmpVoipDuration;
        _tmpVoipDuration = _cursor.getLong(_cursorIndexOfVoipDuration);
        final String _tmpVoipDateTime;
        _tmpVoipDateTime = _cursor.getString(_cursorIndexOfVoipDateTime);
        final Date _tmpDate;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfDate);
        _tmpDate = __dateConverter.fromTimestamp(_tmp);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        _item = new VoipCall(_tmpVoipId,_tmpFile,_tmpVoipMessenger,_tmpVoipName,_tmpVoipNumber,_tmpVoipDirection,_tmpVoipType,_tmpVoipDuration,_tmpVoipDateTime,_tmpDate,_tmpStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<VoipCall>> selectAllVoipCalls(final String voipMessenger) {
    final String _sql = "Select * from voip_call_table where voipMessenger=? order by date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (voipMessenger == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, voipMessenger);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"voip_call_table"}, false, new Callable<List<VoipCall>>() {
      @Override
      public List<VoipCall> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVoipId = CursorUtil.getColumnIndexOrThrow(_cursor, "voipId");
          final int _cursorIndexOfFile = CursorUtil.getColumnIndexOrThrow(_cursor, "file");
          final int _cursorIndexOfVoipMessenger = CursorUtil.getColumnIndexOrThrow(_cursor, "voipMessenger");
          final int _cursorIndexOfVoipName = CursorUtil.getColumnIndexOrThrow(_cursor, "voipName");
          final int _cursorIndexOfVoipNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "voipNumber");
          final int _cursorIndexOfVoipDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "voipDirection");
          final int _cursorIndexOfVoipType = CursorUtil.getColumnIndexOrThrow(_cursor, "voipType");
          final int _cursorIndexOfVoipDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "voipDuration");
          final int _cursorIndexOfVoipDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "voipDateTime");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<VoipCall> _result = new ArrayList<VoipCall>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final VoipCall _item;
            final String _tmpVoipId;
            _tmpVoipId = _cursor.getString(_cursorIndexOfVoipId);
            final String _tmpFile;
            _tmpFile = _cursor.getString(_cursorIndexOfFile);
            final String _tmpVoipMessenger;
            _tmpVoipMessenger = _cursor.getString(_cursorIndexOfVoipMessenger);
            final String _tmpVoipName;
            _tmpVoipName = _cursor.getString(_cursorIndexOfVoipName);
            final String _tmpVoipNumber;
            _tmpVoipNumber = _cursor.getString(_cursorIndexOfVoipNumber);
            final String _tmpVoipDirection;
            _tmpVoipDirection = _cursor.getString(_cursorIndexOfVoipDirection);
            final String _tmpVoipType;
            _tmpVoipType = _cursor.getString(_cursorIndexOfVoipType);
            final long _tmpVoipDuration;
            _tmpVoipDuration = _cursor.getLong(_cursorIndexOfVoipDuration);
            final String _tmpVoipDateTime;
            _tmpVoipDateTime = _cursor.getString(_cursorIndexOfVoipDateTime);
            final Date _tmpDate;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfDate);
            _tmpDate = __dateConverter.fromTimestamp(_tmp);
            final int _tmpStatus;
            _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
            _item = new VoipCall(_tmpVoipId,_tmpFile,_tmpVoipMessenger,_tmpVoipName,_tmpVoipNumber,_tmpVoipDirection,_tmpVoipType,_tmpVoipDuration,_tmpVoipDateTime,_tmpDate,_tmpStatus);
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
