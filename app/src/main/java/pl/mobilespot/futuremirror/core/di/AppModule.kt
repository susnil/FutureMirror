package pl.mobilespot.futuremirror.core.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.mobilespot.futuremirror.data.FMDatabase
import pl.mobilespot.futuremirror.data.NameDayDao
import pl.mobilespot.futuremirror.namedays.LocalDataSource
import pl.mobilespot.futuremirror.namedays.NameDay
import pl.mobilespot.futuremirror.namedays.NameDaysDataSource
import pl.mobilespot.futuremirror.namedays.NameDaysRepository
import pl.mobilespot.futuremirror.namedays.Repository
import timber.log.Timber
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTaskRepository(repository: NameDaysRepository): Repository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: NameDaysDataSource): LocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application,
        provider: Provider<NameDayDao>
    ): FMDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = FMDatabase::class.java,
            name = "db"
        )
            .addCallback(AppDatabaseCallback(provider))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLogDao(
        logFMDatabase: FMDatabase
    ): NameDayDao = logFMDatabase.nameDayDao()
}

class AppDatabaseCallback(private val provider: Provider<NameDayDao>) :
    RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        prepopulateDatabase()
    }
    //todo it will be nice to trigger prepopulateDatabase if database is empty. More often than only onCreate
    @OptIn(DelicateCoroutinesApi::class)
    private fun prepopulateDatabase() {
        GlobalScope.launch(Dispatchers.IO) {
            Timber.d("Start prepopulate database")
            provider.get().insert(NameDay(name = "Bogdan", day = 3, month = 3))
            provider.get().insert(NameDay(name = "Zenek", day = 4, month = 3))
            Timber.d("End prepopulate database")
        }
    }
}