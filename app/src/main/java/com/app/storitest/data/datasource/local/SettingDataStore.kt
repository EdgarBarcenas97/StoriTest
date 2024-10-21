package com.app.storitest.data.datasource.local

import kotlinx.coroutines.flow.Flow

interface SettingDataStore {

    suspend fun initSession()

    fun readInitSession(): Flow<Boolean>

    suspend fun deleteDataPreferences()

}
