package com.app.storitest.data

import com.app.storitest.data.datasource.AuthRemoteDataSource
import com.app.storitest.data.datasource.UserRemoteDataSource
import com.app.storitest.domain.models.UserRegister
import com.app.storitest.domain.models.toUserRegisterMap
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authRemoteDataSource: AuthRemoteDataSource,
                                         private val userRemoteDataSource: UserRemoteDataSource) {

    suspend fun signIn(email: String, password: String): Result<String> = authRemoteDataSource.signIn(email, password)

    suspend fun signUp(userRegister: UserRegister): Result<String> {
        val result = authRemoteDataSource.signUp(userRegister.email, userRegister.password)
        if (result.isSuccess) {
            val resultUrl = userRemoteDataSource.savePictureUser(userRegister.picture)
            if (resultUrl.isSuccess) userRegister.picture = resultUrl.getOrNull().orEmpty() else return resultUrl
            return if (result.isSuccess) userRemoteDataSource.saveUser(userRegister.toUserRegisterMap()) else result
        }
        return result
    }
}