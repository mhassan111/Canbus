package com.im.bin.respository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\b\u0010\t\u001a\u00020\nH&J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u000e"}, d2 = {"Lcom/im/bin/respository/UserRepository;", "", "createAccount", "Lio/reactivex/Single;", "Lcom/google/firebase/auth/AuthResult;", "user", "Lcom/im/bin/models/User;", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "logOut", "", "login", "setUser", "Lio/reactivex/Completable;", "app_debug"})
public abstract interface UserRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.google.firebase.auth.AuthResult> login(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.google.firebase.auth.AuthResult> createAccount(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.google.firebase.auth.FirebaseUser currentUser();
    
    public abstract void logOut();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Completable setUser(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user);
}