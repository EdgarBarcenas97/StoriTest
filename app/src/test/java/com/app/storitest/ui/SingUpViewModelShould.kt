package com.app.storitest.ui

import androidx.lifecycle.viewModelScope
import com.app.storitest.core.TestDispatcherRule
import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.exception.AuthException
import com.app.storitest.domain.SignUpUseCase
import com.app.storitest.fakeData.ANY_PICTURE
import com.app.storitest.fakeData.ANY_USER_ID
import com.app.storitest.fakeData.givenUserRegister
import com.app.storitest.fakeData.givenUserRegisterUi
import com.app.storitest.ui.features.auth.signup.SignUpUiModelState
import com.app.storitest.ui.features.auth.signup.SingUpViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SingUpViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val signUpUseCase = mock<SignUpUseCase>()

    private lateinit var singUpViewModel: SingUpViewModel

    @Before
    fun setup() {
        singUpViewModel = SingUpViewModel(signUpUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `Success from signUpUiModelState when singUp is called and signUpUseCase is success`() = runTest {
        val userRegisterUi = givenUserRegisterUi()
        val userRegister = givenUserRegister()
        val resultUserId = Result.success(ANY_USER_ID)
        whenever(signUpUseCase.signUp(userRegister)).thenReturn(resultUserId)

        singUpViewModel.singUp(userRegisterUi)

        val result = singUpViewModel.signUpUiModelState.firstOrNull()

        verify(signUpUseCase).signUp(userRegister)
        assertThatIsInstanceOf<SignUpUiModelState.Success>(result)
    }

    @Test
    fun `get SignUpException from signUpUiModelState when singUp is called and signUpUseCase is failure`() = runTest {
        val userRegisterUi = givenUserRegisterUi()
        val userRegister = givenUserRegister()
        val resultSignUpException: Result<String> = Result.failure(AuthException.SignUpException())
        whenever(signUpUseCase.signUp(userRegister)).thenReturn(resultSignUpException)

        singUpViewModel.singUp(userRegisterUi)

        val result = singUpViewModel.signUpUiModelState.firstOrNull()

        verify(signUpUseCase).signUp(userRegister)
        assertThatIsInstanceOf<SignUpUiModelState.Error>(result)
        assertThatIsInstanceOf<AuthException.SignUpException>((result as SignUpUiModelState.Error).error)
    }

    @Test
    fun `pass pictureIdentification when loadPictureIdentification is called`() = runTest {
        singUpViewModel.loadPicture(ANY_PICTURE)

        val result = singUpViewModel.pictureUiModelState.firstOrNull()

        assertThatEquals(result, ANY_PICTURE)
    }

    @Test
    fun `navigate to home when navigateToHome is called`() = runTest {
        var result: Unit? = null
        singUpViewModel.viewModelScope.launch { result = singUpViewModel.navigateToHome.firstOrNull() }

        singUpViewModel.navigateToHome()

        assertThatEquals(result, Unit)
    }
}
