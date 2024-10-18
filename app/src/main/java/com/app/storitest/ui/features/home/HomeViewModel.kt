package com.app.storitest.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storitest.core.CoroutinesDispatchers
import com.app.storitest.domain.GetUserUseCase
import com.app.storitest.domain.models.User
import com.app.storitest.ui.features.home.data.toUserUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.withContext

@HiltViewModel
class HomeViewModel @Inject constructor(private val getUserDataUseCase: GetUserUseCase,
                                        private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private val _userUiModelState = MutableStateFlow<UserUiModelState?>(null)
    val userUiModelState: StateFlow<UserUiModelState?>
        get() = _userUiModelState

    private val _navigateToTransactionDetail = MutableSharedFlow<String>()
    val navigateToTransactionDetail: SharedFlow<String>
        get() = _navigateToTransactionDetail

    fun getUserData() {
        emitUserDataUiState(UserUiModelState.Loading)
        viewModelScope.launch(coroutinesDispatchers.io) {
            val result = getUserDataUseCase.getUser()
            withContext(coroutinesDispatchers.main) {
                if (result.isSuccess) {
                    result.getOrNull()?.let { getUserDataSuccess(it) }
                } else {
                    result.exceptionOrNull()?.let { getUserError(it) }
                }
            }
        }
    }

    private fun getUserDataSuccess(result: User) {
        emitUserDataUiState(UserUiModelState.Success(result.toUserUi()))
    }

    private fun getUserError(result: Throwable) {
        result.printStackTrace()
        emitUserDataUiState(UserUiModelState.Error(result))
    }

    private fun emitUserDataUiState(signUpUiState: UserUiModelState) {
        _userUiModelState.value = signUpUiState
    }

    fun navigateToTransactionDetail(transactionId: String) = viewModelScope.launch {
        _navigateToTransactionDetail.emit(transactionId)
    }
}
