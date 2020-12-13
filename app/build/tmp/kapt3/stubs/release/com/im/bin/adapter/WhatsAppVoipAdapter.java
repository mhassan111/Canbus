package com.im.bin.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0014J\u0018\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0002H\u0002J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/im/bin/adapter/WhatsAppVoipAdapter;", "Lcom/im/bin/recyclerView/DataBoundListAdapter;", "Lcom/im/bin/db/entities/VoipCall;", "mContext", "Landroid/content/Context;", "viewModel", "Lcom/im/bin/viewModel/WhatsAppVoipViewModel;", "(Landroid/content/Context;Lcom/im/bin/viewModel/WhatsAppVoipViewModel;)V", "bind", "", "holder", "Lcom/im/bin/recyclerView/DataBoundViewHolder;", "binding", "Landroidx/databinding/ViewDataBinding;", "item", "createBinding", "parent", "Landroid/view/ViewGroup;", "viewType", "", "playVoipCall", "voipCall", "shareFile", "filePath", "", "showOptionsMenu", "position", "view", "Landroid/view/View;", "app_release"})
public final class WhatsAppVoipAdapter extends com.im.bin.recyclerView.DataBoundListAdapter<com.im.bin.db.entities.VoipCall> {
    private final android.content.Context mContext = null;
    private final com.im.bin.viewModel.WhatsAppVoipViewModel viewModel = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected androidx.databinding.ViewDataBinding createBinding(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    protected void bind(@org.jetbrains.annotations.NotNull()
    com.im.bin.recyclerView.DataBoundViewHolder holder, @org.jetbrains.annotations.NotNull()
    androidx.databinding.ViewDataBinding binding, @org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.VoipCall item) {
    }
    
    private final void showOptionsMenu(int position, android.view.View view) {
    }
    
    private final void shareFile(java.lang.String filePath) {
    }
    
    private final void playVoipCall(com.im.bin.db.entities.VoipCall voipCall) {
    }
    
    public WhatsAppVoipAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext, @org.jetbrains.annotations.NotNull()
    com.im.bin.viewModel.WhatsAppVoipViewModel viewModel) {
        super(null);
    }
}