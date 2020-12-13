package com.im.bin.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.im.bin.models.User
import com.im.bin.respository.UserRepository
import com.im.bin.ui.auth.AuthError
import com.im.bin.ui.auth.AuthState
import com.im.bin.ui.auth.Loading
import com.im.bin.ui.auth.OnAuthSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SignUpViewModel @Inject constructor() : BaseViewModel() {

    private val authState = MutableLiveData<AuthState>()
    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    private var _user = MutableLiveData<User>()
    private val user: LiveData<User> = _user
    private var _loadProgressBar = MutableLiveData<Boolean>(false)
    val loadProgressBar: LiveData<Boolean> = _loadProgressBar

    @Inject
    lateinit var userRepository: UserRepository

    fun signUp(user: User) {
        val disposable = userRepository.createAccount(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                authState.value = Loading
            }.subscribe(
                {
                    onSignUpSuccess(it)
                }, {
                    onSignUpFailed(it)
                }
            )
        compositeDisposable.add(disposable)
    }

    fun setProgressBarState(state: Boolean) {
        _loadProgressBar.value = state
    }

    fun onSignUpClick(view: View) {
        _user.value = User(email.value!!.toString().trim(), password.value!!.toString().trim())
    }

    fun getUser() = user

    private fun onSignUpSuccess(authResult: AuthResult) {
        authState.value = OnAuthSuccess(authResult)
    }

    private fun onSignUpFailed(throwable: Throwable) {
        authState.value = AuthError(throwable.message)
    }

    fun getSignUpState(): LiveData<AuthState> {
        return authState
    }
}