package pl.mobilespot.futuremirror.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : UserPreferencesRepository {

    private object Keys {
        val showCompleted = booleanPreferencesKey("show_completed")
    }

    private inline val Preferences.showCompleted
        get() = this[Keys.showCompleted] ?: false

    override val userPreferences: Flow<UserPreferences> = dataStore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            UserPreferences(
                showCompleted = preferences.showCompleted
            )
        }
        .distinctUntilChanged()


    override suspend fun updateShowCompleted(showCompleted: Boolean) {
        dataStore.edit { it[Keys.showCompleted] = showCompleted }
        Timber.tag("UserPreferencesRepo").d("updateShowCompleted " + showCompleted)
    }

}