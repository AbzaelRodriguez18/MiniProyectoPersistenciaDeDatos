package com.example.miniproyectopersistenciadedatos.data


import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("settings")

class ManejadorDeAjustes(context: Context) {
    private val dataStore = context.dataStore
    val ModoOscuro: Flow<Boolean> = dataStore.data.map { it[booleanPreferencesKey("dark")] ?: false }
    val idioma: Flow<String> = dataStore.data.map { it[stringPreferencesKey("lang")] ?: "Español" }

    suspend fun saveSettings(isDark: Boolean, lang: String) {
        dataStore.edit { it[booleanPreferencesKey("dark")] = isDark; it[stringPreferencesKey("lang")] = lang }
    }
}