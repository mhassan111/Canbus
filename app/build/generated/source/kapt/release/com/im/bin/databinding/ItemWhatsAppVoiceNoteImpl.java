package com.im.bin.databinding;
import com.im.bin.R;
import com.im.bin.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemWhatsAppVoiceNoteImpl extends ItemWhatsAppVoiceNote implements com.im.bin.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.headphone_icon, 4);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemWhatsAppVoiceNoteImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private ItemWhatsAppVoiceNoteImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[0]
            , (com.im.bin.customViews.RobotoLightTextView) bindings[3]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[4]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[2]
            , (android.widget.RelativeLayout) bindings[1]
            );
        this.cardView.setTag(null);
        this.date.setTag(null);
        this.name.setTag(null);
        this.voiceLayout.setTag(null);
        setRootTag(root);
        // listeners
        mCallback5 = new com.im.bin.generated.callback.OnClickListener(this, 1);
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
            setViewModel((com.im.bin.viewModel.WhatsAppPhotosViewModel) variable);
        }
        else if (BR.voiceNote == variableId) {
            setVoiceNote((com.im.bin.db.entities.WhatsAppMedia) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.im.bin.viewModel.WhatsAppPhotosViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }
    public void setVoiceNote(@Nullable com.im.bin.db.entities.WhatsAppMedia VoiceNote) {
        this.mVoiceNote = VoiceNote;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.voiceNote);
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
        java.lang.String voiceNoteMediaName = null;
        java.lang.String voiceNoteMediaDate = null;
        com.im.bin.viewModel.WhatsAppPhotosViewModel viewModel = mViewModel;
        com.im.bin.db.entities.WhatsAppMedia voiceNote = mVoiceNote;

        if ((dirtyFlags & 0x6L) != 0) {



                if (voiceNote != null) {
                    // read voiceNote.mediaName
                    voiceNoteMediaName = voiceNote.getMediaName();
                    // read voiceNote.mediaDate
                    voiceNoteMediaDate = voiceNote.getMediaDate();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.date, voiceNoteMediaDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.name, voiceNoteMediaName);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.voiceLayout.setOnClickListener(mCallback5);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.im.bin.viewModel.WhatsAppPhotosViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;
        // voiceNote
        com.im.bin.db.entities.WhatsAppMedia voiceNote = mVoiceNote;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {



            viewModel.selectWhatsAppMedia(voiceNote);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): voiceNote
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}