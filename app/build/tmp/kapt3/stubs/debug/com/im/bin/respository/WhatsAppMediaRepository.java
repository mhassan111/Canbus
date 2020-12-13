package com.im.bin.respository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u0005J\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tJ\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u000bJ\u0014\u0010\u0019\u001a\u00020\u00162\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"Lcom/im/bin/respository/WhatsAppMediaRepository;", "", "context", "Landroid/content/Context;", "mediaType", "", "(Landroid/content/Context;Ljava/lang/String;)V", "(Landroid/content/Context;)V", "mediaList", "Landroidx/lifecycle/LiveData;", "", "Lcom/im/bin/db/entities/WhatsAppMedia;", "whatsAppMediaDao", "Lcom/im/bin/db/dao/WhatsAppMediaDao;", "getWhatsAppMediaDao", "()Lcom/im/bin/db/dao/WhatsAppMediaDao;", "setWhatsAppMediaDao", "(Lcom/im/bin/db/dao/WhatsAppMediaDao;)V", "checkMediaNotExitsAlready", "", "mediaId", "deleteAll", "", "deleteMedia", "getWhatsAppMediaListLive", "insertMedia", "whatsAppMedia", "whatsAppMediaList", "app_debug"})
public final class WhatsAppMediaRepository {
    private androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> mediaList;
    @org.jetbrains.annotations.NotNull()
    private com.im.bin.db.dao.WhatsAppMediaDao whatsAppMediaDao;
    private java.lang.String mediaType = "";
    private final android.content.Context context = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.db.dao.WhatsAppMediaDao getWhatsAppMediaDao() {
        return null;
    }
    
    public final void setWhatsAppMediaDao(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.dao.WhatsAppMediaDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> getWhatsAppMediaListLive() {
        return null;
    }
    
    public final void insertMedia(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.WhatsAppMedia whatsAppMedia) {
    }
    
    public final void insertMedia(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.db.entities.WhatsAppMedia> whatsAppMediaList) {
    }
    
    public final void deleteMedia(@org.jetbrains.annotations.NotNull()
    java.lang.String mediaId) {
    }
    
    public final void deleteAll(@org.jetbrains.annotations.NotNull()
    java.lang.String mediaType) {
    }
    
    public final boolean checkMediaNotExitsAlready(@org.jetbrains.annotations.NotNull()
    java.lang.String mediaId) {
        return false;
    }
    
    public WhatsAppMediaRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public WhatsAppMediaRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String mediaType) {
        super();
    }
}