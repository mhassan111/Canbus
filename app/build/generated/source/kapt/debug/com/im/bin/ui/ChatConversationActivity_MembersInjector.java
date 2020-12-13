// Generated by Dagger (https://dagger.dev).
package com.im.bin.ui;

import com.im.bin.ui.base.BaseActivity_MembersInjector;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ChatConversationActivity_MembersInjector implements MembersInjector<ChatConversationActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  public ChatConversationActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
  }

  public static MembersInjector<ChatConversationActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider) {
    return new ChatConversationActivity_MembersInjector(androidInjectorProvider);}

  @Override
  public void injectMembers(ChatConversationActivity instance) {
    BaseActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
  }
}
