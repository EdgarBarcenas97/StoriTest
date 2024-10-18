package com.app.storitest.domain

import com.app.storitest.data.AuthRepository
import com.app.storitest.domain.models.UserRegister
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun signUp(userRegister: UserRegister) = authRepository.signUp(userRegister)
}
