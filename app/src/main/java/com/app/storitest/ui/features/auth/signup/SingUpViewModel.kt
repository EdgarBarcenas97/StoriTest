package com.app.storitest.ui.features.auth.signup

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storitest.core.CoroutinesDispatchers
import com.app.storitest.core.extensions.empty
import com.app.storitest.domain.SettingsSessionUseCase
import com.app.storitest.domain.SignUpUseCase
import com.app.storitest.ui.features.auth.signup.models.UserRegisterUi
import com.app.storitest.ui.features.auth.signup.models.toUserRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SingUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase,
                                          private val settingsSessionUseCase: SettingsSessionUseCase,
                                          private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private val _signUpUiModelState = MutableStateFlow<SignUpUiModelState?>(null)
    val signUpUiModelState: StateFlow<SignUpUiModelState?>
        get() = _signUpUiModelState

    private val _userRegisterUiModelState = MutableStateFlow<UserRegisterUi?>(null)
    val userRegisterUiModelState: StateFlow<UserRegisterUi?>
        get() = _userRegisterUiModelState

    private val _pictureUiModelState = MutableStateFlow(String.empty())
    val pictureUiModelState: StateFlow<String>
        get() = _pictureUiModelState

    private val _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome: SharedFlow<Unit>
        get() = _navigateToHome

    fun saveUserRegisterUi(userRegisterUi: UserRegisterUi) {
        _userRegisterUiModelState.value = userRegisterUi
    }

    fun savePictureUi(uri: Uri) {
        _userRegisterUiModelState.value = _userRegisterUiModelState.value?.copy(pictureIdentification = uri.toString())
    }

    fun singUp(userRegisterUi: UserRegisterUi) {
        emitSignInUiState(SignUpUiModelState.Loading)
        viewModelScope.launch(coroutinesDispatchers.io) {
            val result = signUpUseCase.signUp(userRegisterUi.toUserRegister())
            withContext(coroutinesDispatchers.main) {
                if (result.isSuccess) {
                    signUpSuccess()
                } else {
                    signUpError(result.exceptionOrNull())
                }
            }
        }
    }

    private fun signUpSuccess() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            settingsSessionUseCase.setSession()
        }
        emitSignInUiState(SignUpUiModelState.Success)
    }

    private fun signUpError(result: Throwable?) {
        result?.printStackTrace()
        emitSignInUiState(SignUpUiModelState.Error(result))
    }

    private fun emitSignInUiState(signUpUiModelState: SignUpUiModelState) {
        _signUpUiModelState.value = signUpUiModelState
    }

    fun loadPicture(path: String) {
        _pictureUiModelState.value = path
    }

    fun navigateToHome() = viewModelScope.launch {
        _navigateToHome.emit(Unit)
    }
}
