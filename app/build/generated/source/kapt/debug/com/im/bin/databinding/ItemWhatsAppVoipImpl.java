package com.im.bin.databinding;
import com.im.bin.R;
import com.im.bin.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemWhatsAppVoipImpl extends ItemWhatsAppVoip  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.voipLayout, 5);
        sViewsWithIds.put(R.id.info, 6);
        sViewsWithIds.put(R.id.menu_layout, 7);
        sViewsWithIds.put(R.id.option_menu, 8);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemWhatsAppVoipImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private ItemWhatsAppVoipImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[0]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[7]
            , (android.widget.ImageView) bindings[8]
            , (com.im.bin.customViews.RobotoLightTextView) bindings[3]
            , (com.im.bin.customViews.RobotoLightTextView) bindings[4]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[2]
            );
        this.cardView.setTag(null);
        this.voipDate.setTag(null);
        this.voipDuration.setTag(null);
        this.voipIcon.setTag(null);
        this.voipName.setTag(null);
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
        if (BR.voip == variableId) {
            setVoip((com.im.bin.db.entities.VoipCall) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.im.bin.viewModel.WhatsAppVoipViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVoip(@Nullable com.im.bin.db.entities.VoipCall Voip) {
        this.mVoip = Voip;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.voip);
        super.requestRebind();
    }
    public void setViewModel(@Nullable com.im.bin.viewModel.WhatsAppVoipViewModel ViewModel) {
        this.mViewModel = ViewModel;
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
        java.lang.String dataBindingConverterConvertLongToStringVoipVoipDuration = null;
        java.lang.String voipVoipDateTime = null;
        com.im.bin.db.entities.VoipCall voip = mVoip;
        java.lang.String voipVoipName = null;
        java.lang.String voipVoipDirection = null;
        java.lang.String dataBindingConverterGetVoipCallIconContextVoipVoipDirection = null;
        long voipVoipDuration = 0;

        if ((dirtyFlags & 0x5L) != 0) {



                if (voip != null) {
                    // read voip.voipDateTime
                    voipVoipDateTime = voip.getVoipDateTime();
                    // read voip.voipName
                    voipVoipName = voip.getVoipName();
                    // read voip.voipDirection
                    voipVoipDirection = voip.getVoipDirection();
                    // read voip.voipDuration
                    voipVoipDuration = voip.getVoipDuration();
                }


                // read DataBindingConverter.getVoipCallIcon(context, voip.voipDirection)
                dataBindingConverterGetVoipCallIconContextVoipVoipDirection = com.im.bin.appUtils.DataBindingConverter.getVoipCallIcon(getRoot().getContext(), voipVoipDirection);
                // read DataBindingConverter.convertLongToString(voip.voipDuration)
                dataBindingConverterConvertLongToStringVoipVoipDuration = com.im.bin.appUtils.DataBindingConverter.convertLongToString(voipVoipDuration);
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.voipDate, voipVoipDateTime);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.voipDuration, dataBindingConverterConvertLongToStringVoipVoipDuration);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.voipIcon, dataBindingConverterGetVoipCallIconContextVoipVoipDirection);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.voipName, voipVoipName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): voip
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}