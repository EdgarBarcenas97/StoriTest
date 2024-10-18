package com.app.storitest.ui.features.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storitest.core.CoroutinesDispatchers
import com.app.storitest.domain.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.withContext

@HiltViewModel
class SingInViewModel @Inject constructor(private val signInUseCase: SignInUseCase,
                                          private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private val _signInUiModelState = MutableStateFlow<SignInUiModelState?>(null)
    val signInUiModelState: StateFlow<SignInUiModelState?>
        get() = _signInUiModelState

    private val _navigateToSingUp = MutableSharedFlow<Unit>()
    val navigateToSingUp: SharedFlow<Unit>
        get() = _navigateToSingUp

    private val _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome: SharedFlow<Unit>
        get() = _navigateToHome

    fun signIn(email: String, password: String) {
        emitSignInUiState(SignInUiModelState.Loading)
        viewModelScope.launch(coroutinesDispatchers.io) {
            val result = signInUseCase.signIn(email, password)
            withContext(coroutinesDispatchers.main) {
                if (result.isSuccess) {
                    signInSuccess()
                } else {
                    signInError(result.exceptionOrNull())
                }
            }
        }
    }

    private fun signInSuccess() {
        emitSignInUiState(SignInUiModelState.Success)
    }

    private fun signInError(result: Throwable?) {
        result?.printStackTrace()
        emitSignInUiState(SignInUiModelState.Error(result))
    }

    private fun emitSignInUiState(signInUiModelState: SignInUiModelState) {
        _signInUiModelState.value = signInUiModelState
    }

    fun navigateToSingUp() = viewModelScope.launch {
        _navigateToSingUp.emit(Unit)
    }

    fun navigateToHome() = viewModelScope.launch {
        _navigateToHome.emit(Unit)
    }
}
