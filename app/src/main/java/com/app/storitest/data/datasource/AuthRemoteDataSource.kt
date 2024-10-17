package com.app.storitest.data.datasource

import com.app.storitest.data.exception.AuthException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    fun signIn(email: String, password: String): Flow<Result<String>> = callbackFlow {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { trySend(Result.success(it.user?.uid.orEmpty())) }
            .addOnFailureListener { trySend(Result.failure(AuthException.SignInException())) }
        awaitClose { close() }
    }

    fun signUp(email: String, password: String): Flow<Result<String>> = callbackFlow {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { trySend(Result.success(it.user?.uid.orEmpty())) }
            .addOnFailureListener { trySend(Result.failure(getAuthenticationException(it))) }
        awaitClose { close() }
    }

    private fun getAuthenticationException(it: Exception) =
        if (it is FirebaseAuthUserCollisionException) AuthException.UserAlreadyExistException()
        else AuthException.SignUpException()
}