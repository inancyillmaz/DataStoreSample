package com.codexflow.datastore.repository

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepository(context: Context) {

    private val dataStore = context.createDataStore(name = "user_prefs")

    companion object {

        val USER_NAME_KEY = preferencesKey<String>("USER_NAME")
        val AGE_KEY = preferencesKey<Int>("USER_AGE")

    }

    suspend fun storeUserInfoAge(age: Int) {
        dataStore.edit {
            it[AGE_KEY] = age
        }
    }

    suspend fun storeUserInfoName(name: String) {
        dataStore.edit {
            it[USER_NAME_KEY] = name
        }
    }


    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: "Adınızı Girin"
    }

    val userAgeFlow: Flow<Int> = dataStore.data.map {
        val age = it[AGE_KEY] ?: 0
        age
    }
}