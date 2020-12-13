package com.im.bin.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\u0012\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0015J\b\u0010\u0015\u001a\u00020\u0010H\u0014J\b\u0010\u0016\u001a\u00020\u0010H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/im/bin/ui/PremiumFeaturesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "getBillingClient", "()Lcom/android/billingclient/api/BillingClient;", "billingClient$delegate", "Lkotlin/Lazy;", "progressDialog", "Lcom/im/bin/dialog/CustomProgressDialog;", "purchasesUpdatedListener", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "checkAppPurchaseStatus", "", "hideProgressDialog", "", "launchAppBillingClient", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "showProgressDialog", "app_release"})
public final class PremiumFeaturesActivity extends androidx.appcompat.app.AppCompatActivity {
    private final com.im.bin.dialog.CustomProgressDialog progressDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy billingClient$delegate = null;
    private final com.android.billingclient.api.PurchasesUpdatedListener purchasesUpdatedListener = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.android.billingclient.api.BillingClient getBillingClient() {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showProgressDialog() {
    }
    
    private final void hideProgressDialog() {
    }
    
    private final void launchAppBillingClient() {
    }
    
    private final boolean checkAppPurchaseStatus(com.android.billingclient.api.BillingClient billingClient) {
        return false;
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public PremiumFeaturesActivity() {
        super();
    }
}