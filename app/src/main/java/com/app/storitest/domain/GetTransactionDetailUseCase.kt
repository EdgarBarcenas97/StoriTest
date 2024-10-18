package com.app.storitest.domain

import com.app.storitest.data.UserRepository
import javax.inject.Inject

class GetTransactionDetailUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getTransactionDetail(transactionId: String) = userRepository.getTransactionDetail(transactionId)
}
