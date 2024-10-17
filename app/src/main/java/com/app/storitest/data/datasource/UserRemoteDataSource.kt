package com.app.storitest.data.datasource

import android.net.Uri
import com.app.storitest.data.exception.UserException
import com.app.storitest.data.models.TransactionDetailFirestore
import com.app.storitest.data.models.UserFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                               private val firebaseStorage: FirebaseStorage,
                                               private val firebaseFirestore: FirebaseFirestore) {

    fun savePictureUser(picture: String): Flow<Result<String>> = callbackFlow {
        val uri = Uri.parse(picture)
        val mountainImagesRef = firebaseStorage.reference.child("$PATH_IMAGE/${uri.lastPathSegment}")
        mountainImagesRef.putFile(uri)
            .addOnSuccessListener {
                mountainImagesRef.downloadUrl.addOnSuccessListener {
                    trySend(Result.success(it.toString()))
                }.addOnFailureListener {
                    trySend(Result.failure(UserException.SavePictureUserException()))
                }
            }.addOnFailureListener {
                trySend(Result.failure(UserException.SavePictureUserException()))
            }
        awaitClose { close() }
    }

    fun saveUser(userMap: Map<String, String>): Flow<Result<String>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(USERS_COLLECTION)
            .document(userId)
            .set(userMap)
            .addOnSuccessListener { trySend(Result.success(userId)) }
            .addOnFailureListener { trySend(Result.failure(UserException.SaveUserException())) }
        awaitClose { close() }
    }

    fun getUser(): Flow<Result<UserFirestore?>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .addOnSuccessListener {
                trySend(
                    if (it.exists()) Result.success(it.toObject<UserFirestore>())
                    else Result.failure(UserException.GetUserException())
                )
            }
            .addOnFailureListener { trySend(Result.failure(UserException.GetUserException())) }
        awaitClose { close() }
    }

    fun getTransactionDetail(transactionId: String): Flow<Result<TransactionDetailFirestore?>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid.orEmpty()
        firebaseFirestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(TRANSACTIONS_COLLECTION)
            .document(transactionId)
            .get()
            .addOnSuccessListener { trySend(
                if (it.exists()) Result.success(it.toObject<TransactionDetailFirestore>())
                else Result.failure(UserException.GetUserException()))
            }
            .addOnFailureListener {
                it.printStackTrace()
                trySend(Result.failure(UserException.GetUserException()))
            }
        awaitClose { close() }
    }

    companion object {
        const val USERS_COLLECTION = "users"
        const val TRANSACTIONS_COLLECTION = "transactions"
        const val PATH_IMAGE = "images"
    }
}