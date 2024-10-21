package com.app.storitest.ui.features.auth.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storitest.core.CoroutinesDispatchers
import com.app.storitest.domain.SettingsAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val settingsAuthUseCase: SettingsAuthUseCase,
                                              private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private var _initSession = MutableStateFlow(InitSession(isLoading = true))
    val initSession: StateFlow<InitSession> = _initSession.asStateFlow()

    init {
        getSession()
    }

    private fun getSession() {
        viewModelScope.launch(coroutinesDispatchers.io) {
            val isInitSession = settingsAuthUseCase.readInitSession().first()
            _initSession.value = InitSession(isInitSession = isInitSession, isLoading = false)
        }
    }


}

data class InitSession(val isInitSession: Boolean = false, val isLoading: Boolean = true)
