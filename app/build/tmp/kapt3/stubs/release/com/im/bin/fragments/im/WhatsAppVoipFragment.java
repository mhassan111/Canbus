package com.im.bin.fragments.im;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 22\u00020\u0001:\u00012B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0018H\u0002J\u0016\u0010\u001d\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0002J\b\u0010!\u001a\u00020\u0018H\u0016J&\u0010\"\u001a\u0004\u0018\u00010\u001b2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\u000e\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u0006J\b\u0010+\u001a\u00020\u0018H\u0002J\b\u0010,\u001a\u00020\u0018H\u0002J\b\u0010-\u001a\u00020\u0018H\u0002J\b\u0010.\u001a\u00020\u0018H\u0002J\b\u0010/\u001a\u00020\u0018H\u0002J\b\u00100\u001a\u00020\u0018H\u0002J\b\u00101\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014\u00a8\u00063"}, d2 = {"Lcom/im/bin/fragments/im/WhatsAppVoipFragment;", "Lcom/im/bin/ui/base/BaseFragment;", "()V", "binding", "Lcom/im/bin/databinding/WhatsAppVoipBinding;", "imType", "", "mAdapter", "Lcom/im/bin/adapter/WhatsAppVoipAdapter;", "mContext", "Landroid/content/Context;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "whatsAppVoipViewModel", "Lcom/im/bin/viewModel/WhatsAppVoipViewModel;", "getWhatsAppVoipViewModel", "()Lcom/im/bin/viewModel/WhatsAppVoipViewModel;", "whatsAppVoipViewModel$delegate", "Lkotlin/Lazy;", "hideDataSection", "", "initViews", "view", "Landroid/view/View;", "loadVoipCalls", "onCallsListUpdated", "calls", "", "Lcom/im/bin/db/entities/VoipCall;", "onCreateFragment", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onSearchQuery", "query", "setCallsObserver", "setFilteredCallsObserver", "setOnDataRefreshListener", "setOnRefreshListener", "setSelectedChatObserver", "showDataSection", "stopRefreshing", "Companion", "app_release"})
public final class WhatsAppVoipFragment extends com.im.bin.ui.base.BaseFragment {
    private com.im.bin.databinding.WhatsAppVoipBinding binding;
    private android.content.Context mContext;
    private com.im.bin.adapter.WhatsAppVoipAdapter mAdapter;
    private java.lang.String imType;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private final kotlin.Lazy whatsAppVoipViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = null;
    private static final java.lang.String EXTRA_IM_TYPE = "EXTRA_IM_TYPE";
    public static final com.im.bin.fragments.im.WhatsAppVoipFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    private final com.im.bin.viewModel.WhatsAppVoipViewModel getWhatsAppVoipViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreateFragment() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void initViews(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void setOnRefreshListener() {
    }
    
    private final void loadVoipCalls() {
    }
    
    private final void stopRefreshing() {
    }
    
    private final void setOnDataRefreshListener() {
    }
    
    private final void setCallsObserver() {
    }
    
    private final void onCallsListUpdated(java.util.List<com.im.bin.db.entities.VoipCall> calls) {
    }
    
    private final void setFilteredCallsObserver() {
    }
    
    private final void setSelectedChatObserver() {
    }
    
    public final void onSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    private final void showDataSection() {
    }
    
    private final void hideDataSection() {
    }
    
    public WhatsAppVoipFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\f"}, d2 = {"Lcom/im/bin/fragments/im/WhatsAppVoipFragment$Companion;", "", "()V", "EXTRA_IM_TYPE", "", "TAG", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/im/bin/fragments/im/WhatsAppVoipFragment;", "imType", "Lcom/im/bin/enums/IMType;", "app_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTAG() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.fragments.im.WhatsAppVoipFragment newInstance(@org.jetbrains.annotations.NotNull()
        com.im.bin.enums.IMType imType) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}