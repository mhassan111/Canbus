package com.im.bin.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\tJ\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\u0012J\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\u0012J\u0006\u0010&\u001a\u00020\"J\u001a\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0(2\u0006\u0010)\u001a\u00020\u0005J\u000e\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\tJ\u0014\u0010,\u001a\u00020\"2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0\fJ\u000e\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u000eJ\u0014\u00100\u001a\u00020\"2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0\fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u00a8\u00061"}, d2 = {"Lcom/im/bin/viewModel/WhatsAppVoipViewModel;", "Lcom/im/bin/viewModel/BaseViewModel;", "context", "Landroid/content/Context;", "imType", "", "(Landroid/content/Context;Ljava/lang/String;)V", "_calls", "", "Lcom/im/bin/db/entities/VoipCall;", "_filteredCalls", "Landroidx/lifecycle/MutableLiveData;", "", "_loadDataSection", "", "kotlin.jvm.PlatformType", "_selectedCall", "filteredCalls", "Landroidx/lifecycle/LiveData;", "loadDataSection", "getLoadDataSection", "()Landroidx/lifecycle/LiveData;", "selectedCall", "voipCallList", "getVoipCallList", "setVoipCallList", "(Landroidx/lifecycle/LiveData;)V", "voipCallRepository", "Lcom/im/bin/respository/VoipCallRepository;", "getVoipCallRepository", "()Lcom/im/bin/respository/VoipCallRepository;", "setVoipCallRepository", "(Lcom/im/bin/respository/VoipCallRepository;)V", "delete", "", "voipCall", "getFilteredVoipCalls", "getSelectedVoipCall", "loadVoipCalls", "performSearchQuery", "Lio/reactivex/Single;", "query", "selectVoipCall", "chat", "setFilteredCalls", "calls", "setLoadDataSection", "load", "setOriginalCalls", "app_debug"})
public final class WhatsAppVoipViewModel extends com.im.bin.viewModel.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    public com.im.bin.respository.VoipCallRepository voipCallRepository;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.VoipCall>> voipCallList;
    private final java.util.List<com.im.bin.db.entities.VoipCall> _calls = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.im.bin.db.entities.VoipCall>> _filteredCalls = null;
    private final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.VoipCall>> filteredCalls = null;
    private final androidx.lifecycle.MutableLiveData<com.im.bin.db.entities.VoipCall> _selectedCall = null;
    private final androidx.lifecycle.LiveData<com.im.bin.db.entities.VoipCall> selectedCall = null;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _loadDataSection;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> loadDataSection = null;
    private final android.content.Context context = null;
    private final java.lang.String imType = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.VoipCallRepository getVoipCallRepository() {
        return null;
    }
    
    public final void setVoipCallRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.VoipCallRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.VoipCall>> getVoipCallList() {
        return null;
    }
    
    public final void setVoipCallList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.VoipCall>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getLoadDataSection() {
        return null;
    }
    
    public final void loadVoipCalls() {
    }
    
    public final void delete(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.VoipCall voipCall) {
    }
    
    public final void setOriginalCalls(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.db.entities.VoipCall> calls) {
    }
    
    public final void setFilteredCalls(@org.jetbrains.annotations.NotNull()
    java.util.List<com.im.bin.db.entities.VoipCall> calls) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.im.bin.db.entities.VoipCall>> getFilteredVoipCalls() {
        return null;
    }
    
    public final void selectVoipCall(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.VoipCall chat) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.im.bin.db.entities.VoipCall> getSelectedVoipCall() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<java.util.List<com.im.bin.db.entities.VoipCall>> performSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    public final void setLoadDataSection(boolean load) {
    }
    
    public WhatsAppVoipViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String imType) {
        super();
    }
}