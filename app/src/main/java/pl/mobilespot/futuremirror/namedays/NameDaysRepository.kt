package pl.mobilespot.futuremirror.namedays

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameDaysRepository @Inject constructor(private val localDataSource: LocalDataSource) :
    Repository {

    override fun getNamesForDay(): String {
        return localDataSource.getNamesForDay(2, 2)
    }
}

interface Repository {
    fun getNamesForDay(): String
}
