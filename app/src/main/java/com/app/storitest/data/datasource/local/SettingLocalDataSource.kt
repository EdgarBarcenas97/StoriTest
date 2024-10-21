package com.app.storitest.data.datasource.local

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class SettingLocalDataSource @Inject constructor(private val context: Application) : SettingDataStore {

    override fun readInitSession(): Flow<Boolean> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences()) else throw exception
            }.map { preferences ->
                val session = preferences[DataStoreKeys.SESSION] ?: false
                session
            }
    }

    override suspend fun initSession() {
        context.dataStore.edit { preferences ->
            val current = preferences[DataStoreKeys.SESSION] ?: false
            preferences[DataStoreKeys.SESSION] = !current
        }
    }

    override suspend fun deleteDataPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private const val SETTINGS_PREFERENCES_NAME: String = "settings_preferences"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(SETTINGS_PREFERENCES_NAME)
    }
}
