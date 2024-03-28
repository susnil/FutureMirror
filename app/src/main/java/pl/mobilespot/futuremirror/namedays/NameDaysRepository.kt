package pl.mobilespot.futuremirror.namedays

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameDaysRepository @Inject constructor(private val localDataSource: LocalDataSource) :
    Repository {

    override fun getNamesForDay(dayMonth: DayMonth): List<String> {
        Timber.d("Month: ${dayMonth.month}, day: ${dayMonth.day}")
        return localDataSource.getNamesForDay(dayMonth)
    }
}

interface Repository {
    fun getNamesForDay(dayMonth: DayMonth): List<String>
}