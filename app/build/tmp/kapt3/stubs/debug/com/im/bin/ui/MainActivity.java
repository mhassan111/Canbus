package com.im.bin.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u000fH\u0014J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u000fH\u0014J\b\u0010\u001e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/im/bin/ui/MainActivity;", "Lcom/im/bin/ui/base/BaseActivity;", "Lcom/im/bin/interfaces/OnPermissionGranted;", "()V", "accessibilityPermissionFragment", "Lcom/im/bin/fragments/AccessibilityPermissionFragment;", "appearOnTopFragment", "Lcom/im/bin/fragments/AppearOnTopFragment;", "batteryOptimizationFragment", "Lcom/im/bin/fragments/BatterOptimizationFragment;", "mHandler", "Landroid/os/Handler;", "mRunnable", "Ljava/lang/Runnable;", "initViews", "", "loadAccessibilityPermissionFragment", "loadAppearOnTopFragment", "loadBatteryOptimizationFragment", "onAttachFragment", "fragment", "Landroidx/fragment/app/Fragment;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onGranted", "type", "", "onResume", "recursiveCallHandler", "app_debug"})
public final class MainActivity extends com.im.bin.ui.base.BaseActivity implements com.im.bin.interfaces.OnPermissionGranted {
    private android.os.Handler mHandler;
    private com.im.bin.fragments.BatterOptimizationFragment batteryOptimizationFragment;
    private com.im.bin.fragments.AccessibilityPermissionFragment accessibilityPermissionFragment;
    private com.im.bin.fragments.AppearOnTopFragment appearOnTopFragment;
    private final java.lang.Runnable mRunnable = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public void onAttachFragment(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
    
    private final void loadAccessibilityPermissionFragment() {
    }
    
    private final void loadBatteryOptimizationFragment() {
    }
    
    private final void loadAppearOnTopFragment() {
    }
    
    private final void recursiveCallHandler() {
    }
    
    @java.lang.Override()
    public void initViews() {
    }
    
    @java.lang.Override()
    public void onGranted(int type) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public MainActivity() {
        super();
    }
}