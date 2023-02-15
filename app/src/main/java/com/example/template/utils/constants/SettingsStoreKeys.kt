package com.example.template.utils.constants

import androidx.datastore.preferences.core.booleanPreferencesKey

object SettingsStoreKeys {
    val USER_LOGGED_IN_STATUS = booleanPreferencesKey(name = "user_logged_in_status")
}