package com.im.bin.respository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/im/bin/respository/DatabaseRepository;", "", "getHikeChats", "", "Lcom/im/bin/models/Chat;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIMOChats", "getInstagramChats", "getLineChats", "getSnapChatChats", "getViberChats", "getWhatsAppChats", "app_release"})
public abstract interface DatabaseRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getWhatsAppChats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.im.bin.models.Chat>> p0);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getViberChats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.im.bin.models.Chat>> p0);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLineChats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.im.bin.models.Chat>> p0);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getIMOChats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.im.bin.models.Chat>> p0);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSnapChatChats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.im.bin.models.Chat>> p0);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getInstagramChats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.im.bin.models.Chat>> p0);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getHikeChats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.im.bin.models.Chat>> p0);
}