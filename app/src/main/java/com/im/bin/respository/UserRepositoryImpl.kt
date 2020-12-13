package com.im.bin.respository

import com.im.bin.firebase.FirebaseSource
import com.im.bin.models.User
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    @Inject
    lateinit var firebaseSource: FirebaseSource

    override fun login(user: User) = firebaseSource.login(user)

    override fun createAccount(user: User) =
        firebaseSource.createAccount(user)

    override fun currentUser() = firebaseSource.currentUser()

    override fun setUser(user: User) = firebaseSource.setUser(user)

    override fun logOut() = firebaseSource.logOut()
}