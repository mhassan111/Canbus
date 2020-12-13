package com.im.bin.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010!\u001a\u00020\"H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\u0019\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0005H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u0012\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0012J\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\n0\u0012J\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0*2\u0006\u0010+\u001a\u00020\u0005J\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\n0-H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\u000e\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u00020\nJ\u000e\u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\fJ\u0014\u00102\u001a\u00020\"2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0014\u0010\u0019\u001a\u00020\"2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\n0\tR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00064"}, d2 = {"Lcom/im/bin/viewModel/WhatsAppPhotosViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "mediaType", "", "(Landroid/content/Context;Ljava/lang/String;)V", "_filteredMediaList", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/im/bin/db/entities/WhatsAppMedia;", "_loadDataSection", "", "kotlin.jvm.PlatformType", "_loadProgressBar", "_mediaList", "_selectedMedia", "filteredMediaList", "Landroidx/lifecycle/LiveData;", "loadDataSection", "getLoadDataSection", "()Landroidx/lifecycle/LiveData;", "selectedWhatsAppMedia", "whatsAppMediaList", "getWhatsAppMediaList", "setWhatsAppMediaList", "(Landroidx/lifecycle/LiveData;)V", "whatsAppMediaRepository", "Lcom/im/bin/respository/WhatsAppMediaRepository;", "getWhatsAppMediaRepository", "()Lcom/im/bin/respository/WhatsAppMediaRepository;", "setWhatsAppMediaRepository", "(Lcom/im/bin/respository/WhatsAppMediaRepository;)V", "deleteAllMedia", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMedia", "mediaId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMediaList", "getSelectedWhatsAppMedia", "performSearchQuery", "Lio/reactivex/Single;", "query", "retrieveAllPhotos", "Ljava/util/ArrayList;", "selectWhatsAppMedia", "media", "setLoadDataSection", "load", "setOriginalWhatsAppMediaList", "medias", "app_release"})
public final class WhatsAppPhotosViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private com.im.bin.respository.WhatsAppMediaRepository whatsAppMediaRepository;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> whatsAppMediaList;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> _mediaList = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> _filteredMediaList = null;
    private final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> filteredMediaList = null;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _loadDataSection;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> loadDataSection = null;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _loadProgressBar;
    private final androidx.lifecycle.MutableLiveData<com.im.bin.db.entities.WhatsAppMedia> _selectedMedia = null;
    private final androidx.lifecycle.LiveData<com.im.bin.db.entities.WhatsAppMedia> selectedWhatsAppMedia = null;
    private final android.content.Context context = null;
    private final java.lang.String mediaType = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.WhatsAppMediaRepository getWhatsAppMediaRepository() {
        return null;
    }
    
    public final void setWhatsAppMediaRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.WhatsAppMediaRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> getWhatsAppMediaList() {
        return null;
    }
    
    public final void setWhatsAppMediaList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getLoadDataSection() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object retrieveAllPhotos(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.ArrayList<com.im.bin.db.entities.WhatsAppMedia>> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteMedia(@org.jetbrains.annotations.NotNull()
    java.lang.String mediaId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAllMedia(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p0) {
        return null;
    }
    
    public final void setOriginalWhatsAppMediaList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.db.entities.WhatsAppMedia> medias) {
    }
    
    public final void setWhatsAppMediaList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.db.entities.WhatsAppMedia> medias) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> getMediaList() {
        return null;
    }
    
    public final void selectWhatsAppMedia(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.WhatsAppMedia media) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.im.bin.db.entities.WhatsAppMedia> getSelectedWhatsAppMedia() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<java.util.List<com.im.bin.db.entities.WhatsAppMedia>> performSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    public final void setLoadDataSection(boolean load) {
    }
    
    public WhatsAppPhotosViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String mediaType) {
        super();
    }
}