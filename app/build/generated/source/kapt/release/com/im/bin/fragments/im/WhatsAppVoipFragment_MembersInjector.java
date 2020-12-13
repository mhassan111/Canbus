// Generated by Dagger (https://dagger.dev).
package com.im.bin.fragments.im;

import androidx.lifecycle.ViewModelProvider;
import com.im.bin.ui.base.BaseFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class WhatsAppVoipFragment_MembersInjector implements MembersInjector<WhatsAppVoipFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public WhatsAppVoipFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<WhatsAppVoipFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new WhatsAppVoipFragment_MembersInjector(androidInjectorProvider, viewModelFactoryProvider);}

  @Override
  public void injectMembers(WhatsAppVoipFragment instance) {
    BaseFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  public static void injectViewModelFactory(WhatsAppVoipFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}