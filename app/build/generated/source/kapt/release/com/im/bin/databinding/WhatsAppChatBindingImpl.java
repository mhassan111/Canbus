package com.im.bin.databinding;
import com.im.bin.R;
import com.im.bin.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class WhatsAppChatBindingImpl extends WhatsAppChatBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.loadingText, 3);
        sViewsWithIds.put(R.id.refresh_data, 4);
        sViewsWithIds.put(R.id.howItWorks, 5);
        sViewsWithIds.put(R.id.howItWorksText, 6);
        sViewsWithIds.put(R.id.swipe_refresh_layout, 7);
        sViewsWithIds.put(R.id.recycler_view, 8);
        sViewsWithIds.put(R.id.ad_view_container, 9);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public WhatsAppChatBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private WhatsAppChatBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.FrameLayout) bindings[9]
            , (android.widget.RelativeLayout) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[1]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[5]
            , (com.im.bin.customViews.RobotoLightTextView) bindings[6]
            , (com.im.bin.customViews.RobotoMediumTextView) bindings[3]
            , (androidx.recyclerview.widget.RecyclerView) bindings[8]
            , (android.widget.ImageView) bindings[4]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[7]
            );
        this.dataSection.setTag(null);
        this.errorSection.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
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
            setViewModel((com.im.bin.viewModel.WhatsAppChatViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
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
            case 0 :
                return onChangeViewModelLoadDataSection((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelLoadDataSection(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelLoadDataSection, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelLoadDataSectionGetValue = false;
        int viewModelLoadDataSectionViewGONEViewVISIBLE = 0;
        int viewModelLoadDataSectionViewVISIBLEViewGONE = 0;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelLoadDataSection = null;
        com.im.bin.viewModel.WhatsAppChatViewModel viewModel = mViewModel;
        java.lang.Boolean viewModelLoadDataSectionGetValue = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.loadDataSection
                    viewModelLoadDataSection = viewModel.getLoadDataSection();
                }
                updateLiveDataRegistration(0, viewModelLoadDataSection);


                if (viewModelLoadDataSection != null) {
                    // read viewModel.loadDataSection.getValue()
                    viewModelLoadDataSectionGetValue = viewModelLoadDataSection.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.loadDataSection.getValue())
                androidxDatabindingViewDataBindingSafeUnboxViewModelLoadDataSectionGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelLoadDataSectionGetValue);
            if((dirtyFlags & 0x7L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxViewModelLoadDataSectionGetValue) {
                        dirtyFlags |= 0x10L;
                        dirtyFlags |= 0x40L;
                }
                else {
                        dirtyFlags |= 0x8L;
                        dirtyFlags |= 0x20L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.loadDataSection.getValue()) ? View.GONE : View.VISIBLE
                viewModelLoadDataSectionViewGONEViewVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelLoadDataSectionGetValue) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.loadDataSection.getValue()) ? View.VISIBLE : View.GONE
                viewModelLoadDataSectionViewVISIBLEViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelLoadDataSectionGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.dataSection.setVisibility(viewModelLoadDataSectionViewVISIBLEViewGONE);
            this.errorSection.setVisibility(viewModelLoadDataSectionViewGONEViewVISIBLE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.loadDataSection
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
        flag 3 (0x4L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.loadDataSection.getValue()) ? View.GONE : View.VISIBLE
        flag 4 (0x5L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.loadDataSection.getValue()) ? View.GONE : View.VISIBLE
        flag 5 (0x6L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.loadDataSection.getValue()) ? View.VISIBLE : View.GONE
        flag 6 (0x7L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.loadDataSection.getValue()) ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}