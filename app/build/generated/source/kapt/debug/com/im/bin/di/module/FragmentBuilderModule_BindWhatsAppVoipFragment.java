package com.im.bin.di.module;

import com.im.bin.fragments.im.WhatsAppVoipFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      FragmentBuilderModule_BindWhatsAppVoipFragment.WhatsAppVoipFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindWhatsAppVoipFragment {
  private FragmentBuilderModule_BindWhatsAppVoipFragment() {}

  @Binds
  @IntoMap
  @ClassKey(WhatsAppVoipFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      WhatsAppVoipFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface WhatsAppVoipFragmentSubcomponent extends AndroidInjector<WhatsAppVoipFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<WhatsAppVoipFragment> {}
  }
}
