package com.im.bin.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00a2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0011\u0010D\u001a\u00020EH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010FJ\u0006\u0010G\u001a\u00020EJ\u0017\u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010FJ\u0012\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0013J\f\u0010K\u001a\b\u0012\u0004\u0012\u0002000\u0013J\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\f0\u0013J\u0006\u0010M\u001a\u00020EJ\u0012\u0010N\u001a\u00020E2\b\u0010O\u001a\u0004\u0018\u00010PH\u0002J\u0016\u0010Q\u001a\u00020E2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020I0\u000bH\u0002J\u0012\u0010S\u001a\u00020E2\b\u0010O\u001a\u0004\u0018\u00010PH\u0002J\u0016\u0010T\u001a\u00020E2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020I0UH\u0002J\u001a\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0W2\u0006\u0010X\u001a\u00020\u0005J\u000e\u0010Y\u001a\u00020E2\u0006\u0010Z\u001a\u00020\fJ\u0014\u0010[\u001a\u00020E2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u000e\u0010]\u001a\u00020E2\u0006\u0010^\u001a\u00020\u000fJ\u0016\u0010_\u001a\u00020E2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u001f\u0010`\u001a\u00020E2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020I0\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010aJ\u001c\u0010b\u001a\u00020E2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020I0U2\u0006\u0010c\u001a\u00020IR\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\'X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u0002000\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020\f0\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u00102\u001a\u000203X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u00108\u001a\u000209X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020?X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010C\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006d"}, d2 = {"Lcom/im/bin/viewModel/WhatsAppChatViewModel;", "Lcom/im/bin/viewModel/BaseViewModel;", "context", "Landroid/content/Context;", "imType", "", "firebaseSource", "Lcom/im/bin/firebase/FirebaseSource;", "(Landroid/content/Context;Ljava/lang/String;Lcom/im/bin/firebase/FirebaseSource;)V", "_chats", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/im/bin/models/Chat;", "_filteredChats", "_loadDataSection", "", "kotlin.jvm.PlatformType", "_selectedChat", "filteredChats", "Landroidx/lifecycle/LiveData;", "hikeRepository", "Lcom/im/bin/respository/HikeRepository;", "getHikeRepository", "()Lcom/im/bin/respository/HikeRepository;", "setHikeRepository", "(Lcom/im/bin/respository/HikeRepository;)V", "imoRepository", "Lcom/im/bin/respository/IMORepository;", "getImoRepository", "()Lcom/im/bin/respository/IMORepository;", "setImoRepository", "(Lcom/im/bin/respository/IMORepository;)V", "instagramRepository", "Lcom/im/bin/respository/InstagramRepository;", "getInstagramRepository", "()Lcom/im/bin/respository/InstagramRepository;", "setInstagramRepository", "(Lcom/im/bin/respository/InstagramRepository;)V", "lineRepository", "Lcom/im/bin/respository/LineRepository;", "getLineRepository", "()Lcom/im/bin/respository/LineRepository;", "setLineRepository", "(Lcom/im/bin/respository/LineRepository;)V", "loadDataSection", "getLoadDataSection", "()Landroidx/lifecycle/LiveData;", "networkState", "Lcom/im/bin/appUtils/NetworkState;", "selectedChat", "snapChatRepository", "Lcom/im/bin/respository/SnapChatRepository;", "getSnapChatRepository", "()Lcom/im/bin/respository/SnapChatRepository;", "setSnapChatRepository", "(Lcom/im/bin/respository/SnapChatRepository;)V", "viberRepository", "Lcom/im/bin/respository/ViberRepository;", "getViberRepository", "()Lcom/im/bin/respository/ViberRepository;", "setViberRepository", "(Lcom/im/bin/respository/ViberRepository;)V", "whatsAppRepository", "Lcom/im/bin/respository/WhatsAppRepository;", "getWhatsAppRepository", "()Lcom/im/bin/respository/WhatsAppRepository;", "setWhatsAppRepository", "(Lcom/im/bin/respository/WhatsAppRepository;)V", "deleteChats", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchConversations", "getAllConversations", "Lcom/im/bin/models/IMMessage;", "getChats", "getNetworkState", "getSelectedChat", "loadChats", "onConversationFetchFailed", "throwable", "", "onConversationFetched", "conversations", "onConversationUploadFailed", "onConversationUploaded", "", "performSearchQuery", "Lio/reactivex/Single;", "query", "selectChat", "chat", "setChatMessages", "chats", "setLoadDataSection", "load", "setOriginalChats", "updateConversation", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadConversations", "imMessage", "app_debug"})
public final class WhatsAppChatViewModel extends com.im.bin.viewModel.BaseViewModel {
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
    private final androidx.lifecycle.MutableLiveData<com.im.bin.appUtils.NetworkState> networkState = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.im.bin.models.Chat>> _chats = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.im.bin.models.Chat>> _filteredChats = null;
    private final androidx.lifecycle.LiveData<java.util.List<com.im.bin.models.Chat>> filteredChats = null;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _loadDataSection;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> loadDataSection = null;
    private final androidx.lifecycle.MutableLiveData<com.im.bin.models.Chat> _selectedChat = null;
    private final androidx.lifecycle.LiveData<com.im.bin.models.Chat> selectedChat = null;
    private final android.content.Context context = null;
    private final java.lang.String imType = null;
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
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getLoadDataSection() {
        return null;
    }
    
    public final void loadChats() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateConversation(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.models.IMMessage> conversations, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteChats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllConversations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.im.bin.models.IMMessage>> p0) {
        return null;
    }
    
    public final void fetchConversations() {
    }
    
    public final void uploadConversations(@org.jetbrains.annotations.NotNull()
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
    
    private final void setOriginalChats(java.util.List<com.im.bin.models.Chat> chats) {
    }
    
    public final void setChatMessages(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.models.Chat> chats) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.models.Chat>> getChats() {
        return null;
    }
    
    public final void selectChat(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.Chat chat) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.im.bin.models.Chat> getSelectedChat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<java.util.List<com.im.bin.models.Chat>> performSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    public final void setLoadDataSection(boolean load) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.im.bin.appUtils.NetworkState> getNetworkState() {
        return null;
    }
    
    public WhatsAppChatViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    com.im.bin.firebase.FirebaseSource firebaseSource) {
        super();
    }
}