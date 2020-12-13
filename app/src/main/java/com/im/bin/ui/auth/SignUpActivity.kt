package com.im.bin.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.AuthResult
import com.im.bin.R
import com.im.bin.appUtils.Util
import com.im.bin.databinding.ActivitySignUpBinding
import com.im.bin.models.User
import com.im.bin.ui.base.BaseAuthActivity
import com.im.bin.viewModel.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import javax.inject.Inject


class SignUpActivity : BaseAuthActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val signUpViewModel: SignUpViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        initViews()
    }

    override fun initViews() {
        val binding: ActivitySignUpBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        binding.viewModel = signUpViewModel
        signUpViewModel.getSignUpState().observe(this, Observer {
            setState(it)
        })

        signUpViewModel.getUser().observe(this, Observer {
            signUp(it)
        })

        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun setState(authState: AuthState) {
        when (authState) {
            is Loading -> onLoading()
            is AuthError -> onAuthError(authState.message)
            is OnAuthSuccess -> onSuccess(authState.authResult)
        }
    }

    private fun onLoading() {
        showProgressBar()
    }

    private fun signUp(user: User) {
        val emailText = user.email
        val passwordText = user.password
        when {
            emailText.isEmpty() -> {
                email.error = "Please Enter Email"
            }
            !Util.checkEmailAddressValid(emailText) -> {
                email.error = "Please Enter a Valid Email"
            }
            passwordText.isEmpty() -> {
                password.error = "Please enter a Password"
            }
            passwordText.length < 6 -> {
                password.error = "Password at least 6 characters"
            }
            else -> {
                signUpViewModel.signUp(user)
            }
        }
    }

    private fun onSuccess(authResult: AuthResult) {
        hideProgressBar()
        Toast.makeText(
            this@SignUpActivity,
            "Signed Up Successfully",
            Toast.LENGTH_LONG
        ).show()
        finish()
    }

    private fun onAuthError(error: String?) {
        hideProgressBar()
        error?.let {
            Util.showCustomAlertDialog(
                this@SignUpActivity,
                "Error",
                error
            )
        }
    }

    private fun showProgressBar() {
        signUpViewModel.setProgressBarState(true)
        setWindowNotTouchable()
    }

    private fun hideProgressBar() {
        signUpViewModel.setProgressBarState(false)
        clearWindowTouchableFlag()
    }
}