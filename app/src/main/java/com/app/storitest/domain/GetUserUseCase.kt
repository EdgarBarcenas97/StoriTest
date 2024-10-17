package com.app.storitest.domain

import com.app.storitest.data.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getUser() = userRepository.getUser()
}