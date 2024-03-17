package pl.mobilespot.futuremirror.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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