package com.app.storitest.domain

import com.app.storitest.data.SettingsRepository
import javax.inject.Inject

class SettingsSessionUseCase @Inject constructor(private val settingsRepository: SettingsRepository,
                                                 private val settingsAuthUseCase: SettingsAuthUseCase) {

    suspend fun setSession() = settingsAuthUseCase.initSession()

    suspend fun deleteDataPreferences() = settingsRepository.deleteDataPreferences()
}
