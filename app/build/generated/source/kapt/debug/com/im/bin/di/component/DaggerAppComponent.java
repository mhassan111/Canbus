// Generated by Dagger (https://dagger.dev).
package com.im.bin.di.component;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.im.bin.MyApplication;
import com.im.bin.MyApplication_MembersInjector;
import com.im.bin.di.factory.AppViewModelFactory;
import com.im.bin.di.factory.AppViewModelFactory_Factory;
import com.im.bin.di.module.ActivityBuildersModule_BindChatConversationActivity;
import com.im.bin.di.module.ActivityBuildersModule_BindHomeActivity;
import com.im.bin.di.module.ActivityBuildersModule_BindLoginActivity;
import com.im.bin.di.module.ActivityBuildersModule_BindMainActivity;
import com.im.bin.di.module.ActivityBuildersModule_BindRegistrationActivity;
import com.im.bin.di.module.AppModule_ProvideFirebaseSourceFactory;
import com.im.bin.di.module.AppModule_ProvideUserRepositoryFactory;
import com.im.bin.di.module.FragmentBuilderModule_BindChatConversationFragment;
import com.im.bin.di.module.FragmentBuilderModule_BindWhatsAppChatFragment;
import com.im.bin.di.module.FragmentBuilderModule_BindWhatsAppPhotosFragment;
import com.im.bin.di.module.FragmentBuilderModule_BindWhatsAppVoipFragment;
import com.im.bin.firebase.FirebaseSource;
import com.im.bin.firebase.FirebaseSourceImpl_Factory;
import com.im.bin.fragments.im.ChatConversationFragment;
import com.im.bin.fragments.im.ChatConversationFragment_MembersInjector;
import com.im.bin.fragments.im.WhatsAppChatFragment;
import com.im.bin.fragments.im.WhatsAppChatFragment_MembersInjector;
import com.im.bin.fragments.im.WhatsAppMediaFragment;
import com.im.bin.fragments.im.WhatsAppVoipFragment;
import com.im.bin.fragments.im.WhatsAppVoipFragment_MembersInjector;
import com.im.bin.respository.UserRepository;
import com.im.bin.respository.UserRepositoryImpl;
import com.im.bin.respository.UserRepositoryImpl_Factory;
import com.im.bin.ui.ChatConversationActivity;
import com.im.bin.ui.HomeActivity;
import com.im.bin.ui.HomeActivity_MembersInjector;
import com.im.bin.ui.MainActivity;
import com.im.bin.ui.auth.LoginActivity;
import com.im.bin.ui.auth.LoginActivity_MembersInjector;
import com.im.bin.ui.auth.SignUpActivity;
import com.im.bin.ui.auth.SignUpActivity_MembersInjector;
import com.im.bin.ui.base.BaseActivity_MembersInjector;
import com.im.bin.ui.base.BaseAuthActivity_MembersInjector;
import com.im.bin.ui.base.BaseFragment_MembersInjector;
import com.im.bin.viewModel.LoginViewModel;
import com.im.bin.viewModel.LoginViewModel_Factory;
import com.im.bin.viewModel.SignUpViewModel;
import com.im.bin.viewModel.SignUpViewModel_Factory;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.DispatchingAndroidInjector_Factory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapProviderFactory;
import dagger.internal.Preconditions;
import java.util.Map;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerAppComponent implements AppComponent {
  private Provider<ActivityBuildersModule_BindMainActivity.MainActivitySubcomponent.Factory> mainActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuildersModule_BindLoginActivity.LoginActivitySubcomponent.Factory> loginActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuildersModule_BindHomeActivity.HomeActivitySubcomponent.Factory> homeActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuildersModule_BindChatConversationActivity.ChatConversationActivitySubcomponent.Factory> chatConversationActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuildersModule_BindRegistrationActivity.SignUpActivitySubcomponent.Factory> signUpActivitySubcomponentFactoryProvider;

  private Provider<FragmentBuilderModule_BindWhatsAppVoipFragment.WhatsAppVoipFragmentSubcomponent.Factory> whatsAppVoipFragmentSubcomponentFactoryProvider;

  private Provider<FragmentBuilderModule_BindWhatsAppPhotosFragment.WhatsAppMediaFragmentSubcomponent.Factory> whatsAppMediaFragmentSubcomponentFactoryProvider;

  private Provider<FragmentBuilderModule_BindWhatsAppChatFragment.WhatsAppChatFragmentSubcomponent.Factory> whatsAppChatFragmentSubcomponentFactoryProvider;

  private Provider<FragmentBuilderModule_BindChatConversationFragment.ChatConversationFragmentSubcomponent.Factory> chatConversationFragmentSubcomponentFactoryProvider;

  private Provider<FirebaseSource> provideFirebaseSourceProvider;

  private Provider<UserRepositoryImpl> userRepositoryImplProvider;

  private Provider<UserRepository> provideUserRepositoryProvider;

  private Provider<LoginViewModel> loginViewModelProvider;

  private Provider<SignUpViewModel> signUpViewModelProvider;

  private Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> mapOfClassOfAndProviderOfViewModelProvider;

  private Provider<AppViewModelFactory> appViewModelFactoryProvider;

  private DaggerAppComponent(Application application) {

    initialize(application);
  }

  public static AppComponent.Builder builder() {
    return new Builder();
  }

  private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
      ) {
    return ImmutableMap.<Class<?>, Provider<AndroidInjector.Factory<?>>>builderWithExpectedSize(9).put(MainActivity.class, (Provider) mainActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) loginActivitySubcomponentFactoryProvider).put(HomeActivity.class, (Provider) homeActivitySubcomponentFactoryProvider).put(ChatConversationActivity.class, (Provider) chatConversationActivitySubcomponentFactoryProvider).put(SignUpActivity.class, (Provider) signUpActivitySubcomponentFactoryProvider).put(WhatsAppVoipFragment.class, (Provider) whatsAppVoipFragmentSubcomponentFactoryProvider).put(WhatsAppMediaFragment.class, (Provider) whatsAppMediaFragmentSubcomponentFactoryProvider).put(WhatsAppChatFragment.class, (Provider) whatsAppChatFragmentSubcomponentFactoryProvider).put(ChatConversationFragment.class, (Provider) chatConversationFragmentSubcomponentFactoryProvider).build();}

  private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
    return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), ImmutableMap.<String, Provider<AndroidInjector.Factory<?>>>of());}

  @SuppressWarnings("unchecked")
  private void initialize(final Application application) {
    this.mainActivitySubcomponentFactoryProvider = new Provider<ActivityBuildersModule_BindMainActivity.MainActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuildersModule_BindMainActivity.MainActivitySubcomponent.Factory get() {
        return new MainActivitySubcomponentFactory();}
    };
    this.loginActivitySubcomponentFactoryProvider = new Provider<ActivityBuildersModule_BindLoginActivity.LoginActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuildersModule_BindLoginActivity.LoginActivitySubcomponent.Factory get() {
        return new LoginActivitySubcomponentFactory();}
    };
    this.homeActivitySubcomponentFactoryProvider = new Provider<ActivityBuildersModule_BindHomeActivity.HomeActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuildersModule_BindHomeActivity.HomeActivitySubcomponent.Factory get() {
        return new HomeActivitySubcomponentFactory();}
    };
    this.chatConversationActivitySubcomponentFactoryProvider = new Provider<ActivityBuildersModule_BindChatConversationActivity.ChatConversationActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuildersModule_BindChatConversationActivity.ChatConversationActivitySubcomponent.Factory get(
          ) {
        return new ChatConversationActivitySubcomponentFactory();}
    };
    this.signUpActivitySubcomponentFactoryProvider = new Provider<ActivityBuildersModule_BindRegistrationActivity.SignUpActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuildersModule_BindRegistrationActivity.SignUpActivitySubcomponent.Factory get(
          ) {
        return new SignUpActivitySubcomponentFactory();}
    };
    this.whatsAppVoipFragmentSubcomponentFactoryProvider = new Provider<FragmentBuilderModule_BindWhatsAppVoipFragment.WhatsAppVoipFragmentSubcomponent.Factory>() {
      @Override
      public FragmentBuilderModule_BindWhatsAppVoipFragment.WhatsAppVoipFragmentSubcomponent.Factory get(
          ) {
        return new WhatsAppVoipFragmentSubcomponentFactory();}
    };
    this.whatsAppMediaFragmentSubcomponentFactoryProvider = new Provider<FragmentBuilderModule_BindWhatsAppPhotosFragment.WhatsAppMediaFragmentSubcomponent.Factory>() {
      @Override
      public FragmentBuilderModule_BindWhatsAppPhotosFragment.WhatsAppMediaFragmentSubcomponent.Factory get(
          ) {
        return new WhatsAppMediaFragmentSubcomponentFactory();}
    };
    this.whatsAppChatFragmentSubcomponentFactoryProvider = new Provider<FragmentBuilderModule_BindWhatsAppChatFragment.WhatsAppChatFragmentSubcomponent.Factory>() {
      @Override
      public FragmentBuilderModule_BindWhatsAppChatFragment.WhatsAppChatFragmentSubcomponent.Factory get(
          ) {
        return new WhatsAppChatFragmentSubcomponentFactory();}
    };
    this.chatConversationFragmentSubcomponentFactoryProvider = new Provider<FragmentBuilderModule_BindChatConversationFragment.ChatConversationFragmentSubcomponent.Factory>() {
      @Override
      public FragmentBuilderModule_BindChatConversationFragment.ChatConversationFragmentSubcomponent.Factory get(
          ) {
        return new ChatConversationFragmentSubcomponentFactory();}
    };
    this.provideFirebaseSourceProvider = DoubleCheck.provider(AppModule_ProvideFirebaseSourceFactory.create(FirebaseSourceImpl_Factory.create()));
    this.userRepositoryImplProvider = UserRepositoryImpl_Factory.create(provideFirebaseSourceProvider);
    this.provideUserRepositoryProvider = DoubleCheck.provider(AppModule_ProvideUserRepositoryFactory.create(userRepositoryImplProvider));
    this.loginViewModelProvider = LoginViewModel_Factory.create(provideUserRepositoryProvider);
    this.signUpViewModelProvider = SignUpViewModel_Factory.create(provideUserRepositoryProvider);
    this.mapOfClassOfAndProviderOfViewModelProvider = MapProviderFactory.<Class<? extends ViewModel>, ViewModel>builder(2).put(LoginViewModel.class, (Provider) loginViewModelProvider).put(SignUpViewModel.class, (Provider) signUpViewModelProvider).build();
    this.appViewModelFactoryProvider = DoubleCheck.provider(AppViewModelFactory_Factory.create(mapOfClassOfAndProviderOfViewModelProvider));
  }

  @Override
  public void inject(MyApplication myApplication) {
    injectMyApplication(myApplication);}

  @CanIgnoreReturnValue
  private MyApplication injectMyApplication(MyApplication instance) {
    MyApplication_MembersInjector.injectDispatchingAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
    return instance;
  }

  private static final class Builder implements AppComponent.Builder {
    private Application application;

    @Override
    public Builder application(Application application) {
      this.application = Preconditions.checkNotNull(application);
      return this;
    }

    @Override
    public AppComponent build() {
      Preconditions.checkBuilderRequirement(application, Application.class);
      return new DaggerAppComponent(application);
    }
  }

  private final class MainActivitySubcomponentFactory implements ActivityBuildersModule_BindMainActivity.MainActivitySubcomponent.Factory {
    @Override
    public ActivityBuildersModule_BindMainActivity.MainActivitySubcomponent create(
        MainActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new MainActivitySubcomponentImpl(arg0);
    }
  }

  private final class MainActivitySubcomponentImpl implements ActivityBuildersModule_BindMainActivity.MainActivitySubcomponent {
    private MainActivitySubcomponentImpl(MainActivity arg0) {

    }

    @Override
    public void inject(MainActivity arg0) {
      injectMainActivity(arg0);}

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity(MainActivity instance) {
      BaseActivity_MembersInjector.injectAndroidInjector(instance, DaggerAppComponent.this.getDispatchingAndroidInjectorOfObject());
      return instance;
    }
  }

  private final class LoginActivitySubcomponentFactory implements ActivityBuildersModule_BindLoginActivity.LoginActivitySubcomponent.Factory {
    @Override
    public ActivityBuildersModule_BindLoginActivity.LoginActivitySubcomponent create(
        LoginActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new LoginActivitySubcomponentImpl(arg0);
    }
  }

  private final class LoginActivitySubcomponentImpl implements ActivityBuildersModule_BindLoginActivity.LoginActivitySubcomponent {
    private LoginActivitySubcomponentImpl(LoginActivity arg0) {

    }

    @Override
    public void inject(LoginActivity arg0) {
      injectLoginActivity(arg0);}

    @CanIgnoreReturnValue
    private LoginActivity injectLoginActivity(LoginActivity instance) {
      BaseAuthActivity_MembersInjector.injectAndroidInjector(instance, DaggerAppComponent.this.getDispatchingAndroidInjectorOfObject());
      LoginActivity_MembersInjector.injectViewModelFactory(instance, DaggerAppComponent.this.appViewModelFactoryProvider.get());
      return instance;
    }
  }

  private final class HomeActivitySubcomponentFactory implements ActivityBuildersModule_BindHomeActivity.HomeActivitySubcomponent.Factory {
    @Override
    public ActivityBuildersModule_BindHomeActivity.HomeActivitySubcomponent create(
        HomeActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new HomeActivitySubcomponentImpl(arg0);
    }
  }

  private final class HomeActivitySubcomponentImpl implements ActivityBuildersModule_BindHomeActivity.HomeActivitySubcomponent {
    private HomeActivitySubcomponentImpl(HomeActivity arg0) {

    }

    @Override
    public void inject(HomeActivity arg0) {
      injectHomeActivity(arg0);}

    @CanIgnoreReturnValue
    private HomeActivity injectHomeActivity(HomeActivity instance) {
      BaseActivity_MembersInjector.injectAndroidInjector(instance, DaggerAppComponent.this.getDispatchingAndroidInjectorOfObject());
      HomeActivity_MembersInjector.injectUserRepository(instance, DaggerAppComponent.this.provideUserRepositoryProvider.get());
      return instance;
    }
  }

  private final class ChatConversationActivitySubcomponentFactory implements ActivityBuildersModule_BindChatConversationActivity.ChatConversationActivitySubcomponent.Factory {
    @Override
    public ActivityBuildersModule_BindChatConversationActivity.ChatConversationActivitySubcomponent create(
        ChatConversationActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new ChatConversationActivitySubcomponentImpl(arg0);
    }
  }

  private final class ChatConversationActivitySubcomponentImpl implements ActivityBuildersModule_BindChatConversationActivity.ChatConversationActivitySubcomponent {
    private ChatConversationActivitySubcomponentImpl(ChatConversationActivity arg0) {

    }

    @Override
    public void inject(ChatConversationActivity arg0) {
      injectChatConversationActivity(arg0);}

    @CanIgnoreReturnValue
    private ChatConversationActivity injectChatConversationActivity(
        ChatConversationActivity instance) {
      BaseActivity_MembersInjector.injectAndroidInjector(instance, DaggerAppComponent.this.getDispatchingAndroidInjectorOfObject());
      return instance;
    }
  }

  private final class SignUpActivitySubcomponentFactory implements ActivityBuildersModule_BindRegistrationActivity.SignUpActivitySubcomponent.Factory {
    @Override
    public ActivityBuildersModule_BindRegistrationActivity.SignUpActivitySubcomponent create(
        SignUpActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new SignUpActivitySubcomponentImpl(arg0);
    }
  }

  private final class SignUpActivitySubcomponentImpl implements ActivityBuildersModule_BindRegistrationActivity.SignUpActivitySubcomponent {
    private SignUpActivitySubcomponentImpl(SignUpActivity arg0) {

    }

    @Override
    public void inject(SignUpActivity arg0) {
      injectSignUpActivity(arg0);}

    @CanIgnoreReturnValue
    private SignUpActivity injectSignUpActivity(SignUpActivity instance) {
      BaseAuthActivity_MembersInjector.injectAndroidInjector(instance, DaggerAppComponent.this.getDispatchingAndroidInjectorOfObject());
      SignUpActivity_MembersInjector.injectViewModelFactory(instance, DaggerAppComponent.this.appViewModelFactoryProvider.get());
      return instance;
    }
  }

  private final class WhatsAppVoipFragmentSubcomponentFactory implements FragmentBuilderModule_BindWhatsAppVoipFragment.WhatsAppVoipFragmentSubcomponent.Factory {
    @Override
    public FragmentBuilderModule_BindWhatsAppVoipFragment.WhatsAppVoipFragmentSubcomponent create(
        WhatsAppVoipFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new WhatsAppVoipFragmentSubcomponentImpl(arg0);
    }
  }

  private final class WhatsAppVoipFragmentSubcomponentImpl implements FragmentBuilderModule_BindWhatsAppVoipFragment.WhatsAppVoipFragmentSubcomponent {
    private WhatsAppVoipFragmentSubcomponentImpl(WhatsAppVoipFragment arg0) {

    }

    @Override
    public void inject(WhatsAppVoipFragment arg0) {
      injectWhatsAppVoipFragment(arg0);}

    @CanIgnoreReturnValue
    private WhatsAppVoipFragment injectWhatsAppVoipFragment(WhatsAppVoipFragment instance) {
      BaseFragment_MembersInjector.injectAndroidInjector(instance, DaggerAppComponent.this.getDispatchingAndroidInjectorOfObject());
      WhatsAppVoipFragment_MembersInjector.injectViewModelFactory(instance, DaggerAppComponent.this.appViewModelFactoryProvider.get());
      return instance;
    }
  }

  private final class WhatsAppMediaFragmentSubcomponentFactory implements FragmentBuilderModule_BindWhatsAppPhotosFragment.WhatsAppMediaFragmentSubcomponent.Factory {
    @Override
    public FragmentBuilderModule_BindWhatsAppPhotosFragment.WhatsAppMediaFragmentSubcomponent create(
        WhatsAppMediaFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new WhatsAppMediaFragmentSubcomponentImpl(arg0);
    }
  }

  private final class WhatsAppMediaFragmentSubcomponentImpl implements FragmentBuilderModule_BindWhatsAppPhotosFragment.WhatsAppMediaFragmentSubcomponent {
    private WhatsAppMediaFragmentSubcomponentImpl(WhatsAppMediaFragment arg0) {

    }

    @Override
    public void inject(WhatsAppMediaFragment arg0) {
      injectWhatsAppMediaFragment(arg0);}

    @CanIgnoreReturnValue
    private WhatsAppMediaFragment injectWhatsAppMediaFragment(WhatsAppMediaFragment instance) {
      BaseFragment_MembersInjector.injectAndroidInjector(instance, DaggerAppComponent.this.getDispatchingAndroidInjectorOfObject());
      return instance;
    }
  }

  private final class WhatsAppChatFragmentSubcomponentFactory implements FragmentBuilderModule_BindWhatsAppChatFragment.WhatsAppChatFragmentSubcomponent.Factory {
    @Override
    public FragmentBuilderModule_BindWhatsAppChatFragment.WhatsAppChatFragmentSubcomponent create(
        WhatsAppChatFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new WhatsAppChatFragmentSubcomponentImpl(arg0);
    }
  }

  private final class WhatsAppChatFragmentSubcomponentImpl implements FragmentBuilderModule_BindWhatsAppChatFragment.WhatsAppChatFragmentSubcomponent {
    private WhatsAppChatFragmentSubcomponentImpl(WhatsAppChatFragment arg0) {

    }

    @Override
    public void inject(WhatsAppChatFragment arg0) {
      injectWhatsAppChatFragment(arg0);}

    @CanIgnoreReturnValue
    private WhatsAppChatFragment injectWhatsAppChatFragment(WhatsAppChatFragment instance) {
      BaseFragment_MembersInjector.injectAndroidInjector(instance, DaggerAppComponent.this.getDispatchingAndroidInjectorOfObject());
      WhatsAppChatFragment_MembersInjector.injectFirebaseSource(instance, DaggerAppComponent.this.provideFirebaseSourceProvider.get());
      WhatsAppChatFragment_MembersInjector.injectViewModelFactory(instance, DaggerAppComponent.this.appViewModelFactoryProvider.get());
      return instance;
    }
  }

  private final class ChatConversationFragmentSubcomponentFactory implements FragmentBuilderModule_BindChatConversationFragment.ChatConversationFragmentSubcomponent.Factory {
    @Override
    public FragmentBuilderModule_BindChatConversationFragment.ChatConversationFragmentSubcomponent create(
        ChatConversationFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new ChatConversationFragmentSubcomponentImpl(arg0);
    }
  }

  private final class ChatConversationFragmentSubcomponentImpl implements FragmentBuilderModule_BindChatConversationFragment.ChatConversationFragmentSubcomponent {
    private ChatConversationFragmentSubcomponentImpl(ChatConversationFragment arg0) {

    }

    @Override
    public void inject(ChatConversationFragment arg0) {
      injectChatConversationFragment(arg0);}

    @CanIgnoreReturnValue
    private ChatConversationFragment injectChatConversationFragment(
        ChatConversationFragment instance) {
      BaseFragment_MembersInjector.injectAndroidInjector(instance, DaggerAppComponent.this.getDispatchingAndroidInjectorOfObject());
      ChatConversationFragment_MembersInjector.injectFirebaseSource(instance, DaggerAppComponent.this.provideFirebaseSourceProvider.get());
      return instance;
    }
  }
}
