package com.im.bin.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0002H\u0014J\u0018\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/im/bin/adapter/WhatsAppChatAdapter;", "Lcom/im/bin/recyclerView/DataBoundListAdapter;", "Lcom/im/bin/models/Chat;", "viewModel", "Lcom/im/bin/viewModel/WhatsAppChatViewModel;", "(Lcom/im/bin/viewModel/WhatsAppChatViewModel;)V", "bind", "", "holder", "Lcom/im/bin/recyclerView/DataBoundViewHolder;", "binding", "Landroidx/databinding/ViewDataBinding;", "item", "createBinding", "parent", "Landroid/view/ViewGroup;", "viewType", "", "app_release"})
public final class WhatsAppChatAdapter extends com.im.bin.recyclerView.DataBoundListAdapter<com.im.bin.models.Chat> {
    private final com.im.bin.viewModel.WhatsAppChatViewModel viewModel = null;
    
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
    com.im.bin.models.Chat item) {
    }
    
    public WhatsAppChatAdapter(@org.jetbrains.annotations.NotNull()
    com.im.bin.viewModel.WhatsAppChatViewModel viewModel) {
        super(null);
    }
}