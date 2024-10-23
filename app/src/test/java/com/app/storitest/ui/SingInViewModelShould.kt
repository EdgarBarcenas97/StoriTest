package com.app.storitest.ui

import androidx.lifecycle.viewModelScope
import com.app.storitest.core.TestDispatcherRule
import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.exception.AuthException
import com.app.storitest.domain.SettingsSessionUseCase
import com.app.storitest.domain.SignInUseCase
import com.app.storitest.fakeData.ANY_PASSWORD
import com.app.storitest.fakeData.ANY_USER_EMAIL
import com.app.storitest.fakeData.ANY_USER_ID
import com.app.storitest.ui.features.auth.signin.SignInUiModelState
import com.app.storitest.ui.features.auth.signin.SingInViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SingInViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val signInUseCase = mock<SignInUseCase>()
    private val settingsSessionUseCase = mock<SettingsSessionUseCase>()

    private lateinit var singInViewModel: SingInViewModel

    @Before
    fun setup() {
        singInViewModel = SingInViewModel(signInUseCase, settingsSessionUseCase, testDispatcherRule.coroutinesDispatchers)
    }


    @Test
    fun `Success from signInUiModelState when signIn is called and signInUseCase is success`() = runTest {
        val resultUserId = Result.success(ANY_USER_ID)
        whenever(signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(resultUserId)
        singInViewModel.signIn(ANY_USER_EMAIL, ANY_PASSWORD)

        val result = singInViewModel.signInUiModelState.firstOrNull()

        verify(signInUseCase).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<SignInUiModelState.Success>(result)
    }

    @Test
    fun `Get SignInException from signInUiModelState when signIn is called and signInUseCase is failure`() = runTest {
        val resultSignInException: Result<String> = Result.failure(AuthException.SignInException())
        whenever(signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(resultSignInException)
        singInViewModel.signIn(ANY_USER_EMAIL, ANY_PASSWORD)

        val result = singInViewModel.signInUiModelState.firstOrNull()

        verify(signInUseCase).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<SignInUiModelState.Error>(result)
        assertThatIsInstanceOf<AuthException.SignInException>((result as SignInUiModelState.Error).error)
    }

    @Test
    fun `navigate to signUp when navigateToSingUp is called`() = runTest {
        var result: Unit? = null
        singInViewModel.viewModelScope.launch { result = singInViewModel.navigateToSingUp.firstOrNull() }

        singInViewModel.navigateToSingUp()

        assertThatEquals(result, Unit)
    }

    @Test
    fun `navigate to home when navigateToHome is called`() = runTest {
        var result: Unit? = null
        singInViewModel.viewModelScope.launch { result = singInViewModel.navigateToHome.firstOrNull() }

        singInViewModel.navigateToHome()

        assertThatEquals(result, Unit)
    }
}
