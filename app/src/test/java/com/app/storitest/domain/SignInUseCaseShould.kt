package com.app.storitest.domain

import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.AuthRepository
import com.app.storitest.data.exception.AuthException.SignInException
import com.app.storitest.fakeData.ANY_PASSWORD
import com.app.storitest.fakeData.ANY_USER_EMAIL
import com.app.storitest.fakeData.ANY_USER_ID
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SignInUseCaseShould {

    private val authRepository = mock<AuthRepository>()

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setup() {
        signInUseCase = SignInUseCase(authRepository)
    }

    @Test
    fun `Return UserId when signIn is called and signIn repository are success`() = runTest {
        val resultUserId = Result.success(ANY_USER_ID)
        whenever(authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(resultUserId)

        val result = signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD)

        verify(authRepository).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignInException when signIn is called and signIn in repository is failure`() = runTest {
        val resultSignUpException: Result<String> = Result.failure(SignInException())
        whenever(authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(resultSignUpException)

        val result = signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD)

        verify(authRepository).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<SignInException>(result.exceptionOrNull())
    }
}
