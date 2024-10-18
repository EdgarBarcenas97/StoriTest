package com.app.storitest.data

import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.datasource.AuthRemoteDataSource
import com.app.storitest.data.datasource.UserRemoteDataSource
import com.app.storitest.data.exception.AuthException
import com.app.storitest.data.exception.UserException
import com.app.storitest.fakeData.ANY_LOCAL_PICTURE_USER
import com.app.storitest.fakeData.ANY_PASSWORD
import com.app.storitest.fakeData.ANY_PICTURE
import com.app.storitest.fakeData.ANY_USER_EMAIL
import com.app.storitest.fakeData.ANY_USER_ID
import com.app.storitest.fakeData.givenUserMap
import com.app.storitest.fakeData.givenUserRegister
import com.app.storitest.fakeData.givenUserRegisterWithLocalPictureUser
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthRepositoryShould {

    private val authRemoteDataSource = mock<AuthRemoteDataSource>()
    private val userRemoteDataSource = mock<UserRemoteDataSource>()

    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        authRepository = AuthRepository(authRemoteDataSource, userRemoteDataSource)
    }

    @Test
    fun `Return UserId when signUp is called and signUp and storeUser in datasource are success`() = runTest {
        val userRegister = givenUserRegisterWithLocalPictureUser()
        val userMap = givenUserMap()
        val resultPicture = Result.success(ANY_PICTURE)
        val resultUserId = Result.success(ANY_USER_ID)
        whenever(authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))
        whenever(userRemoteDataSource.savePictureUser(ANY_LOCAL_PICTURE_USER)).thenReturn(flowOf(resultPicture))
        whenever(userRemoteDataSource.saveUser(userMap)).thenReturn(flowOf(resultUserId))

        val result = authRepository.signUp(userRegister).lastOrNull()

        verify(authRemoteDataSource).signUp(ANY_USER_EMAIL, ANY_PASSWORD)
        verify(userRemoteDataSource).savePictureUser(ANY_LOCAL_PICTURE_USER)
        verify(userRemoteDataSource).saveUser(userMap)
        assertThatEquals(result?.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignUpException when signUp is called and signUp in datasource is failure`() = runTest {
        val userRegister = givenUserRegister()
        val resultSignUpException: Result<String> = Result.failure(AuthException.SignUpException())
        whenever(authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignUpException))

        val result = authRepository.signUp(userRegister).lastOrNull()

        verify(authRemoteDataSource).signUp(ANY_USER_EMAIL, ANY_PASSWORD)
        verify(userRemoteDataSource, never()).savePictureUser(ANY_LOCAL_PICTURE_USER)
        verify(userRemoteDataSource, never()).saveUser(any())
        assertThatIsInstanceOf<AuthException.SignUpException>(result?.exceptionOrNull())
    }

    @Test
    fun `Get SavePictureUserException when signUp is called and storagePictureIdentification in datasource is failure`() = runTest {
        val userRegister = givenUserRegisterWithLocalPictureUser()
        val userMap = givenUserMap()
        val resultUserId = Result.success(ANY_USER_ID)
        val resultSavePictureUserException: Result<String> = Result.failure(UserException.SavePictureUserException())
        whenever(authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))
        whenever(userRemoteDataSource.savePictureUser(ANY_LOCAL_PICTURE_USER)).thenReturn(flowOf(resultSavePictureUserException))

        val result = authRepository.signUp(userRegister).lastOrNull()

        verify(authRemoteDataSource).signUp(ANY_USER_EMAIL, ANY_PASSWORD)
        verify(userRemoteDataSource).savePictureUser(ANY_LOCAL_PICTURE_USER)
        verify(userRemoteDataSource, never()).saveUser(userMap)
        assertThatIsInstanceOf<UserException.SavePictureUserException>(result?.exceptionOrNull())
    }

    @Test
    fun `Get SaveUserException when signUp is called and saveUser in datasource is failure`() = runTest {
        val userRegister = givenUserRegisterWithLocalPictureUser()
        val userMap = givenUserMap()
        val resultPicture = Result.success(ANY_PICTURE)
        val resultUserId = Result.success(ANY_USER_ID)
        val resultSaveUserException: Result<String> = Result.failure(UserException.SaveUserException())
        whenever(authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))
        whenever(userRemoteDataSource.savePictureUser(ANY_LOCAL_PICTURE_USER)).thenReturn(flowOf(resultPicture))
        whenever(userRemoteDataSource.saveUser(userMap)).thenReturn(flowOf(resultSaveUserException))

        val result = authRepository.signUp(userRegister).lastOrNull()

        verify(authRemoteDataSource).signUp(ANY_USER_EMAIL, ANY_PASSWORD)
        verify(userRemoteDataSource).savePictureUser(ANY_LOCAL_PICTURE_USER)
        verify(userRemoteDataSource).saveUser(userMap)
        assertThatIsInstanceOf<UserException.SaveUserException>(result?.exceptionOrNull())
    }

    @Test
    fun `Return UserId when signIn is called and signIn in datasource is success`() = runTest {
        val resultUserId = Result.success(ANY_USER_ID)
        whenever(authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRemoteDataSource).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result?.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignInException when signIn is called and signIn in datasource is failure`() = runTest {
        val resultSignInException: Result<String> = Result.failure(AuthException.SignInException())
        whenever(authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignInException))

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRemoteDataSource).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<AuthException.SignInException>(result?.exceptionOrNull())
    }
}
