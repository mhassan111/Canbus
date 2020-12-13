package com.im.bin.db.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\'J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\'J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003H\'J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\b\u001a\u00020\u0003H\'J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000bH\'J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u000f2\u0006\u0010\b\u001a\u00020\u0003H\'J\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\'\u00a8\u0006\u0014"}, d2 = {"Lcom/im/bin/db/dao/VoipCallDao;", "", "checkIfAlreadyExist", "", "voipId", "deleteVoipCall", "", "deleteVoipCalls", "voipMessenger", "getAllVoipCalls", "", "Lcom/im/bin/db/entities/VoipCall;", "insert", "snapChat", "selectAllVoipCalls", "Landroidx/lifecycle/LiveData;", "update", "status", "", "updated_status", "app_release"})
public abstract interface VoipCallDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.VoipCall snapChat);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "Select voipId from voip_call_table where voipId = :voipId")
    public abstract java.lang.String checkIfAlreadyExist(@org.jetbrains.annotations.NotNull()
    java.lang.String voipId);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "Select * from voip_call_table where voipMessenger=:voipMessenger order by date DESC")
    public abstract java.util.List<com.im.bin.db.entities.VoipCall> getAllVoipCalls(@org.jetbrains.annotations.NotNull()
    java.lang.String voipMessenger);
    
    @androidx.room.Query(value = "delete from voip_call_table where voipMessenger=:voipMessenger")
    public abstract void deleteVoipCalls(@org.jetbrains.annotations.NotNull()
    java.lang.String voipMessenger);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "Select * from voip_call_table where voipMessenger=:voipMessenger order by date DESC")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.VoipCall>> selectAllVoipCalls(@org.jetbrains.annotations.NotNull()
    java.lang.String voipMessenger);
    
    @androidx.room.Query(value = "delete from voip_call_table where voipId=:voipId")
    public abstract void deleteVoipCall(@org.jetbrains.annotations.NotNull()
    java.lang.String voipId);
    
    @androidx.room.Query(value = "Update voip_call_table set status=:updated_status where status=:status")
    public abstract void update(int status, int updated_status);
}