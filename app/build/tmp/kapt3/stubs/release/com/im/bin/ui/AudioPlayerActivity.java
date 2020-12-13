package com.im.bin.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\b\u0010\u001b\u001a\u00020\u0018H\u0002J\"\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\u00182\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\u0018H\u0014J\b\u0010&\u001a\u00020\nH\u0016J\b\u0010\'\u001a\u00020\u0018H\u0002J\u0010\u0010(\u001a\u00020\u00182\b\u0010)\u001a\u0004\u0018\u00010\u0004J\b\u0010*\u001a\u00020\u0018H\u0002J\b\u0010+\u001a\u00020\u0018H\u0002J\b\u0010,\u001a\u00020\u0018H\u0002J\b\u0010-\u001a\u00020\u0018H\u0002J\b\u0010.\u001a\u00020\u0018H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u00060"}, d2 = {"Lcom/im/bin/ui/AudioPlayerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "filePath", "", "getFilePath", "()Ljava/lang/String;", "setFilePath", "(Ljava/lang/String;)V", "isPlaying", "", "mHandler", "Landroid/os/Handler;", "mMediaPlayer", "Landroid/media/MediaPlayer;", "onCompletionListener", "Landroid/media/MediaPlayer$OnCompletionListener;", "whatsAppMedia", "Lcom/im/bin/db/entities/WhatsAppMedia;", "getWhatsAppMedia", "()Lcom/im/bin/db/entities/WhatsAppMedia;", "setWhatsAppMedia", "(Lcom/im/bin/db/entities/WhatsAppMedia;)V", "addSeekBarChangeListener", "", "deleteFile", "initializeAudioPlayer", "initializeSeekBar", "onActivityResult", "requestCode", "", "resultCode", "resultData", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onSupportNavigateUp", "pausePlayer", "performFileSearch", "path", "resetPlayingSetting", "shareFile", "startPlayer", "stopPlayer", "updateSeekBar", "Companion", "app_release"})
public final class AudioPlayerActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public java.lang.String filePath;
    @org.jetbrains.annotations.NotNull()
    public com.im.bin.db.entities.WhatsAppMedia whatsAppMedia;
    private android.media.MediaPlayer mMediaPlayer;
    private final android.os.Handler mHandler = null;
    private boolean isPlaying = false;
    private final android.media.MediaPlayer.OnCompletionListener onCompletionListener = null;
    private static final int READ_REQUEST_CODE = 42;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MEDIA = "EXTRA_MEDIA";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_POSITION = "EXTRA_POSITION";
    public static final com.im.bin.ui.AudioPlayerActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFilePath() {
        return null;
    }
    
    public final void setFilePath(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.db.entities.WhatsAppMedia getWhatsAppMedia() {
        return null;
    }
    
    public final void setWhatsAppMedia(@org.jetbrains.annotations.NotNull()
    com.im.bin.db.entities.WhatsAppMedia p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void shareFile() {
    }
    
    private final void deleteFile() {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent resultData) {
    }
    
    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    public final void performFileSearch(@org.jetbrains.annotations.Nullable()
    java.lang.String path) {
    }
    
    private final void initializeSeekBar() {
    }
    
    private final void initializeAudioPlayer() {
    }
    
    private final void resetPlayingSetting() {
    }
    
    private final void startPlayer() {
    }
    
    private final void pausePlayer() {
    }
    
    private final void stopPlayer() {
    }
    
    private final void updateSeekBar() {
    }
    
    private final void addSeekBarChangeListener() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    public AudioPlayerActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/im/bin/ui/AudioPlayerActivity$Companion;", "", "()V", "EXTRA_MEDIA", "", "EXTRA_POSITION", "READ_REQUEST_CODE", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}