package com.im.bin.di.module;

import com.im.bin.ui.ChatConversationActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      ActivityBuildersModule_BindChatConversationActivity.ChatConversationActivitySubcomponent.class
)
public abstract class ActivityBuildersModule_BindChatConversationActivity {
  private ActivityBuildersModule_BindChatConversationActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ChatConversationActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ChatConversationActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface ChatConversationActivitySubcomponent
      extends AndroidInjector<ChatConversationActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ChatConversationActivity> {}
  }
}
