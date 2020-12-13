package com.im.bin.appUtils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011J)\u0010\u0014\u001a\u0004\u0018\u0001H\u0015\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0017\u00a2\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\u0011J!\u0010\u001a\u001a\u00020\u001b\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u0002H\u0015\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u000fJ\u0016\u0010 \u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0013J\u0016\u0010\"\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0011R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006#"}, d2 = {"Lcom/im/bin/appUtils/SharedPreferencesUtil;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mEditor", "Landroid/content/SharedPreferences$Editor;", "kotlin.jvm.PlatformType", "mGson", "Lcom/google/gson/Gson;", "mPreferences", "Landroid/content/SharedPreferences;", "getMPreferences", "()Landroid/content/SharedPreferences;", "getBoolPref", "", "key", "", "getIntegerValue", "", "getObject", "T", "c", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "getPreferenceValue", "putObject", "", "y", "(Ljava/lang/String;Ljava/lang/Object;)V", "setBoolPref", "pref", "setIntegerValue", "value", "setPreferenceValue", "app_debug"})
public final class SharedPreferencesUtil {
    private final android.content.SharedPreferences mPreferences = null;
    private final android.content.SharedPreferences.Editor mEditor = null;
    private final com.google.gson.Gson mGson = null;
    
    public final android.content.SharedPreferences getMPreferences() {
        return null;
    }
    
    /**
     * Saves object into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     */
    public final <T extends java.lang.Object>void putObject(@org.jetbrains.annotations.NotNull()
    java.lang.String key, T y) {
    }
    
    /**
     * Saves collection of objects into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     */
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>T getObject(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.Class<T> c) {
        return null;
    }
    
    /**
     * Save the String Value in SharedPreferences.
     */
    public final void setPreferenceValue(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    /**
     * get the String Value from Preferences.
     */
    public final int getIntegerValue(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return 0;
    }
    
    /**
     * Save the String Value in SharedPreferences.
     */
    public final void setIntegerValue(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int value) {
    }
    
    /**
     * get the String Value from Preferences.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPreferenceValue(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    /**
     * Save the Bool Value in SharedPreferences.
     */
    public final void setBoolPref(@org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean pref) {
    }
    
    /**
     * get the Bool Value from Preferences.
     */
    public final boolean getBoolPref(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return false;
    }
    
    public SharedPreferencesUtil(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}