package pl.mobilespot.futuremirror.datastore.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.mobilespot.futuremirror.datastore.UserPreferencesRepository
import pl.mobilespot.futuremirror.datastore.UserPreferencesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    @Provides
    @Singleton
    internal fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
    ): UserPreferencesRepository =
        UserPreferencesRepositoryImpl(context.dataStore)
}
