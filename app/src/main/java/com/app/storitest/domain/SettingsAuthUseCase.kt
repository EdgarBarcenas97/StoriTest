package com.app.storitest.domain

import com.app.storitest.data.SettingsRepository
import javax.inject.Inject

class SettingsAuthUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    fun readInitSession() = settingsRepository.readInitSession()

    suspend fun initSession() = settingsRepository.initSession()

}