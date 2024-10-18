package com.app.storitest.domain

import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.UserRepository
import com.app.storitest.data.exception.UserException.GetUserException
import com.app.storitest.domain.models.User
import com.app.storitest.fakeData.givenUser
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetUserUseCaseShould {

    private val userRepository = mock<UserRepository>()

    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setup() {
        getUserUseCase = GetUserUseCase(userRepository)
    }

    @Test
    fun `Return User when getUser is called and getUser repository are success`() = runTest {
        val user = givenUser()
        val resultUser = Result.success(user)
        whenever(userRepository.getUser()).thenReturn(flowOf(resultUser))

        val result = getUserUseCase.getUser().lastOrNull()

        verify(userRepository).getUser()
        assertThatEquals(result?.getOrNull(), user)
    }

    @Test
    fun `Get GetUserException when getUser is called and getUser in repository is failure`() = runTest {
        val resultGetUserException: Result<User> = Result.failure(GetUserException())
        whenever(userRepository.getUser()).thenReturn(flowOf(resultGetUserException))

        val result = getUserUseCase.getUser().lastOrNull()

        verify(userRepository).getUser()
        assertThatIsInstanceOf<GetUserException>(result?.exceptionOrNull())
    }
}
