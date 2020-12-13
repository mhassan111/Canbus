// Generated by Dagger (https://dagger.dev).
package com.im.bin.ui.base;

import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BaseAuthActivity_MembersInjector implements MembersInjector<BaseAuthActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  public BaseAuthActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
  }

  public static MembersInjector<BaseAuthActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider) {
    return new BaseAuthActivity_MembersInjector(androidInjectorProvider);}

  @Override
  public void injectMembers(BaseAuthActivity instance) {
    injectAndroidInjector(instance, androidInjectorProvider.get());
  }

  public static void injectAndroidInjector(BaseAuthActivity instance,
      DispatchingAndroidInjector<Object> androidInjector) {
    instance.androidInjector = androidInjector;
  }
}
