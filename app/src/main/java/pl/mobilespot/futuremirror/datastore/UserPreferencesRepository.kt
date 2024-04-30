package pl.mobilespot.futuremirror.datastore


import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val userPreferences: Flow<UserPreferences>

    suspend fun updateShowCompleted(showCompleted: Boolean)

}