package com.im.bin.appUtils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\nJ\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011J\u0012\u0010\u0013\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\nJ\b\u0010\u0017\u001a\u00020\nH\u0007J\u0017\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0007\u00a2\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\nH\u0007J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0006H\u0007J\u0010\u0010!\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010\nJ\u0018\u0010#\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\nH\u0007J\u0010\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\nH\u0007J\u0006\u0010\'\u001a\u00020\u0004J\u000e\u0010(\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u0006J\u001e\u0010)\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020\nJ@\u0010)\u001a\u00020\u000f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010*\u001a\u0004\u0018\u00010\n2\b\u0010+\u001a\u0004\u0018\u00010\n2\b\u0010,\u001a\u0004\u0018\u00010\n2\b\u0010-\u001a\u0004\u0018\u00010\n2\u0006\u0010.\u001a\u00020/J\u001e\u00100\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020\nJ\u000e\u00101\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u00102\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u00103\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00104\u001a\u000205H\u0007\u00a8\u00066"}, d2 = {"Lcom/im/bin/appUtils/Util;", "", "()V", "appearOnTopPermissionAllowed", "", "context", "Landroid/content/Context;", "checkAccessibillityPermissionGranted", "checkEmailAddressValid", "email", "", "checkInternetIsConnected", "createChildDir", "directoryName", "createStorageDir", "", "dpToPx", "", "dp", "freeVersionInstalled", "generateFontAwesomeIcon", "Lcom/im/bin/appUtils/TextDrawable;", "iconString", "generateUniqueID", "getDate", "Ljava/util/Date;", "timeStamp", "", "(Ljava/lang/Long;)Ljava/util/Date;", "getTodaysDate", "format", "isAppIgnoringBatteryOptimization", "mContext", "isImageFileExtension", "file", "isServiceRunning", "serviceName", "md5Hash", "s", "osVersionGreaterThanLollipop", "rateOnGooglePlay", "showAlertDialog", "title", "message", "positiveText", "negativeText", "listener", "Lcom/im/bin/interfaces/CustomDialogListener;", "showCustomAlertDialog", "showDialog", "showLoadAds", "startService", "intent", "Landroid/content/Intent;", "app_release"})
public final class Util {
    public static final com.im.bin.appUtils.Util INSTANCE = null;
    
    /**
     * Check if Battery Optimisation turned On
     */
    @android.annotation.TargetApi(value = android.os.Build.VERSION_CODES.M)
    public final boolean isAppIgnoringBatteryOptimization(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext) {
        return false;
    }
    
    public static final boolean checkInternetIsConnected(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    private final void createStorageDir() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String createChildDir(@org.jetbrains.annotations.NotNull()
    java.lang.String directoryName) {
        return null;
    }
    
    /**
     * Convert the time to date instance
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.util.Date getDate(@org.jetbrains.annotations.Nullable()
    java.lang.Long timeStamp) {
        return null;
    }
    
    /**
     * Returns today's date
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getTodaysDate(@org.jetbrains.annotations.NotNull()
    java.lang.String format) {
        return null;
    }
    
    /**
     * Check if OS version Greater than Lollipop
     */
    public final boolean osVersionGreaterThanLollipop() {
        return false;
    }
    
    /**
     * Check the Email Address is Valid Or Not
     */
    public final boolean checkEmailAddressValid(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return false;
    }
    
    /**
     * Check the Accessibility Permission is granted
     */
    public static final boolean checkAccessibillityPermissionGranted(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final int dpToPx(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int dp) {
        return 0;
    }
    
    public final boolean appearOnTopPermissionAllowed(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String md5Hash(@org.jetbrains.annotations.NotNull()
    java.lang.String s) {
        return null;
    }
    
    public final void rateOnGooglePlay(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext) {
    }
    
    public final void showAlertDialog(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.String positiveText, @org.jetbrains.annotations.Nullable()
    java.lang.String negativeText, @org.jetbrains.annotations.NotNull()
    com.im.bin.interfaces.CustomDialogListener listener) {
    }
    
    public final void showAlertDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void showCustomAlertDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void showDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.im.bin.appUtils.TextDrawable generateFontAwesomeIcon(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String iconString) {
        return null;
    }
    
    public static final boolean freeVersionInstalled(@org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        return false;
    }
    
    public static final boolean isServiceRunning(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String serviceName) {
        return false;
    }
    
    public static final void startService(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String generateUniqueID() {
        return null;
    }
    
    public static final boolean showLoadAds(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean isImageFileExtension(@org.jetbrains.annotations.Nullable()
    java.lang.String file) {
        return false;
    }
    
    private Util() {
        super();
    }
}