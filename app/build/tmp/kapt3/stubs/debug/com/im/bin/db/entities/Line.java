package com.im.bin.db.entities;

import java.lang.System;

@androidx.room.Entity(tableName = "line_table")
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001:\u00016By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010 J\u009a\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u00100J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u00020\u0003H\u00d6\u0001J\t\u00105\u001a\u00020\u0005H\u00d6\u0001R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0014R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u001a\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014\u00a8\u00067"}, d2 = {"Lcom/im/bin/db/entities/Line;", "", "id", "", "messageId", "", "conversationId", "conversationName", "senderName", "message", "type", "messageDatetime", "timeStamp", "", "isDeleted", "date", "Ljava/util/Date;", "status", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/util/Date;I)V", "getConversationId", "()Ljava/lang/String;", "getConversationName", "getDate", "()Ljava/util/Date;", "getId", "()I", "getMessage", "getMessageDatetime", "getMessageId", "getSenderName", "getStatus", "getTimeStamp", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getType", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/util/Date;I)Lcom/im/bin/db/entities/Line;", "equals", "", "other", "hashCode", "toString", "LineUnrootedBuilder", "app_debug"})
public final class Line {
    @androidx.room.PrimaryKey(autoGenerate = true)
    @androidx.room.ColumnInfo(name = "id")
    private final int id = 0;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "message_id")
    private final java.lang.String messageId = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "conversation_id")
    private final java.lang.String conversationId = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "conversation_name")
    private final java.lang.String conversationName = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "sender_name")
    private final java.lang.String senderName = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "message")
    private final java.lang.String message = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "type")
    private final java.lang.String type = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "message_datetime")
    private final java.lang.String messageDatetime = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "timeStamp")
    private final java.lang.Long timeStamp = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "isDeleted")
    private final java.lang.String isDeleted = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "date", defaultValue = "2020-03-12 17:08:36")
    private final transient java.util.Date date = null;
    @androidx.room.ColumnInfo(name = "status")
    private final int status = 0;
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessageId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConversationId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConversationName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSenderName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessageDatetime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getTimeStamp() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String isDeleted() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date getDate() {
        return null;
    }
    
    public final int getStatus() {
        return 0;
    }
    
    public Line(int id, @org.jetbrains.annotations.Nullable()
    java.lang.String messageId, @org.jetbrains.annotations.Nullable()
    java.lang.String conversationId, @org.jetbrains.annotations.Nullable()
    java.lang.String conversationName, @org.jetbrains.annotations.Nullable()
    java.lang.String senderName, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.Nullable()
    java.lang.String messageDatetime, @org.jetbrains.annotations.Nullable()
    java.lang.Long timeStamp, @org.jetbrains.annotations.Nullable()
    java.lang.String isDeleted, @org.jetbrains.annotations.Nullable()
    java.util.Date date, int status) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date component11() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.db.entities.Line copy(int id, @org.jetbrains.annotations.Nullable()
    java.lang.String messageId, @org.jetbrains.annotations.Nullable()
    java.lang.String conversationId, @org.jetbrains.annotations.Nullable()
    java.lang.String conversationName, @org.jetbrains.annotations.Nullable()
    java.lang.String senderName, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.Nullable()
    java.lang.String messageDatetime, @org.jetbrains.annotations.Nullable()
    java.lang.Long timeStamp, @org.jetbrains.annotations.Nullable()
    java.lang.String isDeleted, @org.jetbrains.annotations.Nullable()
    java.util.Date date, int status) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0017\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0018\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001b\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001c\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001d\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001e\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\tJ\u0015\u0010 \u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/im/bin/db/entities/Line$LineUnrootedBuilder;", "", "()V", "conversationId", "", "conversationName", "date", "Ljava/util/Date;", "id", "", "isDeleted", "message", "messageDatetime", "messageId", "senderName", "status", "timeStamp", "", "Ljava/lang/Long;", "type", "create", "Lcom/im/bin/db/entities/Line;", "setConversationId", "setConversationName", "setDate", "setId", "setIsDeleted", "setMessage", "setMessageDatetime", "setMessageId", "setSenderName", "setStatus", "setTimeStamp", "(Ljava/lang/Long;)Lcom/im/bin/db/entities/Line$LineUnrootedBuilder;", "setType", "app_debug"})
    public static final class LineUnrootedBuilder {
        private int id = 0;
        private java.lang.String messageId;
        private java.lang.String conversationId;
        private java.lang.String conversationName;
        private java.lang.String senderName;
        private java.lang.String message;
        private java.lang.String type;
        private java.lang.String messageDatetime;
        private java.lang.Long timeStamp;
        private java.util.Date date;
        private java.lang.String isDeleted;
        private int status = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setId(int id) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setMessageId(@org.jetbrains.annotations.Nullable()
        java.lang.String messageId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setConversationId(@org.jetbrains.annotations.Nullable()
        java.lang.String conversationId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setConversationName(@org.jetbrains.annotations.Nullable()
        java.lang.String conversationName) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setSenderName(@org.jetbrains.annotations.Nullable()
        java.lang.String senderName) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setMessage(@org.jetbrains.annotations.Nullable()
        java.lang.String message) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setType(@org.jetbrains.annotations.Nullable()
        java.lang.String type) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setMessageDatetime(@org.jetbrains.annotations.Nullable()
        java.lang.String messageDatetime) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setTimeStamp(@org.jetbrains.annotations.Nullable()
        java.lang.Long timeStamp) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setDate(@org.jetbrains.annotations.Nullable()
        java.util.Date date) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setIsDeleted(@org.jetbrains.annotations.Nullable()
        java.lang.String isDeleted) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line.LineUnrootedBuilder setStatus(int status) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.db.entities.Line create() {
            return null;
        }
        
        public LineUnrootedBuilder() {
            super();
        }
    }
}