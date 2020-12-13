package com.im.bin.appUtils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\bH\u0007J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0018"}, d2 = {"Lcom/im/bin/appUtils/ActivityUtil;", "", "()V", "launchChatConversationActivity", "", "context", "Landroid/content/Context;", "imType", "", "chat", "Lcom/im/bin/models/Chat;", "launchHomeActivity", "launchLockScreenActivity", "type", "launchLogInActivity", "launchMainActivity", "launchPremiumFeaturesActivity", "launchPreviewImageActivity", "activity", "Landroid/app/Activity;", "whatsAppMedia", "Lcom/im/bin/db/entities/WhatsAppMedia;", "launchSignUpActivity", "restartService", "app_debug"})
public final class ActivityUtil {
    public static final com.im.bin.appUtils.ActivityUtil INSTANCE = null;
    
    public final void restartService(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void launchPreviewImageActivity(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.WhatsAppMedia whatsAppMedia) {
    }
    
    public final void launchPremiumFeaturesActivity(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void launchLogInActivity(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void launchSignUpActivity(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public static final void launchLockScreenActivity(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
    }
    
    public final void launchMainActivity(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void launchHomeActivity(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void launchChatConversationActivity(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String imType, @org.jetbrains.annotations.NotNull()
    com.im.bin.models.Chat chat) {
    }
    
    private ActivityUtil() {
        super();
    }
}