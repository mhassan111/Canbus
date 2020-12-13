package com.im.bin.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\'\u00a8\u0006\u000e"}, d2 = {"Lcom/im/bin/di/module/ViewModelModule;", "", "()V", "bindActivationViewModel", "Landroidx/lifecycle/ViewModel;", "loginViewModel", "Lcom/im/bin/viewModel/LoginViewModel;", "bindSignUpViewModel", "signUpViewModel", "Lcom/im/bin/viewModel/SignUpViewModel;", "bindViewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "factory", "Lcom/im/bin/di/factory/AppViewModelFactory;", "app_release"})
@dagger.Module()
public abstract class ViewModelModule {
    
    @org.jetbrains.annotations.NotNull()
    @ViewModelKey(value = com.im.bin.viewModel.LoginViewModel.class)
    @dagger.multibindings.IntoMap()
    @dagger.Binds()
    public abstract androidx.lifecycle.ViewModel bindActivationViewModel(@org.jetbrains.annotations.NotNull()
    com.im.bin.viewModel.LoginViewModel loginViewModel);
    
    @org.jetbrains.annotations.NotNull()
    @ViewModelKey(value = com.im.bin.viewModel.SignUpViewModel.class)
    @dagger.multibindings.IntoMap()
    @dagger.Binds()
    public abstract androidx.lifecycle.ViewModel bindSignUpViewModel(@org.jetbrains.annotations.NotNull()
    com.im.bin.viewModel.SignUpViewModel signUpViewModel);
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract androidx.lifecycle.ViewModelProvider.Factory bindViewModelFactory(@org.jetbrains.annotations.NotNull()
    com.im.bin.di.factory.AppViewModelFactory factory);
    
    public ViewModelModule() {
        super();
    }
}