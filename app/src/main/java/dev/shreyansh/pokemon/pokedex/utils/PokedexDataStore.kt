package dev.shreyansh.pokemon.pokedex.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


object PREFS{
    val THEME_KEY = stringPreferencesKey("THEME_KEY")
    val QUIZ_KEY = longPreferencesKey("QUIZ_KEY")
    val LEVELS_KEY = intPreferencesKey("LEVELS_KEY")

}


class PokedexDataStore private constructor(context: Context) {

    companion object {

        @Volatile
        private var INSTANCE: PokedexDataStore? = null

        fun getInstance(context: Context): PokedexDataStore {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = PokedexDataStore(context)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "notesBayDS")
    private val dataStore = context.dataStore



    //theme prefs
    suspend fun setAppTheme(theme: String) {
        dataStore.edit { pref ->
            pref[PREFS.THEME_KEY] = theme
        }
    }
    fun getAppTheme(): Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { pref ->
            val theme = pref[PREFS.THEME_KEY] ?: "System Default"
            theme
        }


    //quiz-cool-down
    suspend fun setQuizCoolDown(show: Long) {
        dataStore.edit { pref ->
            pref[PREFS.QUIZ_KEY] = show
        }
    }

    fun getQuizCoolDown(): Flow<Long> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { pref ->
            val show = pref[PREFS.QUIZ_KEY] ?: 0L
            show
        }


    //level
    suspend fun setLevel(level: Int) {
        dataStore.edit { pref ->
            pref[PREFS.LEVELS_KEY] = level
        }
    }

    fun getLevel(): Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { pref ->
            val show = pref[PREFS.LEVELS_KEY] ?: 0
            show
        }
}