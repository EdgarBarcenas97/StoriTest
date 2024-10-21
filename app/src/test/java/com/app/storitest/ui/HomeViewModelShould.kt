package com.app.storitest.ui

import com.app.storitest.core.TestDispatcherRule
import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.exception.UserException
import com.app.storitest.domain.GetUserUseCase
import com.app.storitest.domain.models.User
import com.app.storitest.fakeData.givenUser
import com.app.storitest.fakeData.givenUserUi
import com.app.storitest.ui.features.home.HomeViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class HomeViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val getUserUseCase = mock<GetUserUseCase>()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(getUserUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `get UserDataUi from userDataUiState when getUserData is called and getUserDataUseCase is success`() = runTest {
        val userData = givenUser()
        val userDataUi = givenUserUi()
        val resultUserData = Result.success(userData)

        whenever(getUserUseCase.getUser()).thenReturn(resultUserData)

        homeViewModel.getUser()

        val result = homeViewModel.userUiModelState.firstOrNull()

        verify(getUserUseCase).getUser()
        assertThatEquals((result)?.userUi, userDataUi)
    }

    @Test
    fun `Get GetUserException from userDataUiState when getUser is called and getUserUseCase is failure`() = runTest {
        val resultGetUserException: Result<User> = Result.failure(UserException.GetUserException())

        whenever(getUserUseCase.getUser()).thenReturn(resultGetUserException)

        homeViewModel.getUser()

        val result = homeViewModel.userUiModelState.firstOrNull()

        verify(getUserUseCase).getUser()
        assertThatIsInstanceOf<UserException.GetUserException>((result?.error))
    }

}
