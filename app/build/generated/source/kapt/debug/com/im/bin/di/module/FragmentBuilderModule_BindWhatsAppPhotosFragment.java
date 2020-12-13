package com.im.bin.di.module;

import com.im.bin.fragments.im.WhatsAppMediaFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      FragmentBuilderModule_BindWhatsAppPhotosFragment.WhatsAppMediaFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindWhatsAppPhotosFragment {
  private FragmentBuilderModule_BindWhatsAppPhotosFragment() {}

  @Binds
  @IntoMap
  @ClassKey(WhatsAppMediaFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      WhatsAppMediaFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface WhatsAppMediaFragmentSubcomponent
      extends AndroidInjector<WhatsAppMediaFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<WhatsAppMediaFragment> {}
  }
}
