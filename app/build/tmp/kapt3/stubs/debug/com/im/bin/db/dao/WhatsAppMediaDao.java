package com.im.bin.db.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\'J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\'J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\'J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\'J\u0016\u0010\n\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\'J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000e0\u00102\u0006\u0010\u0007\u001a\u00020\u0003H\'J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\'\u00a8\u0006\u0012"}, d2 = {"Lcom/im/bin/db/dao/WhatsAppMediaDao;", "", "checkIfAlreadyExists", "", "media_id", "deleteAll", "", "media_type", "is_deleted", "deleteMedia", "insert", "whatsAppMedia", "Lcom/im/bin/db/entities/WhatsAppMedia;", "whatsAppMediaList", "", "selectAllWhatsAppMedia", "Landroidx/lifecycle/LiveData;", "update", "app_debug"})
public abstract interface WhatsAppMediaDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.WhatsAppMedia whatsAppMedia);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.db.entities.WhatsAppMedia> whatsAppMediaList);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "Select media_id from whats_app_media_table where media_id=:media_id")
    public abstract java.lang.String checkIfAlreadyExists(@org.jetbrains.annotations.NotNull()
    java.lang.String media_id);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "Select * from whats_app_media_table where media_type=:media_type AND is_deleted=0")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> selectAllWhatsAppMedia(@org.jetbrains.annotations.NotNull()
    java.lang.String media_type);
    
    @androidx.room.Query(value = "update whats_app_media_table set is_deleted=:is_deleted where media_id=:media_id")
    public abstract void update(@org.jetbrains.annotations.NotNull()
    java.lang.String media_id, @org.jetbrains.annotations.NotNull()
    java.lang.String is_deleted);
    
    @androidx.room.Query(value = "delete from whats_app_media_table where media_id =:media_id")
    public abstract void deleteMedia(@org.jetbrains.annotations.NotNull()
    java.lang.String media_id);
    
    @androidx.room.Query(value = "update whats_app_media_table set is_deleted=:is_deleted where media_type=:media_type")
    public abstract void deleteAll(@org.jetbrains.annotations.NotNull()
    java.lang.String media_type, @org.jetbrains.annotations.NotNull()
    java.lang.String is_deleted);
}