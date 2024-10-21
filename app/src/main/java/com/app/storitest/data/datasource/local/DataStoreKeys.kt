package com.app.storitest.data.datasource.local

import androidx.datastore.preferences.core.booleanPreferencesKey

class DataStoreKeys {

    companion object {
        val SESSION = booleanPreferencesKey("com.app.storitest.SESSION")
    }
}
