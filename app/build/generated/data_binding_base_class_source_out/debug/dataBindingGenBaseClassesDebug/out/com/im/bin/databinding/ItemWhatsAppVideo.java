// Generated by data binding compiler. Do not edit!
package com.im.bin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.im.bin.R;
import com.im.bin.customViews.RobotoMediumTextView;
import com.im.bin.db.entities.WhatsAppMedia;
import com.im.bin.viewModel.WhatsAppPhotosViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemWhatsAppVideo extends ViewDataBinding {
  @NonNull
  public final CardView cardView;

  @NonNull
  public final RobotoMediumTextView date;

  @NonNull
  public final ImageView image;

  @NonNull
  public final RobotoMediumTextView playButton;

  @NonNull
  public final FrameLayout videoLayout;

  @Bindable
  protected WhatsAppMedia mVideo;

  @Bindable
  protected WhatsAppPhotosViewModel mViewModel;

  protected ItemWhatsAppVideo(Object _bindingComponent, View _root, int _localFieldCount,
      CardView cardView, RobotoMediumTextView date, ImageView image,
      RobotoMediumTextView playButton, FrameLayout videoLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardView = cardView;
    this.date = date;
    this.image = image;
    this.playButton = playButton;
    this.videoLayout = videoLayout;
  }

  public abstract void setVideo(@Nullable WhatsAppMedia video);

  @Nullable
  public WhatsAppMedia getVideo() {
    return mVideo;
  }

  public abstract void setViewModel(@Nullable WhatsAppPhotosViewModel viewModel);

  @Nullable
  public WhatsAppPhotosViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static ItemWhatsAppVideo inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_video, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemWhatsAppVideo inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemWhatsAppVideo>inflateInternal(inflater, R.layout.item_video, root, attachToRoot, component);
  }

  @NonNull
  public static ItemWhatsAppVideo inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_video, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemWhatsAppVideo inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemWhatsAppVideo>inflateInternal(inflater, R.layout.item_video, null, false, component);
  }

  public static ItemWhatsAppVideo bind(@NonNull View view) {
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
  public static ItemWhatsAppVideo bind(@NonNull View view, @Nullable Object component) {
    return (ItemWhatsAppVideo)bind(component, view, R.layout.item_video);
  }
}
