// Generated by data binding compiler. Do not edit!
package com.im.bin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.im.bin.R;
import com.im.bin.customViews.RobotoLightTextView;
import com.im.bin.customViews.RobotoMediumTextView;
import com.im.bin.db.entities.WhatsAppMedia;
import com.im.bin.viewModel.WhatsAppPhotosViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemWhatsAppVoiceNote extends ViewDataBinding {
  @NonNull
  public final CardView cardView;

  @NonNull
  public final RobotoLightTextView date;

  @NonNull
  public final RobotoMediumTextView headphoneIcon;

  @NonNull
  public final RobotoMediumTextView name;

  @NonNull
  public final RelativeLayout voiceLayout;

  @Bindable
  protected WhatsAppMedia mVoiceNote;

  @Bindable
  protected WhatsAppPhotosViewModel mViewModel;

  protected ItemWhatsAppVoiceNote(Object _bindingComponent, View _root, int _localFieldCount,
      CardView cardView, RobotoLightTextView date, RobotoMediumTextView headphoneIcon,
      RobotoMediumTextView name, RelativeLayout voiceLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardView = cardView;
    this.date = date;
    this.headphoneIcon = headphoneIcon;
    this.name = name;
    this.voiceLayout = voiceLayout;
  }

  public abstract void setVoiceNote(@Nullable WhatsAppMedia voiceNote);

  @Nullable
  public WhatsAppMedia getVoiceNote() {
    return mVoiceNote;
  }

  public abstract void setViewModel(@Nullable WhatsAppPhotosViewModel viewModel);

  @Nullable
  public WhatsAppPhotosViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static ItemWhatsAppVoiceNote inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_voice_note, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemWhatsAppVoiceNote inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemWhatsAppVoiceNote>inflateInternal(inflater, R.layout.item_voice_note, root, attachToRoot, component);
  }

  @NonNull
  public static ItemWhatsAppVoiceNote inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_voice_note, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemWhatsAppVoiceNote inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemWhatsAppVoiceNote>inflateInternal(inflater, R.layout.item_voice_note, null, false, component);
  }

  public static ItemWhatsAppVoiceNote bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ItemWhatsAppVoiceNote bind(@NonNull View view, @Nullable Object component) {
    return (ItemWhatsAppVoiceNote)bind(component, view, R.layout.item_voice_note);
  }
}
