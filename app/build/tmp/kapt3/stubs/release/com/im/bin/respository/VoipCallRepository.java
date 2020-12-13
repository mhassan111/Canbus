package com.im.bin.respository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005J\u0006\u0010\u0018\u001a\u00020\u0016J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tJ\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u000bJ\u0014\u0010\u001d\u001a\u00020\u00162\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/im/bin/respository/VoipCallRepository;", "", "context", "Landroid/content/Context;", "voipMessenger", "", "(Landroid/content/Context;Ljava/lang/String;)V", "(Landroid/content/Context;)V", "calls", "Landroidx/lifecycle/LiveData;", "", "Lcom/im/bin/db/entities/VoipCall;", "voipCallDao", "Lcom/im/bin/db/dao/VoipCallDao;", "getVoipCallDao", "()Lcom/im/bin/db/dao/VoipCallDao;", "setVoipCallDao", "(Lcom/im/bin/db/dao/VoipCallDao;)V", "checkMessageNotExitsAlready", "", "messageId", "deleteVoipCall", "", "voipId", "deleteVoipCalls", "getAllVoipCalls", "getVoipCallsLive", "insertVoipCall", "voipCall", "updateConversation", "voipCalls", "app_release"})
public final class VoipCallRepository {
    private androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.VoipCall>> calls;
    @org.jetbrains.annotations.NotNull()
    private com.im.bin.db.dao.VoipCallDao voipCallDao;
    private java.lang.String voipMessenger = "";
    private final android.content.Context context = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.db.dao.VoipCallDao getVoipCallDao() {
        return null;
    }
    
    public final void setVoipCallDao(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.dao.VoipCallDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.im.bin.db.entities.VoipCall> getAllVoipCalls() {
        return null;
    }
    
    public final void deleteVoipCalls() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.VoipCall>> getVoipCallsLive() {
        return null;
    }
    
    public final void updateConversation(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.db.entities.VoipCall> voipCalls) {
    }
    
    public final void deleteVoipCall(@org.jetbrains.annotations.NotNull()
    java.lang.String voipId) {
    }
    
    public final void insertVoipCall(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.VoipCall voipCall) {
    }
    
    public final boolean checkMessageNotExitsAlready(@org.jetbrains.annotations.NotNull()
    java.lang.String messageId) {
        return false;
    }
    
    public VoipCallRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public VoipCallRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String voipMessenger) {
        super();
    }
}