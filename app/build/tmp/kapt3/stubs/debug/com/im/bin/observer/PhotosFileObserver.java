package com.im.bin.observer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00070\u00070\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/im/bin/observer/PhotosFileObserver;", "Landroid/os/FileObserver;", "file", "Ljava/io/File;", "(Ljava/io/File;)V", "observable", "Lio/reactivex/Observable;", "", "getObservable", "()Lio/reactivex/Observable;", "subject", "Lio/reactivex/subjects/Subject;", "kotlin.jvm.PlatformType", "onEvent", "", "event", "", "path", "app_debug"})
public final class PhotosFileObserver extends android.os.FileObserver {
    private final io.reactivex.subjects.Subject<java.lang.String> subject = null;
    @org.jetbrains.annotations.NotNull()
    private final io.reactivex.Observable<java.lang.String> observable = null;
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.lang.String> getObservable() {
        return null;
    }
    
    @java.lang.Override()
    public void onEvent(int event, @org.jetbrains.annotations.Nullable()
    java.lang.String path) {
    }
    
    public PhotosFileObserver(@org.jetbrains.annotations.NotNull()
    java.io.File file) {
        super(null);
    }
}