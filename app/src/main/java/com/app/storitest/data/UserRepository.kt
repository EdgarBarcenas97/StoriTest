package com.app.storitest.data

import com.app.storitest.data.datasource.remote.UserRemoteDataSource
import com.app.storitest.domain.models.toTransactionDetail
import com.app.storitest.domain.models.toResultUser
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) {

    suspend fun getUser() =
        userRemoteDataSource.getUser().toResultUser()

    suspend fun getTransactionDetail(transactionId: String) =
        userRemoteDataSource.getTransactionDetail(transactionId).toTransactionDetail()
}
