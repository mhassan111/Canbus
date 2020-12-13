package com.im.bin.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u000fJ\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u0014\u001a\u00020\u0007J\u0012\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u000e\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&J\u0010\u0010\'\u001a\u00020 2\u0006\u0010(\u001a\u00020)H\u0002J\u000e\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006,"}, d2 = {"Lcom/im/bin/viewModel/LoginViewModel;", "Lcom/im/bin/viewModel/BaseViewModel;", "()V", "_loadProgressBar", "Landroidx/lifecycle/MutableLiveData;", "", "_user", "Lcom/im/bin/models/User;", "authState", "Lcom/im/bin/ui/auth/AuthState;", "email", "", "getEmail", "()Landroidx/lifecycle/MutableLiveData;", "loadProgressBar", "Landroidx/lifecycle/LiveData;", "getLoadProgressBar", "()Landroidx/lifecycle/LiveData;", "password", "getPassword", "user", "userRepository", "Lcom/im/bin/respository/UserRepository;", "getUserRepository", "()Lcom/im/bin/respository/UserRepository;", "setUserRepository", "(Lcom/im/bin/respository/UserRepository;)V", "getAuthState", "getCurrentUser", "Lcom/google/firebase/auth/FirebaseUser;", "getUser", "login", "", "onError", "throwable", "", "onSignInClick", "view", "Landroid/view/View;", "onSuccess", "authResult", "Lcom/google/firebase/auth/AuthResult;", "setProgressBarState", "state", "app_release"})
public final class LoginViewModel extends com.im.bin.viewModel.BaseViewModel {
    private final androidx.lifecycle.MutableLiveData<com.im.bin.ui.auth.AuthState> authState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> email = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> password = null;
    private androidx.lifecycle.MutableLiveData<com.im.bin.models.User> _user;
    private final androidx.lifecycle.LiveData<com.im.bin.models.User> user = null;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _loadProgressBar;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> loadProgressBar = null;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.im.bin.respository.UserRepository userRepository;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getPassword() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getLoadProgressBar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.im.bin.respository.UserRepository getUserRepository() {
        return null;
    }
    
    public final void setUserRepository(@org.jetbrains.annotations.NotNull()
    com.im.bin.respository.UserRepository p0) {
    }
    
    public final void login(@org.jetbrains.annotations.NotNull()
    com.im.bin.models.User user) {
    }
    
    public final void setProgressBarState(boolean state) {
    }
    
    public final void onSignInClick(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.im.bin.models.User> getUser() {
        return null;
    }
    
    private final void onSuccess(com.google.firebase.auth.AuthResult authResult) {
    }
    
    private final void onError(java.lang.Throwable throwable) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.auth.FirebaseUser getCurrentUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.im.bin.ui.auth.AuthState> getAuthState() {
        return null;
    }
    
    @javax.inject.Inject()
    public LoginViewModel() {
        super();
    }
}