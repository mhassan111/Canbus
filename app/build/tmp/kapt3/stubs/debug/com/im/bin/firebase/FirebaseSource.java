package com.im.bin.firebase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH&J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u00032\u0006\u0010\u000b\u001a\u00020\fH&J$\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH&J\b\u0010\u0013\u001a\u00020\u0014H&J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0011H&\u00a8\u0006\u0019"}, d2 = {"Lcom/im/bin/firebase/FirebaseSource;", "", "createAccount", "Lio/reactivex/Single;", "Lcom/google/firebase/auth/AuthResult;", "user", "Lcom/im/bin/models/User;", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "deleteChats", "Lio/reactivex/Completable;", "imType", "", "deleteConversation", "conversationId", "fetchConversations", "", "Lcom/im/bin/models/IMMessage;", "fetchCovnersation", "logOut", "", "login", "setUser", "uploadConversation", "imMessage", "app_debug"})
public abstract interface FirebaseSource {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.google.firebase.auth.AuthResult> login(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable uploadConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    com.im.bin.models.IMMessage imMessage);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.util.List<com.im.bin.models.IMMessage>> fetchCovnersation(@org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    java.lang.String conversationId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.util.List<com.im.bin.models.IMMessage>> fetchConversations(@org.jetbrains.annotations.NotNull()
    java.lang.String imType);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable deleteConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    java.lang.String conversationId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable deleteChats(@org.jetbrains.annotations.NotNull()
    java.lang.String imType);
    
    public abstract void logOut();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.google.firebase.auth.AuthResult> createAccount(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.google.firebase.auth.FirebaseUser currentUser();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable setUser(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user);
}