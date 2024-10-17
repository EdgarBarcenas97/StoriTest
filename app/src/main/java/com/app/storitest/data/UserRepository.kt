package com.app.storitest.data

import com.app.storitest.data.datasource.UserRemoteDataSource
import com.app.storitest.domain.models.toTransactionDetail
import com.app.storitest.domain.models.toResultUser
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) {

    fun getUser() = userRemoteDataSource.getUser().map { it.toResultUser() }

    fun getTransactionDetail(transactionId: String) = userRemoteDataSource.getTransactionDetail(transactionId).map { it.toTransactionDetail() }
}
