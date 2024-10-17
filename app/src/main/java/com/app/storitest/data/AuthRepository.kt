package com.app.storitest.data

import com.app.storitest.data.datasource.AuthRemoteDataSource
import com.app.storitest.data.datasource.UserRemoteDataSource
import com.app.storitest.domain.models.UserRegister
import com.app.storitest.domain.models.toUserRegisterMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class AuthRepository @Inject constructor(private val authRemoteDataSource: AuthRemoteDataSource,
                                         private val userRemoteDataSource: UserRemoteDataSource) {

    fun signIn(email: String, password: String): Flow<Result<String>> =
        authRemoteDataSource.signIn(email, password)

    fun signUp(userRegister: UserRegister): Flow<Result<String>> =
        authRemoteDataSource.signUp(userRegister.email, userRegister.password)
            .flatMapConcat { if (it.isSuccess) userRemoteDataSource.savePictureUser(userRegister.picture) else flowOf(it) }
            .onEach { if (it.isSuccess) userRegister.picture = it.getOrNull().orEmpty() }
            .flatMapConcat { if (it.isSuccess) userRemoteDataSource.saveUser(userRegister.toUserRegisterMap()) else flowOf(it) }
}