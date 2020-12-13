package com.im.bin.dialog;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0018"}, d2 = {"Lcom/im/bin/dialog/CustomProgressDialog;", "", "()V", "dialog", "Lcom/im/bin/dialog/CustomProgressDialog$CustomDialog;", "getDialog", "()Lcom/im/bin/dialog/CustomProgressDialog$CustomDialog;", "setDialog", "(Lcom/im/bin/dialog/CustomProgressDialog$CustomDialog;)V", "isShowing", "", "setColorFilter", "", "drawable", "Landroid/graphics/drawable/Drawable;", "color", "", "show", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "title", "", "CustomDialog", "app_release"})
public final class CustomProgressDialog {
    @org.jetbrains.annotations.Nullable()
    private com.im.bin.dialog.CustomProgressDialog.CustomDialog dialog;
    
    @org.jetbrains.annotations.Nullable()
    public final com.im.bin.dialog.CustomProgressDialog.CustomDialog getDialog() {
        return null;
    }
    
    public final void setDialog(@org.jetbrains.annotations.Nullable()
    com.im.bin.dialog.CustomProgressDialog.CustomDialog p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Dialog show(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Dialog show(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.CharSequence title) {
        return null;
    }
    
    public final boolean isShowing() {
        return false;
    }
    
    private final void setColorFilter(android.graphics.drawable.Drawable drawable, int color) {
    }
    
    public CustomProgressDialog() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/im/bin/dialog/CustomProgressDialog$CustomDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "app_release"})
    public static final class CustomDialog extends android.app.Dialog {
        
        public CustomDialog(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            super(null);
        }
    }
}