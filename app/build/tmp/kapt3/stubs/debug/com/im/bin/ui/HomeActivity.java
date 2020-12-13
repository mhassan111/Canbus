package com.im.bin.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000b\u0018\u0000 I2\u00020\u00012\u00020\u0002:\u0001IB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002J\u0010\u0010&\u001a\u00020\'2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020#H\u0016J\b\u0010+\u001a\u00020\u001fH\u0003J\b\u0010,\u001a\u00020\u001fH\u0002J\b\u0010-\u001a\u00020\u001fH\u0002J\b\u0010.\u001a\u00020\u001fH\u0016J\b\u0010/\u001a\u00020\u001fH\u0002J\b\u00100\u001a\u00020\u001fH\u0002J\b\u00101\u001a\u00020\u001fH\u0002J\b\u00102\u001a\u00020\u001fH\u0016J\u0012\u00103\u001a\u00020\u001f2\b\u00104\u001a\u0004\u0018\u000105H\u0014J\b\u00106\u001a\u00020\u001fH\u0014J\u0010\u00107\u001a\u00020\'2\u0006\u00108\u001a\u00020!H\u0016J\u0010\u00109\u001a\u00020\'2\u0006\u00108\u001a\u00020!H\u0016J-\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020)2\u000e\u0010<\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0=2\u0006\u0010>\u001a\u00020?H\u0017\u00a2\u0006\u0002\u0010@J\u0010\u0010A\u001a\u00020\u001f2\u0006\u0010B\u001a\u00020#H\u0002J\b\u0010C\u001a\u00020\u001fH\u0002J\b\u0010D\u001a\u00020\u001fH\u0002J\b\u0010E\u001a\u00020\u001fH\u0002J\b\u0010F\u001a\u00020\u001fH\u0002J\b\u0010G\u001a\u00020\u001fH\u0002J\b\u0010H\u001a\u00020\u001fH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006J"}, d2 = {"Lcom/im/bin/ui/HomeActivity;", "Lcom/im/bin/ui/base/BaseActivity;", "Lcom/google/android/material/navigation/NavigationView$OnNavigationItemSelectedListener;", "()V", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "getBillingClient", "()Lcom/android/billingclient/api/BillingClient;", "billingClient$delegate", "Lkotlin/Lazy;", "customDialogListener", "Lcom/im/bin/interfaces/CustomDialogListener;", "mDrawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "mNavigationView", "Lcom/google/android/material/navigation/NavigationView;", "mWhatsAppFragment", "Lcom/im/bin/fragments/im/WhatsAppFragment;", "progressDialog", "Lcom/im/bin/dialog/CustomProgressDialog;", "purchasesUpdatedListener", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "userRepository", "Lcom/im/bin/respository/UserRepository;", "getUserRepository", "()Lcom/im/bin/respository/UserRepository;", "setUserRepository", "(Lcom/im/bin/respository/UserRepository;)V", "voipCallRecordingFragment", "Lcom/im/bin/fragments/im/VoipCallRecordingFragment;", "addIcon", "", "menuItem", "Landroid/view/MenuItem;", "icon", "", "addMenuIcons", "buyPremiumPackage", "checkAppPurchaseStatus", "", "checkSelfPermission", "", "permission", "displayPermissionRequestDialog", "exit", "hideProgressDialog", "initViews", "launchAppBillingClient", "loadWhatsAppFragment", "logOutUser", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNavigationItemSelected", "item", "onOptionsItemSelected", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onSearchQuery", "query", "rateUs", "requestRunTimePermissions", "share", "showProgressDialog", "startForegroundServiceIfNotRunning", "startSettingsActivity", "Companion", "app_debug"})
public final class HomeActivity extends com.im.bin.ui.base.BaseActivity implements com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener {
    private androidx.drawerlayout.widget.DrawerLayout mDrawerLayout;
    private com.google.android.material.navigation.NavigationView mNavigationView;
    private com.im.bin.fragments.im.WhatsAppFragment mWhatsAppFragment;
    private com.im.bin.fragments.im.VoipCallRecordingFragment voipCallRecordingFragment;
    private final com.im.bin.dialog.CustomProgressDialog progressDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy billingClient$delegate = null;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.im.bin.respository.UserRepository userRepository;
    private final com.im.bin.interfaces.CustomDialogListener customDialogListener = null;
    private final com.android.billingclient.api.PurchasesUpdatedListener purchasesUpdatedListener = null;
    private static final int REQUEST_PERMISSION_CODE = 1000;
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    private static final java.lang.String[] permissionsArray = {"android.permission.INTERNET", "android.permission.RECEIVE_BOOT_COMPLETED", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.RECORD_AUDIO"};
    public static final com.im.bin.ui.HomeActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.android.billingclient.api.BillingClient getBillingClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.UserRepository getUserRepository() {
        return null;
    }
    
    public final void setUserRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.UserRepository p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void startForegroundServiceIfNotRunning() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void onSearchQuery(java.lang.String query) {
    }
    
    @java.lang.Override()
    public void initViews() {
    }
    
    private final void loadWhatsAppFragment() {
    }
    
    private final void addMenuIcons() {
    }
    
    private final void addIcon(android.view.MenuItem menuItem, java.lang.String icon) {
    }
    
    @java.lang.Override()
    public boolean onNavigationItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void buyPremiumPackage() {
    }
    
    private final void logOutUser() {
    }
    
    private final void startSettingsActivity() {
    }
    
    private final void rateUs() {
    }
    
    private final void share() {
    }
    
    private final void exit() {
    }
    
    private final void launchAppBillingClient() {
    }
    
    private final boolean checkAppPurchaseStatus(com.android.billingclient.api.BillingClient billingClient) {
        return false;
    }
    
    private final void showProgressDialog() {
    }
    
    private final void hideProgressDialog() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void requestRunTimePermissions() {
    }
    
    @java.lang.Override()
    public int checkSelfPermission(@org.jetbrains.annotations.NotNull()
    java.lang.String permission) {
        return 0;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    private final void displayPermissionRequestDialog() {
    }
    
    public HomeActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0083\u0004\u00a2\u0006\u0004\n\u0002\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/im/bin/ui/HomeActivity$Companion;", "", "()V", "REQUEST_PERMISSION_CODE", "", "permissionsArray", "", "", "[Ljava/lang/String;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}