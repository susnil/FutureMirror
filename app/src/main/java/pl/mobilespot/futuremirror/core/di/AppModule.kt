package pl.mobilespot.futuremirror.core.di

import android.app.Application
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mobilespot.futuremirror.data.FMDatabase
import pl.mobilespot.futuremirror.data.NameDayDao
import pl.mobilespot.futuremirror.namedays.LocalDataSource
import pl.mobilespot.futuremirror.namedays.NameDaysDataSource
import pl.mobilespot.futuremirror.namedays.NameDaysRepository
import pl.mobilespot.futuremirror.namedays.Repository
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
        application: Application
    ): FMDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = FMDatabase::class.java,
            name = "db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLogDao(
        logFMDatabase: FMDatabase
    ): NameDayDao = logFMDatabase.nameDayDao()

}