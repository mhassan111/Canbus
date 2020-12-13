package com.im.bin.databinding;
import com.im.bin.R;
import com.im.bin.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemChatBindingImpl extends ItemChatBinding implements com.im.bin.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.chat_user, 4);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemChatBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private ItemChatBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[1]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[4]
            , (com.im.bin.customViews.RobotoLightTextView) bindings[3]
            , (com.im.bin.customViews.RobotoLightTextView) bindings[2]
            );
        this.chatLayout.setTag(null);
        this.chatName.setTag(null);
        this.date.setTag(null);
        this.lastMessage.setTag(null);
        setRootTag(root);
        // listeners
        mCallback3 = new com.im.bin.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.chat == variableId) {
            setChat((com.im.bin.models.Chat) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.im.bin.viewModel.WhatsAppChatViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setChat(@Nullable com.im.bin.models.Chat Chat) {
        this.mChat = Chat;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.chat);
        super.requestRebind();
    }
    public void setViewModel(@Nullable com.im.bin.viewModel.WhatsAppChatViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.im.bin.models.Chat chat = mChat;
        java.lang.String chatConversationName = null;
        java.lang.String chatLastMessage = null;
        com.im.bin.viewModel.WhatsAppChatViewModel viewModel = mViewModel;
        java.lang.String chatMessageDateTime = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (chat != null) {
                    // read chat.conversationName
                    chatConversationName = chat.getConversationName();
                    // read chat.lastMessage
                    chatLastMessage = chat.getLastMessage();
                    // read chat.messageDateTime
                    chatMessageDateTime = chat.getMessageDateTime();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.chatLayout.setOnClickListener(mCallback3);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.chatName, chatConversationName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.date, chatMessageDateTime);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.lastMessage, chatLastMessage);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // chat
        com.im.bin.models.Chat chat = mChat;
        // viewModel
        com.im.bin.viewModel.WhatsAppChatViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {



            viewModel.selectChat(chat);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): chat
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}