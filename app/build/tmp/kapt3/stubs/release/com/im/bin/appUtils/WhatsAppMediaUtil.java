package com.im.bin.appUtils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ \u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002J2\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\u0016"}, d2 = {"Lcom/im/bin/appUtils/WhatsAppMediaUtil;", "", "()V", "deleteDirectory", "", "context", "Landroid/content/Context;", "mediaType", "", "getDestinationFilePath", "fileName", "getMedia", "Lcom/im/bin/db/entities/WhatsAppMedia;", "filePath", "mediaId", "direction", "timeStamp", "", "type", "traverseThroughWhatsAppDirectory", "Ljava/util/ArrayList;", "ProcessingException", "app_release"})
public final class WhatsAppMediaUtil {
    public static final com.im.bin.appUtils.WhatsAppMediaUtil INSTANCE = null;
    
    public final void deleteDirectory(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String mediaType) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.im.bin.db.entities.WhatsAppMedia> traverseThroughWhatsAppDirectory(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String mediaType) {
        return null;
    }
    
    private final java.lang.String getDestinationFilePath(android.content.Context context, java.lang.String mediaType, java.lang.String fileName) {
        return null;
    }
    
    private final com.im.bin.db.entities.WhatsAppMedia getMedia(java.lang.String filePath, java.lang.String mediaId, java.lang.String direction, long timeStamp, java.lang.String type) {
        return null;
    }
    
    private WhatsAppMediaUtil() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0005B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0006B\u001b\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\tB\u0011\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/im/bin/appUtils/WhatsAppMediaUtil$ProcessingException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "()V", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/Throwable;)V", "app_release"})
    public static final class ProcessingException extends java.lang.Exception {
        
        public ProcessingException(@org.jetbrains.annotations.Nullable()
        java.lang.String message) {
            super();
        }
        
        public ProcessingException() {
            super();
        }
        
        public ProcessingException(@org.jetbrains.annotations.Nullable()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.Throwable cause) {
            super();
        }
        
        public ProcessingException(@org.jetbrains.annotations.Nullable()
        java.lang.Throwable cause) {
            super();
        }
    }
}