package com.im.bin.fragments.im;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 ,2\u00020\u0001:\u0002,-B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0006H\u0016J\u0012\u0010\u001b\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J&\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u000e\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&J\u001a\u0010\'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010)\u001a\u00020\u0017H\u0003J\u000e\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020&R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/im/bin/fragments/im/WhatsAppFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adContainerView", "Landroid/widget/FrameLayout;", "mContext", "Landroid/content/Context;", "mFragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "mViewPager", "Landroidx/viewpager/widget/ViewPager;", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "whatsAppChatFragment", "Lcom/im/bin/fragments/im/WhatsAppChatFragment;", "whatsAppPhotosFragment", "Lcom/im/bin/fragments/im/WhatsAppMediaFragment;", "whatsAppStatusesFragment", "whatsAppVideosFragment", "whatsAppVoiceNotesFragment", "whatsAppVoipFragment", "Lcom/im/bin/fragments/im/WhatsAppVoipFragment;", "addIconsAndTitles", "", "addViewPagerPageSelectedListener", "onAttach", "context", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onSearchQuery", "query", "", "onViewCreated", "view", "setUpTabIcons", "setViewPagerCurrentPosition", "title", "Companion", "ViewPagerAdapter", "app_debug"})
public final class WhatsAppFragment extends androidx.fragment.app.Fragment {
    private androidx.fragment.app.FragmentActivity mFragmentActivity;
    private androidx.viewpager.widget.ViewPager mViewPager;
    private com.google.android.material.tabs.TabLayout tabLayout;
    private android.content.Context mContext;
    private android.widget.FrameLayout adContainerView;
    private com.im.bin.fragments.im.WhatsAppChatFragment whatsAppChatFragment;
    private com.im.bin.fragments.im.WhatsAppVoipFragment whatsAppVoipFragment;
    private com.im.bin.fragments.im.WhatsAppMediaFragment whatsAppPhotosFragment;
    private com.im.bin.fragments.im.WhatsAppMediaFragment whatsAppVideosFragment;
    private com.im.bin.fragments.im.WhatsAppMediaFragment whatsAppVoiceNotesFragment;
    private com.im.bin.fragments.im.WhatsAppMediaFragment whatsAppStatusesFragment;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = null;
    private static final int noOfFragments = 6;
    private static final java.util.List<java.lang.String> icons = null;
    private static final java.util.List<java.lang.String> titles = null;
    public static final com.im.bin.fragments.im.WhatsAppFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void onSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void setViewPagerCurrentPosition(@org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    private final void addIconsAndTitles() {
    }
    
    @android.annotation.SuppressLint(value = {"InflateParams"})
    private final void setUpTabIcons() {
    }
    
    private final void addViewPagerPageSelectedListener() {
    }
    
    public WhatsAppFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\t\u001a\u00020\u0006H\u0016\u00a8\u0006\f"}, d2 = {"Lcom/im/bin/fragments/im/WhatsAppFragment$ViewPagerAdapter;", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "(Lcom/im/bin/fragments/im/WhatsAppFragment;Landroidx/fragment/app/FragmentManager;)V", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", "position", "getPageTitle", "", "app_debug"})
    final class ViewPagerAdapter extends androidx.fragment.app.FragmentStatePagerAdapter {
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public androidx.fragment.app.Fragment getItem(int position) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.CharSequence getPageTitle(int position) {
            return null;
        }
        
        @java.lang.Override()
        public int getCount() {
            return 0;
        }
        
        public ViewPagerAdapter(@org.jetbrains.annotations.NotNull()
        androidx.fragment.app.FragmentManager fragmentManager) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/im/bin/fragments/im/WhatsAppFragment$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "icons", "", "noOfFragments", "", "titles", "newInstance", "Lcom/im/bin/fragments/im/WhatsAppFragment;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTAG() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.im.bin.fragments.im.WhatsAppFragment newInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}