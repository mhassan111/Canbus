package com.im.bin.di.module;

import com.im.bin.fragments.im.ChatConversationFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      FragmentBuilderModule_BindChatConversationFragment.ChatConversationFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindChatConversationFragment {
  private FragmentBuilderModule_BindChatConversationFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ChatConversationFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ChatConversationFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ChatConversationFragmentSubcomponent
      extends AndroidInjector<ChatConversationFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ChatConversationFragment> {}
  }
}
