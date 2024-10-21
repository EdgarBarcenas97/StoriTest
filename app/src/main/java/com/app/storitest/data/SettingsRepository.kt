package com.app.storitest.data

import com.app.storitest.data.datasource.local.SettingLocalDataSource
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val settingLocalDataSource: SettingLocalDataSource) {

    fun readInitSession() = settingLocalDataSource.readInitSession()

    suspend fun initSession() = settingLocalDataSource.initSession()

    suspend fun deleteDataPreferences() = settingLocalDataSource.deleteDataPreferences()

}
