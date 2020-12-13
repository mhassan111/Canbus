package com.im.bin.respository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.im.bin.models.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun login(user: User): Single<AuthResult>
    fun createAccount(user: User): Single<AuthResult>
    fun currentUser(): FirebaseUser?
    fun logOut()
    fun setUser(user: User): Completable
}