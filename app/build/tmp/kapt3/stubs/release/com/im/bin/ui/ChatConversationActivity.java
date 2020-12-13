package com.im.bin.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/im/bin/ui/ChatConversationActivity;", "Lcom/im/bin/ui/base/BaseActivity;", "()V", "chat", "Lcom/im/bin/models/Chat;", "imType", "", "mChatConversationFragment", "Lcom/im/bin/fragments/im/ChatConversationFragment;", "initViews", "", "loadIMFragment", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSearchQuery", "query", "onSupportNavigateUp", "", "Companion", "app_release"})
public final class ChatConversationActivity extends com.im.bin.ui.base.BaseActivity {
    private com.im.bin.fragments.im.ChatConversationFragment mChatConversationFragment;
    private java.lang.String imType;
    private com.im.bin.models.Chat chat;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_IM_TYPE = "EXTRA_IM_TYPE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_CHAT = "EXTRA_CHAT";
    public static final com.im.bin.ui.ChatConversationActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void onSearchQuery(java.lang.String query) {
    }
    
    @java.lang.Override()
    public void initViews() {
    }
    
    private final void loadIMFragment() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    public ChatConversationActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/im/bin/ui/ChatConversationActivity$Companion;", "", "()V", "EXTRA_CHAT", "", "EXTRA_IM_TYPE", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}