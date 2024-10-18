package com.app.storitest.domain

import com.app.storitest.data.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun signIn(email: String, password: String) = authRepository.signIn(email, password)
}