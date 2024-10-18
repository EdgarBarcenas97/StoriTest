package com.app.storitest.domain

import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.AuthRepository
import com.app.storitest.data.exception.AuthException
import com.app.storitest.fakeData.ANY_USER_ID
import com.app.storitest.fakeData.givenUserRegister
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SignUpUseCaseShould {

    private val authRepository = mock<AuthRepository>()

    private lateinit var signUpUseCase: SignUpUseCase

    @Before
    fun setup() {
        signUpUseCase = SignUpUseCase(authRepository)
    }

    @Test
    fun `Return UserId when signUp is called and signUp repository are success`() = runTest {
        val userRegister = givenUserRegister()
        val resultUserId = Result.success(ANY_USER_ID)
        whenever(authRepository.signUp(userRegister)).thenReturn(resultUserId)

        val result = signUpUseCase.signUp(userRegister)

        verify(authRepository).signUp(userRegister)
        assertThatEquals(result.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignUpException when signUp is called and signUp in repository is failure`() = runTest {
        val userRegister = givenUserRegister()
        val resultSignUpException: Result<String> = Result.failure(AuthException.SignUpException())
        whenever(authRepository.signUp(userRegister)).thenReturn(resultSignUpException)

        val result = signUpUseCase.signUp(userRegister)

        verify(authRepository).signUp(userRegister)
        assertThatIsInstanceOf<AuthException.SignUpException>(result.exceptionOrNull())
    }
}
