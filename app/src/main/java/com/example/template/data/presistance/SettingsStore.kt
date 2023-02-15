package com.example.template.data.presistance

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.template.utils.constants.SettingsStoreKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsStore @Inject constructor(
    private val context: Context,
) {
    private val Context.dataStore by preferencesDataStore(
        name = data_store
    )

    suspend fun setUserLoggedInStatus(status: Boolean) {
        context.dataStore.edit {
            it[SettingsStoreKeys.USER_LOGGED_IN_STATUS] = status
        }
    }

    suspend fun getUserLoggedInStatus(): Boolean {
        val status = context.dataStore.data.first()
        return status[SettingsStoreKeys.USER_LOGGED_IN_STATUS] ?: false
    }

    fun observeUserLoggedInStatus(): Flow<Boolean> = context.dataStore.data.map {
        it[SettingsStoreKeys.USER_LOGGED_IN_STATUS] ?: false
    }

    companion object {
        private const val data_store = "settings_data_store"
    }
}