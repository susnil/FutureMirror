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
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
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

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        prepopulateDatabase()
    }

    private val mutex = Mutex()

    @OptIn(DelicateCoroutinesApi::class)
    private fun prepopulateDatabase() {
        GlobalScope.launch(Dispatchers.IO) {
            mutex.withLock {
                Timber.d("Check if is already exist any data")
                val provider = provider.get()
                if (provider.getDayNameCount() == 0) {
                    Timber.d("No data! Start prepopulate database")
                    insertPLNames(provider)
                    Timber.d("End prepopulate database")
                }
            }
        }
    }

    private fun insertPLNames(provider: NameDayDao) {
        val data = listOf(
            NameDay(name = "Bogdan", day = 3, month = 3),
            NameDay(name = "Zenek", day = 4, month = 3)
        )
        data.forEach { nameDay ->
            provider.insert(nameDay)
        }
    }
}