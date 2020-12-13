package com.im.bin.fragments.im;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0002J\"\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u0017H\u0016J&\u0010$\u001a\u0004\u0018\u00010\u001b2\u0006\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0004H\u0016J\b\u0010.\u001a\u00020\u0017H\u0002J\u0010\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u000201H\u0016J\u000e\u00102\u001a\u00020\u00172\u0006\u00103\u001a\u00020\fJ\b\u00104\u001a\u00020\u0017H\u0002J\b\u00105\u001a\u00020\u0017H\u0002J\b\u00106\u001a\u00020\u0017H\u0002J\b\u00107\u001a\u00020\u0017H\u0002J\b\u00108\u001a\u00020\u0017H\u0002J\b\u00109\u001a\u00020\u0017H\u0002J\b\u0010:\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006<"}, d2 = {"Lcom/im/bin/fragments/im/WhatsAppMediaFragment;", "Lcom/im/bin/ui/base/BaseFragment;", "()V", "backUpChats", "Landroid/view/MenuItem;", "binding", "Lcom/im/bin/databinding/WhatsAppPhotosBinding;", "mAdapter", "Lcom/im/bin/adapter/WhatsAppMediaAdapter;", "mContext", "Landroid/content/Context;", "mediaType", "", "refresh", "refreshChats", "upload", "whatsAppPhotoViewModel", "Lcom/im/bin/viewModel/WhatsAppPhotosViewModel;", "getWhatsAppPhotoViewModel", "()Lcom/im/bin/viewModel/WhatsAppPhotosViewModel;", "whatsAppPhotoViewModel$delegate", "Lkotlin/Lazy;", "deleteAllMedia", "", "hideDataSection", "initViews", "view", "Landroid/view/View;", "loadPhotos", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreateFragment", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "onPhotosLoadingFinished", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onSearchQuery", "query", "setHowItWorksText", "setMediaListObserver", "setOnRefreshDataListener", "setOnRefreshListener", "setSelectedMediaObserver", "setWhatsAppMediaListObserver", "showDataSection", "Companion", "app_release"})
public final class WhatsAppMediaFragment extends com.im.bin.ui.base.BaseFragment {
    private com.im.bin.databinding.WhatsAppPhotosBinding binding;
    private android.content.Context mContext;
    private com.im.bin.adapter.WhatsAppMediaAdapter mAdapter;
    private java.lang.String mediaType;
    private android.view.MenuItem upload;
    private android.view.MenuItem refresh;
    private android.view.MenuItem backUpChats;
    private android.view.MenuItem refreshChats;
    private final kotlin.Lazy whatsAppPhotoViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = null;
    private static final java.lang.String EXTRA_MEDIA_TYPE = "EXTRA_MEDIA_TYPE";
    public static final com.im.bin.fragments.im.WhatsAppMediaFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final com.im.bin.viewModel.WhatsAppPhotosViewModel getWhatsAppPhotoViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreateFragment() {
    }
    
    @java.lang.Override()
    public void onPrepareOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void deleteAllMedia() {
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
    
    private final void setHowItWorksText() {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void setOnRefreshListener() {
    }
    
    private final void setOnRefreshDataListener() {
    }
    
    private final void setWhatsAppMediaListObserver() {
    }
    
    private final void setMediaListObserver() {
    }
    
    private final void setSelectedMediaObserver() {
    }
    
    public final void onSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    private final void loadPhotos() {
    }
    
    private final void onPhotosLoadingFinished() {
    }
    
    private final void showDataSection() {
    }
    
    private final void hideDataSection() {
    }
    
    public WhatsAppMediaFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/im/bin/fragments/im/WhatsAppMediaFragment$Companion;", "", "()V", "EXTRA_MEDIA_TYPE", "", "TAG", "getTAG", "()Ljava/lang/String;", "newInstance", "Lcom/im/bin/fragments/im/WhatsAppMediaFragment;", "mediaType", "app_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTAG() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.fragments.im.WhatsAppMediaFragment newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String mediaType) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}