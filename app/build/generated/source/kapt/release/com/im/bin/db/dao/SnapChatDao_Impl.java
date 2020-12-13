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
import com.im.bin.db.entities.SnapChat;
import com.im.bin.models.Chat;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SnapChatDao_Impl implements SnapChatDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SnapChat> __insertionAdapterOfSnapChat;

  private final DateConverter __dateConverter = new DateConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteChats;

  private final SharedSQLiteStatement __preparedStmtOfDeleteConversation;

  private final SharedSQLiteStatement __preparedStmtOfDeleteMessage;

  private final SharedSQLiteStatement __preparedStmtOfUpdate;

  private final SharedSQLiteStatement __preparedStmtOfSetMessageAsDeleted;

  public SnapChatDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSnapChat = new EntityInsertionAdapter<SnapChat>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `snap_chat_table` (`id`,`message_id`,`conversation_id`,`conversation_name`,`sender_name`,`message`,`type`,`message_datetime`,`timeStamp`,`isDeleted`,`date`,`status`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SnapChat value) {
        stmt.bindLong(1, value.getId());
        if (value.getMessageId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMessageId());
        }
        if (value.getConversationId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getConversationId());
        }
        if (value.getConversationName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getConversationName());
        }
        if (value.getSenderName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSenderName());
        }
        if (value.getMessage() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMessage());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
        if (value.getMessageDatetime() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getMessageDatetime());
        }
        if (value.getTimeStamp() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getTimeStamp());
        }
        if (value.isDeleted() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.isDeleted());
        }
        final String _tmp;
        _tmp = __dateConverter.dateToTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, _tmp);
        }
        stmt.bindLong(12, value.getStatus());
      }
    };
    this.__preparedStmtOfDeleteChats = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from snap_chat_table";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteConversation = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from snap_chat_table where conversation_id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteMessage = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from snap_chat_table where message_id=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update snap_chat_table set status=? where status=?";
        return _query;
      }
    };
    this.__preparedStmtOfSetMessageAsDeleted = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update snap_chat_table set isDeleted=? where message_id=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final SnapChat snapChat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSnapChat.insert(snapChat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteChats() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteChats.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteChats.release(_stmt);
    }
  }

  @Override
  public void deleteConversation(final String conversationId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteConversation.acquire();
    int _argIndex = 1;
    if (conversationId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, conversationId);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteConversation.release(_stmt);
    }
  }

  @Override
  public void deleteMessage(final String message_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMessage.acquire();
    int _argIndex = 1;
    if (message_id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, message_id);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteMessage.release(_stmt);
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
  public void setMessageAsDeleted(final String message_id, final String isDeleted) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetMessageAsDeleted.acquire();
    int _argIndex = 1;
    if (isDeleted == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, isDeleted);
    }
    _argIndex = 2;
    if (message_id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, message_id);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetMessageAsDeleted.release(_stmt);
    }
  }

  @Override
  public String checkIfAlreadyExist(final String messageId) {
    final String _sql = "Select message_id from snap_chat_table where message_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (messageId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, messageId);
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
  public List<SnapChat> getAllSnapChatMessages() {
    final String _sql = "Select * from snap_chat_table order by date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
      final int _cursorIndexOfConversationId = CursorUtil.getColumnIndexOrThrow(_cursor, "conversation_id");
      final int _cursorIndexOfConversationName = CursorUtil.getColumnIndexOrThrow(_cursor, "conversation_name");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfMessageDatetime = CursorUtil.getColumnIndexOrThrow(_cursor, "message_datetime");
      final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
      final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final List<SnapChat> _result = new ArrayList<SnapChat>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SnapChat _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpMessageId;
        _tmpMessageId = _cursor.getString(_cursorIndexOfMessageId);
        final String _tmpConversationId;
        _tmpConversationId = _cursor.getString(_cursorIndexOfConversationId);
        final String _tmpConversationName;
        _tmpConversationName = _cursor.getString(_cursorIndexOfConversationName);
        final String _tmpSenderName;
        _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        final String _tmpMessage;
        _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        final String _tmpMessageDatetime;
        _tmpMessageDatetime = _cursor.getString(_cursorIndexOfMessageDatetime);
        final Long _tmpTimeStamp;
        if (_cursor.isNull(_cursorIndexOfTimeStamp)) {
          _tmpTimeStamp = null;
        } else {
          _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
        }
        final String _tmpIsDeleted;
        _tmpIsDeleted = _cursor.getString(_cursorIndexOfIsDeleted);
        final Date _tmpDate;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfDate);
        _tmpDate = __dateConverter.fromTimestamp(_tmp);
        final int _tmpStatus;
        _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
        _item = new SnapChat(_tmpId,_tmpMessageId,_tmpConversationId,_tmpConversationName,_tmpSenderName,_tmpMessage,_tmpType,_tmpMessageDatetime,_tmpTimeStamp,_tmpIsDeleted,_tmpDate,_tmpStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<SnapChat>> selectAllSnapChatMessages(final String conversationId) {
    final String _sql = "Select * from snap_chat_table where conversation_id=? order by date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (conversationId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, conversationId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"snap_chat_table"}, false, new Callable<List<SnapChat>>() {
      @Override
      public List<SnapChat> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
          final int _cursorIndexOfConversationId = CursorUtil.getColumnIndexOrThrow(_cursor, "conversation_id");
          final int _cursorIndexOfConversationName = CursorUtil.getColumnIndexOrThrow(_cursor, "conversation_name");
          final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "sender_name");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfMessageDatetime = CursorUtil.getColumnIndexOrThrow(_cursor, "message_datetime");
          final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
          final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<SnapChat> _result = new ArrayList<SnapChat>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SnapChat _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpMessageId;
            _tmpMessageId = _cursor.getString(_cursorIndexOfMessageId);
            final String _tmpConversationId;
            _tmpConversationId = _cursor.getString(_cursorIndexOfConversationId);
            final String _tmpConversationName;
            _tmpConversationName = _cursor.getString(_cursorIndexOfConversationName);
            final String _tmpSenderName;
            _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpMessageDatetime;
            _tmpMessageDatetime = _cursor.getString(_cursorIndexOfMessageDatetime);
            final Long _tmpTimeStamp;
            if (_cursor.isNull(_cursorIndexOfTimeStamp)) {
              _tmpTimeStamp = null;
            } else {
              _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
            }
            final String _tmpIsDeleted;
            _tmpIsDeleted = _cursor.getString(_cursorIndexOfIsDeleted);
            final Date _tmpDate;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfDate);
            _tmpDate = __dateConverter.fromTimestamp(_tmp);
            final int _tmpStatus;
            _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
            _item = new SnapChat(_tmpId,_tmpMessageId,_tmpConversationId,_tmpConversationName,_tmpSenderName,_tmpMessage,_tmpType,_tmpMessageDatetime,_tmpTimeStamp,_tmpIsDeleted,_tmpDate,_tmpStatus);
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

  @Override
  public List<Chat> selectChats() {
    final String _sql = "Select conversation_id,conversation_name,message,message_datetime from snap_chat_table GROUP BY conversation_id order by date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfConversationId = CursorUtil.getColumnIndexOrThrow(_cursor, "conversation_id");
      final int _cursorIndexOfConversationName = CursorUtil.getColumnIndexOrThrow(_cursor, "conversation_name");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfMessageDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "message_datetime");
      final List<Chat> _result = new ArrayList<Chat>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Chat _item;
        final String _tmpConversationId;
        _tmpConversationId = _cursor.getString(_cursorIndexOfConversationId);
        final String _tmpConversationName;
        _tmpConversationName = _cursor.getString(_cursorIndexOfConversationName);
        final String _tmpLastMessage;
        _tmpLastMessage = _cursor.getString(_cursorIndexOfLastMessage);
        final String _tmpMessageDateTime;
        _tmpMessageDateTime = _cursor.getString(_cursorIndexOfMessageDateTime);
        _item = new Chat(_tmpConversationId,_tmpConversationName,_tmpLastMessage,_tmpMessageDateTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
