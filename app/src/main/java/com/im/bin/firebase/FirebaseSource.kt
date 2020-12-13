package com.im.bin.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.im.bin.models.IMMessage
import com.im.bin.models.User
import io.reactivex.Completable
import io.reactivex.Single

interface FirebaseSource {

    fun login(user: User): Single<AuthResult>
    fun uploadConversation(imType: String, imMessage: IMMessage): Completable
    fun fetchCovnersation(imType: String, conversationId: String): Single<List<IMMessage>>
    fun fetchConversations(imType: String): Single<List<IMMessage>>
    fun deleteConversation(imType: String, conversationId: String): Completable
    fun deleteChats(imType: String): Completable
    fun logOut()
    fun createAccount(user: User): Single<AuthResult>
    fun currentUser(): FirebaseUser?
    fun setUser(user: User): Completable
}