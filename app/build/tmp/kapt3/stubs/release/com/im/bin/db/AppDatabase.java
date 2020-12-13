package com.im.bin.db;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.im.bin.appUtils.DateConverter.class})
@androidx.room.Database(entities = {com.im.bin.db.entities.WhatsApp.class, com.im.bin.db.entities.WhatsAppMedia.class, com.im.bin.db.entities.SnapChat.class, com.im.bin.db.entities.Instagram.class, com.im.bin.db.entities.Line.class, com.im.bin.db.entities.IMO.class, com.im.bin.db.entities.Viber.class, com.im.bin.db.entities.Hike.class, com.im.bin.db.entities.VoipCall.class}, version = 7, exportSchema = false)
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&\u00a8\u0006\u0016"}, d2 = {"Lcom/im/bin/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "hikeDao", "Lcom/im/bin/db/dao/HikeDao;", "imoDao", "Lcom/im/bin/db/dao/IMODao;", "instagramDao", "Lcom/im/bin/db/dao/InstagramDao;", "lineDao", "Lcom/im/bin/db/dao/LineDao;", "snapChatDao", "Lcom/im/bin/db/dao/SnapChatDao;", "viberDao", "Lcom/im/bin/db/dao/ViberDao;", "voipCallDao", "Lcom/im/bin/db/dao/VoipCallDao;", "whatsAppDao", "Lcom/im/bin/db/dao/WhatsAppDao;", "whatsAppMediaDao", "Lcom/im/bin/db/dao/WhatsAppMediaDao;", "Companion", "app_release"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    private static final java.lang.String DB_NAME = "im_bin_db";
    private static volatile com.im.bin.db.AppDatabase INSTANCE;
    private static final androidx.room.migration.Migration MIGRATION_5_6 = null;
    private static final androidx.room.migration.Migration MIGRATION_6_7 = null;
    public static final com.im.bin.db.AppDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.im.bin.db.dao.WhatsAppDao whatsAppDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.im.bin.db.dao.WhatsAppMediaDao whatsAppMediaDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.im.bin.db.dao.SnapChatDao snapChatDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.im.bin.db.dao.InstagramDao instagramDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.im.bin.db.dao.ViberDao viberDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.im.bin.db.dao.LineDao lineDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.im.bin.db.dao.IMODao imoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.im.bin.db.dao.HikeDao hikeDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.im.bin.db.dao.VoipCallDao voipCallDao();
    
    public AppDatabase() {
        super();
    }
    
    /**
     * Get Instance of Room Database
     * @param context Context
     * @return instance
     */
    @org.jetbrains.annotations.Nullable()
    public static final com.im.bin.db.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/im/bin/db/AppDatabase$Companion;", "", "()V", "DB_NAME", "", "INSTANCE", "Lcom/im/bin/db/AppDatabase;", "MIGRATION_5_6", "Landroidx/room/migration/Migration;", "MIGRATION_6_7", "getInstance", "context", "Landroid/content/Context;", "app_release"})
    public static final class Companion {
        
        /**
         * Get Instance of Room Database
         * @param context Context
         * @return instance
         */
        @org.jetbrains.annotations.Nullable()
        public final com.im.bin.db.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}