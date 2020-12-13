package com.im.bin.ui.base;

import java.lang.System;

/**
 * <p>
 * BaseActivity is base class For all the activities. This class contains all base methods and toolbar initialization for all screens.
 * It also initialize the toolbar menu and other base components.
 * </p>
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\'H\u0016J\b\u0010(\u001a\u00020)H\u0004J\u0006\u0010*\u001a\u00020)J\b\u0010+\u001a\u00020)H&J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\u0012\u00100\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u0010\u00101\u001a\u00020)2\u0006\u00102\u001a\u00020\u001cH\u0004J\b\u00103\u001a\u00020)H\u0002J\u0010\u00104\u001a\u00020)2\u0006\u00105\u001a\u000206H\u0004J\b\u00107\u001a\u00020)H\u0004J\u0006\u00108\u001a\u00020)R$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0084.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X\u0084.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00069"}, d2 = {"Lcom/im/bin/ui/base/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Ldagger/android/HasAndroidInjector;", "()V", "androidInjector", "Ldagger/android/DispatchingAndroidInjector;", "", "getAndroidInjector", "()Ldagger/android/DispatchingAndroidInjector;", "setAndroidInjector", "(Ldagger/android/DispatchingAndroidInjector;)V", "backUpChats", "Landroid/view/MenuItem;", "buyPremium", "delete", "deleteChats", "logOut", "mProgressBar", "Landroid/widget/ProgressBar;", "getMProgressBar", "()Landroid/widget/ProgressBar;", "setMProgressBar", "(Landroid/widget/ProgressBar;)V", "refresh", "refreshChats", "removeAds", "searchItem", "searchListener", "Lcom/im/bin/interfaces/OnSearchFilter;", "settings", "toolBarTitle", "Landroid/widget/TextView;", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "getToolbar", "()Landroidx/appcompat/widget/Toolbar;", "setToolbar", "(Landroidx/appcompat/widget/Toolbar;)V", "upload", "Ldagger/android/AndroidInjector;", "displayHomeButton", "", "hideProgressBar", "initViews", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onPrepareOptionsMenu", "setOnSearchQueryListener", "listener", "setOptionMenuItems", "setToolBarTitle", "title", "", "setUpToolBar", "showProgressBar", "app_release"})
public abstract class BaseActivity extends androidx.appcompat.app.AppCompatActivity implements dagger.android.HasAndroidInjector {
    @org.jetbrains.annotations.NotNull()
    protected androidx.appcompat.widget.Toolbar toolbar;
    private android.widget.TextView toolBarTitle;
    @org.jetbrains.annotations.NotNull()
    protected android.widget.ProgressBar mProgressBar;
    private android.view.MenuItem removeAds;
    private android.view.MenuItem delete;
    private android.view.MenuItem settings;
    private android.view.MenuItem logOut;
    private android.view.MenuItem upload;
    private android.view.MenuItem refresh;
    private android.view.MenuItem backUpChats;
    private android.view.MenuItem refreshChats;
    private android.view.MenuItem deleteChats;
    private android.view.MenuItem searchItem;
    private android.view.MenuItem buyPremium;
    private com.im.bin.interfaces.OnSearchFilter searchListener;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public dagger.android.DispatchingAndroidInjector<java.lang.Object> androidInjector;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final androidx.appcompat.widget.Toolbar getToolbar() {
        return null;
    }
    
    protected final void setToolbar(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.widget.Toolbar p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final android.widget.ProgressBar getMProgressBar() {
        return null;
    }
    
    protected final void setMProgressBar(@org.jetbrains.annotations.NotNull()
    android.widget.ProgressBar p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final dagger.android.DispatchingAndroidInjector<java.lang.Object> getAndroidInjector() {
        return null;
    }
    
    public final void setAndroidInjector(@org.jetbrains.annotations.NotNull()
    dagger.android.DispatchingAndroidInjector<java.lang.Object> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public dagger.android.AndroidInjector<java.lang.Object> androidInjector() {
        return null;
    }
    
    /**
     * Use this method to initialize view components.
     */
    public abstract void initViews();
    
    /**
     * Its common use a toolbar within activity, if it exists in the
     * layout this will be configured
     */
    protected final void setUpToolBar() {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onPrepareOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    protected final void setOnSearchQueryListener(@org.jetbrains.annotations.NotNull()
    com.im.bin.interfaces.OnSearchFilter listener) {
    }
    
    private final void setOptionMenuItems() {
    }
    
    protected final void displayHomeButton() {
    }
    
    public final void showProgressBar() {
    }
    
    public final void hideProgressBar() {
    }
    
    protected final void setToolBarTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    public BaseActivity() {
        super();
    }
}