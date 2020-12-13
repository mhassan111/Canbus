package com.im.bin.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.im.bin.models.IMMessage
import com.im.bin.models.User
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import javax.inject.Inject

class FirebaseSourceImpl @Inject constructor() : FirebaseSource {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firebaseFireStore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun login(user: User) =
        Single.create { emitter: SingleEmitter<AuthResult> ->
            firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (!emitter.isDisposed) {
                            val authResult = it.result
                            authResult?.let {
                                emitter.onSuccess(authResult)
                            }
                        }
                    } else {
                        if (!emitter.isDisposed) {
                            emitter.onError(it.exception!!)
                        }
                    }
                }
                .addOnFailureListener {
                    if (!emitter.isDisposed) {
                        emitter.onError(it)
                    }
                }
        }

    override fun uploadConversation(imType: String, imMessage: IMMessage): Completable =
        Completable.create { emitter ->
            val documentReference =
                firebaseFireStore.collection("conversations")
                    .document(firebaseAuth.currentUser?.email!!)
                    .collection(imType).document(imMessage.messageId)
            documentReference.get().addOnSuccessListener { docRef ->
                if (docRef != null && docRef.exists()) {
                    emitter.onComplete()
                } else {
                    documentReference.set(imMessage).addOnSuccessListener {
                        emitter.onComplete()
                    }.addOnFailureListener {
                        emitter.onError(it)
                    }
                }
            }.addOnFailureListener {
                emitter.onError(it)
            }
        }

    override fun fetchCovnersation(
        imType: String,
        conversationId: String
    ): Single<List<IMMessage>> = Single.create { emitter: SingleEmitter<List<IMMessage>> ->
        val documentReference =
            firebaseFireStore.collection("conversations")
                .document(firebaseAuth.currentUser?.email!!)
                .collection(imType)
        documentReference
            .whereEqualTo("conversationId", conversationId)
            .get()
            .addOnSuccessListener {
                val imMessageList = mutableListOf<IMMessage>()
                val documents = it?.documents
                if (!documents.isNullOrEmpty()) {
                    for (document in it.documents) {
                        val imMessage = document.toObject(IMMessage::class.java)
                        imMessage?.let {
                            imMessageList.add(imMessage)
                        }
                    }
                }
                emitter.onSuccess(imMessageList)
            }.addOnFailureListener {
                emitter.onError(it)
            }
    }

    override fun fetchConversations(imType: String): Single<List<IMMessage>> =
        Single.create { emitter: SingleEmitter<List<IMMessage>> ->
            val documentReference =
                firebaseFireStore.collection("conversations")
                    .document(firebaseAuth.currentUser?.email!!)
                    .collection(imType)
            documentReference.get()
                .addOnSuccessListener {
                    val imConversations = mutableListOf<IMMessage>()
                    val documents = it?.documents
                    if (!documents.isNullOrEmpty()) {
                        for (document in it.documents) {
                            val imConversation = document.toObject(IMMessage::class.java)
                            imConversation?.let {
                                imConversations.add(imConversation)
                            }
                        }
                    }
                    emitter.onSuccess(imConversations)
                }.addOnFailureListener {
                    emitter.onError(it)
                }
        }

    override fun deleteConversation(imType: String, conversationId: String): Completable =
        Completable.create { emitter ->
            val documentReference =
                firebaseFireStore.collection("conversations")
                    .document(firebaseAuth.currentUser?.email!!)
                    .collection(imType)
            documentReference
                .whereEqualTo("conversationId", conversationId)
                .get()
                .addOnSuccessListener {
                    val documents = it?.documents
                    if (!documents.isNullOrEmpty()) {
                        for (document in it.documents) {
                            document.reference.delete()
                        }
                    }
                    emitter.onComplete()
                }.addOnFailureListener {
                    emitter.onError(it)
                }
        }

    override fun deleteChats(imType: String): Completable =
        Completable.create { emitter ->
            val documentReference =
                firebaseFireStore.collection("conversations")
                    .document(firebaseAuth.currentUser?.email!!)
                    .collection(imType)
            documentReference
                .get()
                .addOnSuccessListener {
                    val documents = it?.documents
                    if (!documents.isNullOrEmpty()) {
                        for (document in it.documents) {
                            document.reference.delete()
                        }
                    }
                    emitter.onComplete()
                }.addOnFailureListener {
                    emitter.onError(it)
                }
        }

    //    val documentReference =
//        firebaseFirestore.collection("employees").document(email)
//    documentReference.get().addOnSuccessListener { docRef ->
//        if (docRef != null && docRef.exists()) {
//            if (password == docRef.data!!["password"]) {
//                val employee = docRef.toObject(Employee::class.java)
//                if (!emitter.isDisposed) {
//                    emitter.onSuccess(employee!!)
//                }
//            } else {
//                emitter.onError(Throwable("Invalid Password, Try Again"))
//            }
//        } else {
//            emitter.onError(Throwable("Invalid User Credentials"))
//        }
//    }.addOnFailureListener {
//        emitter.onError(it)
//    }

//
//        Completable.create { emitter ->
//            val documentReference =
//                firebaseFirestore.collection("employees").document(email)
//            documentReference.get().addOnSuccessListener { docRef ->
//                if (docRef != null && docRef.exists()) {
//                    if (password == docRef.data!!["password"]) {
//                        val employee = docRef.toObject(Employee::class.java)
//                        SharedPreferencesUtil(MyApplication.getContext()).putObject(
//                            Constants.PREF_USER,
//                            employee
//                        )
//                        emitter.onComplete()
//                    } else {
//                        emitter.onError(Throwable("Invalid Password, Try Again"))
//                    }
//                } else {
//                    emitter.onError(Throwable("Invalid User Credentials"))
//                }
//            }.addOnFailureListener {
//                emitter.onError(it)
//            }
//        }

    override fun createAccount(user: User) =
        Single.create { emitter: SingleEmitter<AuthResult> ->
            firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (!emitter.isDisposed) {
                            val authResult = it.result
                            authResult?.let {
                                emitter.onSuccess(authResult)
                            }
                        } else {
                            if (!emitter.isDisposed) {
                                emitter.onError(it.exception!!)
                            }
                        }
                    }
                }
                .addOnFailureListener {
                    if (!emitter.isDisposed) {
                        emitter.onError(it)
                    }
                }
        }

    override fun setUser(user: User) =
        Completable.create { emitter ->
            val documentReference =
                firebaseFireStore.collection("users").document(user.email)
            documentReference.set(user).addOnSuccessListener {
                emitter.onComplete()
            }.addOnFailureListener {
                emitter.onError(it)
            }
        }

    override fun currentUser(): FirebaseUser? = firebaseAuth.currentUser
    override fun logOut() = firebaseAuth.signOut()
}