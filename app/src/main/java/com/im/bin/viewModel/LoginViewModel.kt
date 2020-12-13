package com.im.bin.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.im.bin.models.User
import com.im.bin.respository.UserRepository
import com.im.bin.ui.auth.AuthError
import com.im.bin.ui.auth.AuthState
import com.im.bin.ui.auth.Loading
import com.im.bin.ui.auth.OnAuthSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val authState = MutableLiveData<AuthState>()
    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    private var _user = MutableLiveData<User>()
    private val user: LiveData<User> = _user
    private var _loadProgressBar = MutableLiveData<Boolean>(false)
    val loadProgressBar: LiveData<Boolean> = _loadProgressBar

    @Inject
    lateinit var userRepository: UserRepository

    fun login(user: User) {
        val disposable = userRepository.login(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                authState.value = Loading
            }.subscribe({
                onSuccess(it)
            }, { th: Throwable? ->
                onError(th)
            })
        compositeDisposable.add(disposable)
    }

    fun setProgressBarState(state: Boolean) {
        _loadProgressBar.value = state
    }

    fun onSignInClick(view: View) {
        _user.value = User(email.value!!.toString().trim(), password.value!!.toString().trim())
    }

    fun getUser() = user

    private fun onSuccess(authResult: AuthResult) {
        authState.value = OnAuthSuccess(authResult)
    }

    private fun onError(throwable: Throwable?) {
        authState.value = AuthError(throwable!!.message)
    }

    fun getCurrentUser(): FirebaseUser? {
        return userRepository.currentUser()
    }

    fun getAuthState(): LiveData<AuthState> {
        return authState
    }
}
