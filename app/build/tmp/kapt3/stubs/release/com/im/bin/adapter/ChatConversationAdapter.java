package com.im.bin.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001!B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0014J\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\u0018\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J \u0010\u001b\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0014H\u0016J\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\u000e\u0010 \u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/im/bin/adapter/ChatConversationAdapter;", "Lcom/im/bin/recyclerView/DataBoundListAdapter;", "Lcom/im/bin/models/IMMessage;", "Lcom/im/bin/recyclerView/ItemTouchCallbacListener$ActionCompletionContract;", "viewModel", "Lcom/im/bin/viewModel/ChatConversationViewModel;", "(Lcom/im/bin/viewModel/ChatConversationViewModel;)V", "touchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "bind", "", "holder", "Lcom/im/bin/recyclerView/DataBoundViewHolder;", "binding", "Landroidx/databinding/ViewDataBinding;", "item", "createBinding", "parent", "Landroid/view/ViewGroup;", "viewType", "", "getItemViewType", "position", "onCreateViewHolder", "onRowClear", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onRowMoved", "fromPosition", "toPosition", "onRowSelected", "onRowSwiped", "setTouchHelper", "Companion", "app_release"})
public final class ChatConversationAdapter extends com.im.bin.recyclerView.DataBoundListAdapter<com.im.bin.models.IMMessage> implements com.im.bin.recyclerView.ItemTouchCallbacListener.ActionCompletionContract {
    private androidx.recyclerview.widget.ItemTouchHelper touchHelper;
    private final com.im.bin.viewModel.ChatConversationViewModel viewModel = null;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    public static final com.im.bin.adapter.ChatConversationAdapter.Companion Companion = null;
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    public final void setTouchHelper(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.ItemTouchHelper touchHelper) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.im.bin.recyclerView.DataBoundViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
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
    com.im.bin.models.IMMessage item) {
    }
    
    @java.lang.Override()
    public void onRowSwiped(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, int position) {
    }
    
    @java.lang.Override()
    public void onRowMoved(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, int fromPosition, int toPosition) {
    }
    
    @java.lang.Override()
    public void onRowSelected(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder) {
    }
    
    @java.lang.Override()
    public void onRowClear(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder) {
    }
    
    public ChatConversationAdapter(@org.jetbrains.annotations.NotNull()
    com.im.bin.viewModel.ChatConversationViewModel viewModel) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/im/bin/adapter/ChatConversationAdapter$Companion;", "", "()V", "VIEW_TYPE_MESSAGE_RECEIVED", "", "VIEW_TYPE_MESSAGE_SENT", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}