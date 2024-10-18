package com.app.storitest.data.datasource

import com.app.storitest.data.exception.AuthException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRemoteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    suspend fun signIn(email: String, password: String): Result<String> = suspendCoroutine { continuation ->
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { continuation.resume(Result.success(it.user?.uid.orEmpty())) }
            .addOnFailureListener { continuation.resume(Result.failure(AuthException.SignInException())) }
    }

    suspend fun signUp(email: String, password: String): Result<String> = suspendCoroutine { continuation ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { continuation.resume(Result.success(it.user?.uid.orEmpty())) }
            .addOnFailureListener { continuation.resume(Result.failure(getAuthenticationException(it))) }
    }

    private fun getAuthenticationException(it: Exception) =
        if (it is FirebaseAuthUserCollisionException) AuthException.UserAlreadyExistException()
        else AuthException.SignUpException()
}