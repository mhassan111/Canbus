package com.im.bin.dialog;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/im/bin/dialog/DeleteAlertDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "titleText", "", "type", "", "customListener", "Lcom/im/bin/interfaces/CustomerAlertListener;", "(Landroid/content/Context;Ljava/lang/String;ILcom/im/bin/interfaces/CustomerAlertListener;)V", "Companion", "app_release"})
public final class DeleteAlertDialog extends android.app.Dialog {
    private final com.im.bin.interfaces.CustomerAlertListener customListener = null;
    public static final int TYPE_WHATS_APP_MEDIA = 0;
    public static final int TYPE_DELETE = 1;
    public static final com.im.bin.dialog.DeleteAlertDialog.Companion Companion = null;
    
    public DeleteAlertDialog(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String titleText, int type, @org.jetbrains.annotations.NotNull()
    com.im.bin.interfaces.CustomerAlertListener customListener) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/im/bin/dialog/DeleteAlertDialog$Companion;", "", "()V", "TYPE_DELETE", "", "TYPE_WHATS_APP_MEDIA", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}