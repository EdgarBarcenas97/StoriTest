package com.app.storitest.data.datasource.remote

import android.net.Uri
import com.app.storitest.data.exception.UserException
import com.app.storitest.data.models.TransactionDetailFirestore
import com.app.storitest.data.models.UserFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRemoteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                               private val firebaseStorage: FirebaseStorage,
                                               private val firebaseFirestore: FirebaseFirestore) {

    suspend fun savePictureUser(picture: String): Result<String> = suspendCoroutine { continuation ->
        val uri = Uri.parse(picture)
        val mountainImagesRef = firebaseStorage.reference.child("$PATH_IMAGE/${uri.lastPathSegment}")
        mountainImagesRef.putFile(uri)
            .addOnSuccessListener {
                mountainImagesRef.downloadUrl.addOnSuccessListener {
                    continuation.resume(Result.success(it.toString()))
                }.addOnFailureListener {
                    continuation.resume(Result.failure(UserException.SavePictureUserException()))
                }
            }.addOnFailureListener {
                continuation.resume(Result.failure(UserException.SavePictureUserException()))
            }
    }

    suspend fun saveUser(userMap: Map<String, String>): Result<String> = suspendCoroutine { continuation ->
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(USERS_COLLECTION)
            .document(userId)
            .set(userMap)
            .addOnSuccessListener { continuation.resume(Result.success(userId)) }
            .addOnFailureListener { continuation.resume(Result.failure(UserException.SaveUserException())) }
    }

    suspend fun getUser(): Result<UserFirestore?> = suspendCoroutine { continuation ->
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .addOnSuccessListener {
                continuation.resume(
                    if (it.exists()) Result.success(it.toObject<UserFirestore>())
                    else Result.failure(UserException.GetUserException())
                )
            }
            .addOnFailureListener { continuation.resume(Result.failure(UserException.GetUserException())) }
    }

    suspend fun getTransactionDetail(transactionId: String): Result<TransactionDetailFirestore?> = suspendCoroutine { continuation ->
        firebaseFirestore.collection(TRANSACTIONS_COLLECTION)
            .document(transactionId)
            .get()
            .addOnSuccessListener {
                continuation.resume(
                    if (it.exists()) Result.success(it.toObject<TransactionDetailFirestore>())
                    else Result.failure(UserException.GetUserException()))
            }
            .addOnFailureListener {
                it.printStackTrace()
                continuation.resume(Result.failure(UserException.GetUserException()))
            }
    }

    companion object {
        const val USERS_COLLECTION = "users"
        const val TRANSACTIONS_COLLECTION = "transactions"
        const val PATH_IMAGE = "images"
    }
}