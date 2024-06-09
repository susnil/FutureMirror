package pl.mobilespot.futuremirror.namedays.repository

import pl.mobilespot.futuremirror.namedays.local.NameDayDao
import pl.mobilespot.futuremirror.namedays.local.data.DayMonth
import pl.mobilespot.futuremirror.namedays.local.data.NameDay
import pl.mobilespot.futuremirror.namedays.local.data.SearchResult
import pl.mobilespot.futuremirror.namedays.local.LocalDataSource
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameDaysRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val nameDayDao: NameDayDao
) : Repository {
    private val mapper = Mapper()
    override fun getNamesForDay(dayMonth: DayMonth): List<String> {
        Timber.d("Month: ${dayMonth.month}, day: ${dayMonth.day}")
        return localDataSource.getNamesForDay(dayMonth)
    }

    override fun getDaysForName(name: String): List<DayMonth> {
        return localDataSource.getDaysForName(name)
    }

    override fun getSearchResult(searchText: String): SearchResult {
        return mapper.toResult(nameDayDao.findNames(searchText))
    }
}

class Mapper {
    fun toResult(list: List<NameDay>): SearchResult {
        return SearchResult(list)
    }
}

interface Repository {
    fun getNamesForDay(dayMonth: DayMonth): List<String>
    fun getDaysForName(name: String): List<DayMonth>

    fun getSearchResult(searchText: String): SearchResult
}
