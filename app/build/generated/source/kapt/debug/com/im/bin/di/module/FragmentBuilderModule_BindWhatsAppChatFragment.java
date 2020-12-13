package com.im.bin.di.module;

import com.im.bin.fragments.im.WhatsAppChatFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      FragmentBuilderModule_BindWhatsAppChatFragment.WhatsAppChatFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindWhatsAppChatFragment {
  private FragmentBuilderModule_BindWhatsAppChatFragment() {}

  @Binds
  @IntoMap
  @ClassKey(WhatsAppChatFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      WhatsAppChatFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface WhatsAppChatFragmentSubcomponent extends AndroidInjector<WhatsAppChatFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<WhatsAppChatFragment> {}
  }
}
