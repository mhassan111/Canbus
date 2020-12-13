package com.im.bin.services;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010\u0012\u001a\u00020\fH\u0016J\"\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u001a"}, d2 = {"Lcom/im/bin/services/ForegroundService;", "Landroid/app/Service;", "()V", "fileObserverEvent", "Lcom/im/bin/observer/RecursiveFileObserver$EventListener;", "recursiveFileObserver", "Lcom/im/bin/observer/RecursiveFileObserver;", "getRecursiveFileObserver", "()Lcom/im/bin/observer/RecursiveFileObserver;", "recursiveFileObserver$delegate", "Lkotlin/Lazy;", "createNotificationChannel", "", "onBind", "Landroid/os/IBinder;", "p0", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "intent", "flags", "startId", "onWhatsAppDirectoryModified", "Companion", "app_release"})
public final class ForegroundService extends android.app.Service {
    private final kotlin.Lazy recursiveFileObserver$delegate = null;
    private final com.im.bin.observer.RecursiveFileObserver.EventListener fileObserverEvent = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID = "WhatsDeletedStore Channel";
    public static final com.im.bin.services.ForegroundService.Companion Companion = null;
    
    private final com.im.bin.observer.RecursiveFileObserver getRecursiveFileObserver() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void createNotificationChannel() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void onWhatsAppDirectoryModified() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent p0) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    public ForegroundService() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/im/bin/services/ForegroundService$Companion;", "", "()V", "CHANNEL_ID", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}