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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val settingsAuthUseCase: SettingsAuthUseCase,
                                              private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private var _initSession = MutableStateFlow(InitSession())
    val initSession: StateFlow<InitSession> = _initSession.asStateFlow()

    init {
        getSession()
    }

    private fun getSession() {
        settingsAuthUseCase.readInitSession()
            .flowOn(coroutinesDispatchers.io)
            .onEach { isInitSession ->
                _initSession.value = InitSession(isInitSession)
            }
            .launchIn(viewModelScope)
    }
}

data class InitSession(val isInitSession: Boolean? = null)
