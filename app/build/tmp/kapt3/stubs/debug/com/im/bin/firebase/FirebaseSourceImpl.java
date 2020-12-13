package com.im.bin.firebase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0016J\u001c\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J$\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0016J\b\u0010 \u001a\u00020!H\u0016J\u001e\u0010\"\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010$\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u001eH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f\u00a8\u0006&"}, d2 = {"Lcom/im/bin/firebase/FirebaseSourceImpl;", "Lcom/im/bin/firebase/FirebaseSource;", "()V", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "getFirebaseAuth", "()Lcom/google/firebase/auth/FirebaseAuth;", "firebaseAuth$delegate", "Lkotlin/Lazy;", "firebaseFireStore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "getFirebaseFireStore", "()Lcom/google/firebase/firestore/FirebaseFirestore;", "firebaseFireStore$delegate", "createAccount", "Lio/reactivex/Single;", "Lcom/google/firebase/auth/AuthResult;", "kotlin.jvm.PlatformType", "user", "Lcom/im/bin/models/User;", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "deleteChats", "Lio/reactivex/Completable;", "imType", "", "deleteConversation", "conversationId", "fetchConversations", "", "Lcom/im/bin/models/IMMessage;", "fetchCovnersation", "logOut", "", "login", "setUser", "uploadConversation", "imMessage", "app_debug"})
public final class FirebaseSourceImpl implements com.im.bin.firebase.FirebaseSource {
    private final kotlin.Lazy firebaseAuth$delegate = null;
    private final kotlin.Lazy firebaseFireStore$delegate = null;
    
    private final com.google.firebase.auth.FirebaseAuth getFirebaseAuth() {
        return null;
    }
    
    private final com.google.firebase.firestore.FirebaseFirestore getFirebaseFireStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.google.firebase.auth.AuthResult> login(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable uploadConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    com.im.bin.models.IMMessage imMessage) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<java.util.List<com.im.bin.models.IMMessage>> fetchCovnersation(@org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    java.lang.String conversationId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<java.util.List<com.im.bin.models.IMMessage>> fetchConversations(@org.jetbrains.annotations.NotNull()
    java.lang.String imType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable deleteConversation(@org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    java.lang.String conversationId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable deleteChats(@org.jetbrains.annotations.NotNull()
    java.lang.String imType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.google.firebase.auth.AuthResult> createAccount(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable setUser(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.google.firebase.auth.FirebaseUser currentUser() {
        return null;
    }
    
    @java.lang.Override()
    public void logOut() {
    }
    
    @javax.inject.Inject()
    public FirebaseSourceImpl() {
        super();
    }
}