package com.im.bin.respository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\rH\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lcom/im/bin/respository/UserRepositoryImpl;", "Lcom/im/bin/respository/UserRepository;", "()V", "firebaseSource", "Lcom/im/bin/firebase/FirebaseSource;", "getFirebaseSource", "()Lcom/im/bin/firebase/FirebaseSource;", "setFirebaseSource", "(Lcom/im/bin/firebase/FirebaseSource;)V", "createAccount", "Lio/reactivex/Single;", "Lcom/google/firebase/auth/AuthResult;", "user", "Lcom/im/bin/models/User;", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "logOut", "", "login", "setUser", "Lio/reactivex/Completable;", "app_debug"})
public final class UserRepositoryImpl implements com.im.bin.respository.UserRepository {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.im.bin.firebase.FirebaseSource firebaseSource;
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.firebase.FirebaseSource getFirebaseSource() {
        return null;
    }
    
    public final void setFirebaseSource(@org.jetbrains.annotations.NotNull()
    com.im.bin.firebase.FirebaseSource p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.google.firebase.auth.AuthResult> login(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.google.firebase.auth.AuthResult> createAccount(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public com.google.firebase.auth.FirebaseUser currentUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Completable setUser(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user) {
        return null;
    }
    
    @java.lang.Override()
    public void logOut() {
    }
    
    @javax.inject.Inject()
    public UserRepositoryImpl() {
        super();
    }
}