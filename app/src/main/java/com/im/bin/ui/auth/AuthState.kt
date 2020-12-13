package com.im.bin.ui.auth

import com.google.firebase.auth.AuthResult

sealed class AuthState
data class AuthError(val message: String?) : AuthState()
object Loading : AuthState()
class OnAuthSuccess(val authResult: AuthResult): AuthState()