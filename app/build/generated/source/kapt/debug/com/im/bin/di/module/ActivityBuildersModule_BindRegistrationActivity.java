package com.im.bin.di.module;

import com.im.bin.ui.auth.SignUpActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents = ActivityBuildersModule_BindRegistrationActivity.SignUpActivitySubcomponent.class
)
public abstract class ActivityBuildersModule_BindRegistrationActivity {
  private ActivityBuildersModule_BindRegistrationActivity() {}

  @Binds
  @IntoMap
  @ClassKey(SignUpActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SignUpActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface SignUpActivitySubcomponent extends AndroidInjector<SignUpActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SignUpActivity> {}
  }
}
