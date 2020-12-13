package com.im.bin.dialog;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020/H\u0002J\b\u00101\u001a\u00020/H\u0002J\b\u00102\u001a\u00020/H\u0002J\b\u00103\u001a\u00020/H\u0002J\b\u00104\u001a\u00020/H\u0002J\u0010\u0010\u0012\u001a\u00020/2\u0006\u00105\u001a\u000206H\u0002J\b\u00107\u001a\u00020/H\u0002J\b\u00108\u001a\u00020/H\u0002J\b\u00109\u001a\u00020/H\u0002R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010&\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u0011\"\u0004\b(\u0010\u0013R\u000e\u0010)\u001a\u00020*X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010+\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0011\"\u0004\b-\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lcom/im/bin/dialog/AudioPlayerDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "titleTxt", "", "filePath", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "closeButton", "Landroid/widget/ImageView;", "getCloseButton", "()Landroid/widget/ImageView;", "setCloseButton", "(Landroid/widget/ImageView;)V", "duration", "Landroid/widget/TextView;", "getDuration", "()Landroid/widget/TextView;", "setDuration", "(Landroid/widget/TextView;)V", "isPlaying", "", "mHandler", "Landroid/os/Handler;", "mMediaPlayer", "Landroid/media/MediaPlayer;", "getMMediaPlayer", "()Landroid/media/MediaPlayer;", "setMMediaPlayer", "(Landroid/media/MediaPlayer;)V", "mSeekBar", "Landroid/widget/SeekBar;", "getMSeekBar", "()Landroid/widget/SeekBar;", "setMSeekBar", "(Landroid/widget/SeekBar;)V", "onCompletionListener", "Landroid/media/MediaPlayer$OnCompletionListener;", "playIcon", "getPlayIcon", "setPlayIcon", "runnable", "Ljava/lang/Runnable;", "title", "getTitle", "setTitle", "addSeekBarChangeListener", "", "initializeAudioPlayer", "initializeSeekBar", "pausePlayer", "recursiveCallHandler", "resetPlayingSetting", "dur", "", "startPlayer", "stopPlayer", "updateSeekBar", "app_debug"})
public final class AudioPlayerDialog extends android.app.Dialog {
    @org.jetbrains.annotations.Nullable()
    private android.media.MediaPlayer mMediaPlayer;
    private final android.os.Handler mHandler = null;
    private boolean isPlaying = false;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView playIcon;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView duration;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView title;
    @org.jetbrains.annotations.Nullable()
    private android.widget.SeekBar mSeekBar;
    @org.jetbrains.annotations.Nullable()
    private android.widget.ImageView closeButton;
    private final java.lang.Runnable runnable = null;
    private final android.media.MediaPlayer.OnCompletionListener onCompletionListener = null;
    private final java.lang.String titleTxt = null;
    private final java.lang.String filePath = null;
    
    @org.jetbrains.annotations.Nullable()
    public final android.media.MediaPlayer getMMediaPlayer() {
        return null;
    }
    
    public final void setMMediaPlayer(@org.jetbrains.annotations.Nullable()
    android.media.MediaPlayer p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.TextView getPlayIcon() {
        return null;
    }
    
    public final void setPlayIcon(@org.jetbrains.annotations.Nullable()
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.TextView getDuration() {
        return null;
    }
    
    public final void setDuration(@org.jetbrains.annotations.Nullable()
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.TextView getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.Nullable()
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.SeekBar getMSeekBar() {
        return null;
    }
    
    public final void setMSeekBar(@org.jetbrains.annotations.Nullable()
    android.widget.SeekBar p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.ImageView getCloseButton() {
        return null;
    }
    
    public final void setCloseButton(@org.jetbrains.annotations.Nullable()
    android.widget.ImageView p0) {
    }
    
    private final void recursiveCallHandler() {
    }
    
    private final void initializeSeekBar() {
    }
    
    private final void setDuration(int dur) {
    }
    
    private final void updateSeekBar() {
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
    
    private final void addSeekBarChangeListener() {
    }
    
    public AudioPlayerDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String titleTxt, @org.jetbrains.annotations.NotNull()
    java.lang.String filePath) {
        super(null);
    }
}