package com.im.bin.databinding;
import com.im.bin.R;
import com.im.bin.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemWhatsAppPhotoImpl extends ItemWhatsAppPhoto implements com.im.bin.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.image, 3);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemWhatsAppPhotoImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ItemWhatsAppPhotoImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[0]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[2]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.FrameLayout) bindings[1]
            );
        this.cardView.setTag(null);
        this.date.setTag(null);
        this.imageLayout.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.im.bin.generated.callback.OnClickListener(this, 1);
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
        if (BR.photo == variableId) {
            setPhoto((com.im.bin.db.entities.WhatsAppMedia) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.im.bin.viewModel.WhatsAppPhotosViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPhoto(@Nullable com.im.bin.db.entities.WhatsAppMedia Photo) {
        this.mPhoto = Photo;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.photo);
        super.requestRebind();
    }
    public void setViewModel(@Nullable com.im.bin.viewModel.WhatsAppPhotosViewModel ViewModel) {
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
        java.lang.String photoMediaDate = null;
        com.im.bin.db.entities.WhatsAppMedia photo = mPhoto;
        com.im.bin.viewModel.WhatsAppPhotosViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x5L) != 0) {



                if (photo != null) {
                    // read photo.mediaDate
                    photoMediaDate = photo.getMediaDate();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.date, photoMediaDate);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.imageLayout.setOnClickListener(mCallback1);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // photo
        com.im.bin.db.entities.WhatsAppMedia photo = mPhoto;
        // viewModel
        com.im.bin.viewModel.WhatsAppPhotosViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {



            viewModel.selectWhatsAppMedia(photo);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): photo
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}