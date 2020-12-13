package com.im.bin.db.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\'J\b\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003H\'J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0003H\'J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\'J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\rH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00112\u0006\u0010\b\u001a\u00020\u0003H\'J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\fH\'J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\'J\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\'\u00a8\u0006\u001a"}, d2 = {"Lcom/im/bin/db/dao/LineDao;", "", "checkIfAlreadyExist", "", "messageId", "deleteChats", "", "deleteConversation", "conversationId", "deleteMessage", "message_id", "getAllLineMessages", "", "Lcom/im/bin/db/entities/Line;", "insert", "Line", "selectAllLineMessages", "Landroidx/lifecycle/LiveData;", "selectChats", "Lcom/im/bin/models/Chat;", "setMessageAsDeleted", "isDeleted", "update", "status", "", "updated_status", "app_release"})
public abstract interface LineDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.Line Line);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "Select message_id from line_table where message_id = :messageId")
    public abstract java.lang.String checkIfAlreadyExist(@org.jetbrains.annotations.NotNull()
    java.lang.String messageId);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "Select * from line_table order by date DESC")
    public abstract java.util.List<com.im.bin.db.entities.Line> getAllLineMessages();
    
    @androidx.room.Query(value = "delete from line_table")
    public abstract void deleteChats();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "Select * from line_table where conversation_id=:conversationId order by date DESC")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Line>> selectAllLineMessages(@org.jetbrains.annotations.NotNull()
    java.lang.String conversationId);
    
    @androidx.room.Query(value = "delete from line_table where conversation_id=:conversationId")
    public abstract void deleteConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String conversationId);
    
    @androidx.room.Query(value = "delete from line_table where message_id=:message_id")
    public abstract void deleteMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message_id);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "Select conversation_id,conversation_name,message,message_datetime from line_table GROUP BY conversation_id order by date DESC")
    public abstract java.util.List<com.im.bin.models.Chat> selectChats();
    
    @androidx.room.Query(value = "Update line_table set status=:updated_status where status=:status")
    public abstract void update(int status, int updated_status);
    
    @androidx.room.Query(value = "Update line_table set isDeleted=:isDeleted where message_id=:message_id")
    public abstract void setMessageAsDeleted(@org.jetbrains.annotations.NotNull()
    java.lang.String message_id, @org.jetbrains.annotations.NotNull()
    java.lang.String isDeleted);
}