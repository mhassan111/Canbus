package com.im.bin.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00d2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0019\u0010^\u001a\u00020_2\u0006\u0010\u0006\u001a\u00020\u0005H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010`J\u000e\u0010a\u001a\u00020_2\u0006\u0010b\u001a\u00020cJ\u0016\u0010d\u001a\u00020_2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0012\u0010e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000f0\u0012J\f\u0010f\u001a\b\u0012\u0004\u0012\u00020\f0\u0012J\f\u0010g\u001a\b\u0012\u0004\u0012\u00020?0\u0012J\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0012\u0010i\u001a\u00020_2\b\u0010j\u001a\u0004\u0018\u00010kH\u0002J\u0016\u0010l\u001a\u00020_2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0002J\u0012\u0010n\u001a\u00020_2\b\u0010j\u001a\u0004\u0018\u00010kH\u0002J\u0016\u0010o\u001a\u00020_2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u001a\u0010p\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000f0q2\u0006\u0010r\u001a\u00020\u0005J\u0014\u0010s\u001a\u00020_2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\f0\u000fJ\u0014\u0010t\u001a\u00020_2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\f0\u000fJ\u001f\u0010u\u001a\u00020_2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010vJ$\u0010w\u001a\u00020_2\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010x\u001a\u00020\fR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000f0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000f0\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u000f0\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R\u001a\u0010#\u001a\u00020$X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R&\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\u000f0\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0016\"\u0004\b,\u0010\u0018R\u001a\u0010-\u001a\u00020.X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R&\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\u000f0\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0016\"\u0004\b6\u0010\u0018R\u001a\u00107\u001a\u000208X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u0014\u0010=\u001a\b\u0012\u0004\u0012\u00020\f0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\u000f0\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u0016\"\u0004\bC\u0010\u0018R\u001a\u0010D\u001a\u00020EX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR&\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020K0\u000f0\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0016\"\u0004\bM\u0010\u0018R\u001a\u0010N\u001a\u00020OX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR&\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020U0\u000f0\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u0016\"\u0004\bW\u0010\u0018R\u001a\u0010X\u001a\u00020YX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006y"}, d2 = {"Lcom/im/bin/viewModel/ChatConversationViewModel;", "Lcom/im/bin/viewModel/BaseViewModel;", "context", "Landroid/content/Context;", "imType", "", "conversationId", "firebaseSource", "Lcom/im/bin/firebase/FirebaseSource;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/im/bin/firebase/FirebaseSource;)V", "_conversations", "", "Lcom/im/bin/models/IMMessage;", "_filteredConversations", "Landroidx/lifecycle/MutableLiveData;", "", "_messageRemoved", "filteredConversations", "Landroidx/lifecycle/LiveData;", "hikeMessagesList", "Lcom/im/bin/db/entities/Hike;", "getHikeMessagesList", "()Landroidx/lifecycle/LiveData;", "setHikeMessagesList", "(Landroidx/lifecycle/LiveData;)V", "hikeRepository", "Lcom/im/bin/respository/HikeRepository;", "getHikeRepository", "()Lcom/im/bin/respository/HikeRepository;", "setHikeRepository", "(Lcom/im/bin/respository/HikeRepository;)V", "imoMessagesList", "Lcom/im/bin/db/entities/IMO;", "getImoMessagesList", "setImoMessagesList", "imoRepository", "Lcom/im/bin/respository/IMORepository;", "getImoRepository", "()Lcom/im/bin/respository/IMORepository;", "setImoRepository", "(Lcom/im/bin/respository/IMORepository;)V", "instagramMessagesList", "Lcom/im/bin/db/entities/Instagram;", "getInstagramMessagesList", "setInstagramMessagesList", "instagramRepository", "Lcom/im/bin/respository/InstagramRepository;", "getInstagramRepository", "()Lcom/im/bin/respository/InstagramRepository;", "setInstagramRepository", "(Lcom/im/bin/respository/InstagramRepository;)V", "lineMessagesList", "Lcom/im/bin/db/entities/Line;", "getLineMessagesList", "setLineMessagesList", "lineRepository", "Lcom/im/bin/respository/LineRepository;", "getLineRepository", "()Lcom/im/bin/respository/LineRepository;", "setLineRepository", "(Lcom/im/bin/respository/LineRepository;)V", "messageRemoved", "networkState", "Lcom/im/bin/appUtils/NetworkState;", "snapChatMessagesList", "Lcom/im/bin/db/entities/SnapChat;", "getSnapChatMessagesList", "setSnapChatMessagesList", "snapChatRepository", "Lcom/im/bin/respository/SnapChatRepository;", "getSnapChatRepository", "()Lcom/im/bin/respository/SnapChatRepository;", "setSnapChatRepository", "(Lcom/im/bin/respository/SnapChatRepository;)V", "viberMessagesList", "Lcom/im/bin/db/entities/Viber;", "getViberMessagesList", "setViberMessagesList", "viberRepository", "Lcom/im/bin/respository/ViberRepository;", "getViberRepository", "()Lcom/im/bin/respository/ViberRepository;", "setViberRepository", "(Lcom/im/bin/respository/ViberRepository;)V", "whatsAppMessagesList", "Lcom/im/bin/db/entities/WhatsApp;", "getWhatsAppMessagesList", "setWhatsAppMessagesList", "whatsAppRepository", "Lcom/im/bin/respository/WhatsAppRepository;", "getWhatsAppRepository", "()Lcom/im/bin/respository/WhatsAppRepository;", "setWhatsAppRepository", "(Lcom/im/bin/respository/WhatsAppRepository;)V", "deleteConversation", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMessage", "position", "", "fetchConversation", "getFilteredConversation", "getMessageRemoved", "getNetworkState", "getOriginalConversation", "onConversationFetchFailed", "throwable", "", "onConversationFetched", "conversations", "onConversationUploadFailed", "onConversationUploaded", "performSearchQuery", "Lio/reactivex/Single;", "query", "setFilteredConversation", "setOriginalConversation", "updateConversation", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadConversation", "imMessage", "app_debug"})
public final class ChatConversationViewModel extends com.im.bin.viewModel.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    public com.im.bin.respository.WhatsAppRepository whatsAppRepository;
    @org.jetbrains.annotations.NotNull()
    public com.im.bin.respository.ViberRepository viberRepository;
    @org.jetbrains.annotations.NotNull()
    public com.im.bin.respository.LineRepository lineRepository;
    @org.jetbrains.annotations.NotNull()
    public com.im.bin.respository.IMORepository imoRepository;
    @org.jetbrains.annotations.NotNull()
    public com.im.bin.respository.InstagramRepository instagramRepository;
    @org.jetbrains.annotations.NotNull()
    public com.im.bin.respository.SnapChatRepository snapChatRepository;
    @org.jetbrains.annotations.NotNull()
    public com.im.bin.respository.HikeRepository hikeRepository;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsApp>> whatsAppMessagesList;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Viber>> viberMessagesList;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Line>> lineMessagesList;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.IMO>> imoMessagesList;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Instagram>> instagramMessagesList;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.SnapChat>> snapChatMessagesList;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Hike>> hikeMessagesList;
    private final androidx.lifecycle.MutableLiveData<com.im.bin.appUtils.NetworkState> networkState = null;
    private final java.util.List<com.im.bin.models.IMMessage> _conversations = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.im.bin.models.IMMessage>> _filteredConversations = null;
    private final androidx.lifecycle.LiveData<java.util.List<com.im.bin.models.IMMessage>> filteredConversations = null;
    private final androidx.lifecycle.MutableLiveData<com.im.bin.models.IMMessage> _messageRemoved = null;
    private final androidx.lifecycle.LiveData<com.im.bin.models.IMMessage> messageRemoved = null;
    private final java.lang.String imType = null;
    private final java.lang.String conversationId = null;
    private final com.im.bin.firebase.FirebaseSource firebaseSource = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.WhatsAppRepository getWhatsAppRepository() {
        return null;
    }
    
    public final void setWhatsAppRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.WhatsAppRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.ViberRepository getViberRepository() {
        return null;
    }
    
    public final void setViberRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.ViberRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.LineRepository getLineRepository() {
        return null;
    }
    
    public final void setLineRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.LineRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.IMORepository getImoRepository() {
        return null;
    }
    
    public final void setImoRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.IMORepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.InstagramRepository getInstagramRepository() {
        return null;
    }
    
    public final void setInstagramRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.InstagramRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.SnapChatRepository getSnapChatRepository() {
        return null;
    }
    
    public final void setSnapChatRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.SnapChatRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.HikeRepository getHikeRepository() {
        return null;
    }
    
    public final void setHikeRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.HikeRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsApp>> getWhatsAppMessagesList() {
        return null;
    }
    
    public final void setWhatsAppMessagesList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsApp>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Viber>> getViberMessagesList() {
        return null;
    }
    
    public final void setViberMessagesList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Viber>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Line>> getLineMessagesList() {
        return null;
    }
    
    public final void setLineMessagesList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Line>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.IMO>> getImoMessagesList() {
        return null;
    }
    
    public final void setImoMessagesList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.IMO>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Instagram>> getInstagramMessagesList() {
        return null;
    }
    
    public final void setInstagramMessagesList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Instagram>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.SnapChat>> getSnapChatMessagesList() {
        return null;
    }
    
    public final void setSnapChatMessagesList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.SnapChat>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Hike>> getHikeMessagesList() {
        return null;
    }
    
    public final void setHikeMessagesList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.Hike>> p0) {
    }
    
    public final void setOriginalConversation(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.models.IMMessage> conversations) {
    }
    
    public final void setFilteredConversation(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.models.IMMessage> conversations) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.models.IMMessage>> getFilteredConversation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.im.bin.models.IMMessage> getOriginalConversation() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateConversation(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.models.IMMessage> conversations, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    public final void deleteMessage(int position) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String conversationId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    public final void fetchConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    java.lang.String conversationId) {
    }
    
    public final void uploadConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.models.IMMessage> conversations, @org.jetbrains.annotations.NotNull()
    com.im.bin.models.IMMessage imMessage) {
    }
    
    private final void onConversationFetched(java.util.List<com.im.bin.models.IMMessage> conversations) {
    }
    
    private final void onConversationFetchFailed(java.lang.Throwable throwable) {
    }
    
    private final void onConversationUploaded(java.util.List<com.im.bin.models.IMMessage> conversations) {
    }
    
    private final void onConversationUploadFailed(java.lang.Throwable throwable) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<java.util.List<com.im.bin.models.IMMessage>> performSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.im.bin.appUtils.NetworkState> getNetworkState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.im.bin.models.IMMessage> getMessageRemoved() {
        return null;
    }
    
    public ChatConversationViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    java.lang.String conversationId, @org.jetbrains.annotations.NotNull()
    com.im.bin.firebase.FirebaseSource firebaseSource) {
        super();
    }
}