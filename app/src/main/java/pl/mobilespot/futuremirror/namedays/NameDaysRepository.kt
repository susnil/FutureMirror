package pl.mobilespot.futuremirror.namedays

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameDaysRepository @Inject constructor(private val localDataSource: LocalDataSource) :
    Repository {

    override fun getNamesForDay(dayMonth: DayMonth): String {
        Timber.d("Month: ${dayMonth.month + 1}, day: ${dayMonth.day}")
        return localDataSource.getNamesForDay(dayMonth)
    }
}

interface Repository {
    fun getNamesForDay(dayMonth: DayMonth): String
}