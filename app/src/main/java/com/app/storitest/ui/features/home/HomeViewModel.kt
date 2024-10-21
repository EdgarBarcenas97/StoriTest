package com.app.storitest.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storitest.core.CoroutinesDispatchers
import com.app.storitest.domain.GetUserUseCase
import com.app.storitest.domain.models.User
import com.app.storitest.ui.features.home.data.UserUi
import com.app.storitest.ui.features.home.data.UserUiModelState
import com.app.storitest.ui.features.home.data.toUserUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.withContext

@HiltViewModel
class HomeViewModel @Inject constructor(private val getUserDataUseCase: GetUserUseCase,
                                        private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private val _userUiModelState = MutableStateFlow(UserUiModelState())
    val userUiModelState: StateFlow<UserUiModelState>
        get() = _userUiModelState

    init {
        getUser()
    }

    private fun getUser() {
        emitUserDataUiState(loading = true)
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
        emitUserDataUiState(userUi = result.toUserUi())
    }

    private fun getUserError(result: Throwable) {
        result.printStackTrace()
        emitUserDataUiState(error = result)
    }

    private fun emitUserDataUiState(loading: Boolean = false, userUi: UserUi? = null, error: Throwable? = null) {
        val userUiModelState = UserUiModelState(loading = loading, userUi = userUi, error = error)
        _userUiModelState.value = userUiModelState
    }

}
