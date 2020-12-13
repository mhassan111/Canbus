package com.im.bin.appUtils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/im/bin/appUtils/AppRater;", "", "()V", "APP_PACKAGE_NAME", "", "DAYS_UNTIL_PROMPT", "", "LAUNCHES_UNTIL_PROMPT", "launchAppRater", "", "mContext", "Landroid/content/Context;", "showRateDialog", "app_debug"})
public final class AppRater {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String APP_PACKAGE_NAME = "com.im.bin";
    private static final int DAYS_UNTIL_PROMPT = 1;
    private static final int LAUNCHES_UNTIL_PROMPT = 3;
    public static final com.im.bin.appUtils.AppRater INSTANCE = null;
    
    public final void launchAppRater(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext) {
    }
    
    private final void showRateDialog(android.content.Context mContext) {
    }
    
    private AppRater() {
        super();
    }
}