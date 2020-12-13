package com.im.bin.databinding;
import com.im.bin.R;
import com.im.bin.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemIncomingMsgBindingImpl extends ItemIncomingMsgBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemIncomingMsgBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ItemIncomingMsgBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.im.bin.customViews.RobotoLightTextView) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[1]
            );
        this.date.setTag(null);
        this.incomingMsgLayout.setTag(null);
        this.text.setTag(null);
        setRootTag(root);
        // listeners
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
        if (BR.viewModel == variableId) {
            setViewModel((com.im.bin.viewModel.ChatConversationViewModel) variable);
        }
        else if (BR.imMessage == variableId) {
            setImMessage((com.im.bin.models.IMMessage) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.im.bin.viewModel.ChatConversationViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }
    public void setImMessage(@Nullable com.im.bin.models.IMMessage ImMessage) {
        this.mImMessage = ImMessage;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.imMessage);
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
        java.lang.String imMessageMessage = null;
        java.lang.String imMessageMessageDatetime = null;
        com.im.bin.models.IMMessage imMessage = mImMessage;

        if ((dirtyFlags & 0x6L) != 0) {



                if (imMessage != null) {
                    // read imMessage.message
                    imMessageMessage = imMessage.getMessage();
                    // read imMessage.messageDatetime
                    imMessageMessageDatetime = imMessage.getMessageDatetime();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.date, imMessageMessageDatetime);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.text, imMessageMessage);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): imMessage
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}