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
import pl.mobilespot.futuremirror.core.utils.Constants.BASE_URL
import pl.mobilespot.futuremirror.core.data.FMDatabase
import pl.mobilespot.futuremirror.namedays.local.NameDayDao
import pl.mobilespot.futuremirror.namedays.local.LocalDataSource
import pl.mobilespot.futuremirror.namedays.local.data.NameDay
import pl.mobilespot.futuremirror.namedays.local.NameDaysDataSource
import pl.mobilespot.futuremirror.namedays.repository.NameDaysRepository
import pl.mobilespot.futuremirror.namedays.repository.Repository
import pl.mobilespot.futuremirror.news.remote.NewsApi
import pl.mobilespot.futuremirror.news.repository.NewsRepository
import pl.mobilespot.futuremirror.news.repository.NewsRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideApiInstance(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
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

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}
