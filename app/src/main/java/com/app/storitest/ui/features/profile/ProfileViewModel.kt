package com.app.storitest.ui.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storitest.core.CoroutinesDispatchers
import com.app.storitest.domain.GetUserUseCase
import com.app.storitest.domain.models.User
import com.app.storitest.ui.features.home.data.toUserUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ProfileViewModel @Inject constructor(private val getUserDataUseCase: GetUserUseCase,
                                           private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private val _profileUiModelState = MutableStateFlow<ProfileUiModelState?>(null)
    val profileUiModelState: StateFlow<ProfileUiModelState?>
        get() = _profileUiModelState

    init{
        getUser()
    }

    fun getUser() {
        emitUserDataUiState(ProfileUiModelState.Loading)
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
        emitUserDataUiState(ProfileUiModelState.Success(result.toUserUi()))
    }

    private fun getUserError(result: Throwable) {
        result.printStackTrace()
        emitUserDataUiState(ProfileUiModelState.Error(result))
    }

    private fun emitUserDataUiState(profileUiModelState: ProfileUiModelState) {
        _profileUiModelState.value = profileUiModelState
    }
}
