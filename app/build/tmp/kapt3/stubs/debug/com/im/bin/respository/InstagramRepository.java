package com.im.bin.respository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005J\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u0005J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fJ\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0011J\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0010J\u0014\u0010\u001f\u001a\u00020\u00162\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00110\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/im/bin/respository/InstagramRepository;", "", "context", "Landroid/content/Context;", "conversationId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "(Landroid/content/Context;)V", "instagramDao", "Lcom/im/bin/db/dao/InstagramDao;", "getInstagramDao", "()Lcom/im/bin/db/dao/InstagramDao;", "setInstagramDao", "(Lcom/im/bin/db/dao/InstagramDao;)V", "messages", "Landroidx/lifecycle/LiveData;", "", "Lcom/im/bin/db/entities/Instagram;", "checkMessageNotExitsAlready", "", "messageId", "deleteChats", "", "deleteConversation", "deleteMessage", "getAllInstagramMessages", "getInstagramMessagesLive", "insertInstagram", "instagram", "selectInstagramChats", "Lcom/im/bin/models/Chat;", "updateConversation", "conversations", "app_debug"})
public final class InstagramRepository {
    private androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Instagram>> messages;
    @org.jetbrains.annotations.NotNull()
    private com.im.bin.db.dao.InstagramDao instagramDao;
    private java.lang.String conversationId = "";
    private final android.content.Context context = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.db.dao.InstagramDao getInstagramDao() {
        return null;
    }
    
    public final void setInstagramDao(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.dao.InstagramDao p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.im.bin.db.entities.Instagram> getAllInstagramMessages() {
        return null;
    }
    
    public final void deleteChats() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Instagram>> getInstagramMessagesLive() {
        return null;
    }
    
    public final void updateConversation(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.db.entities.Instagram> conversations) {
    }
    
    public final void deleteConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String conversationId) {
    }
    
    public final void deleteMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String messageId) {
    }
    
    public final void insertInstagram(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.Instagram instagram) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.im.bin.models.Chat> selectInstagramChats() {
        return null;
    }
    
    public final boolean checkMessageNotExitsAlready(@org.jetbrains.annotations.NotNull()
    java.lang.String messageId) {
        return false;
    }
    
    public InstagramRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public InstagramRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String conversationId) {
        super();
    }
}