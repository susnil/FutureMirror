package pl.mobilespot.futuremirror.namedays.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pl.mobilespot.futuremirror.namedays.local.NameDayDao
import pl.mobilespot.futuremirror.namedays.local.data.NameDay
import pl.mobilespot.futuremirror.namedays.repository.NameDaysRepository
import pl.mobilespot.futuremirror.namedays.local.data.SearchResult
import javax.inject.Inject

class GetSavedNameDays @Inject constructor(
    private val nameDayDao: NameDayDao,
    private val nameDaysRepository: NameDaysRepository
) {

    suspend fun invoke(): List<NameDay> =
        withContext(Dispatchers.IO) {
            nameDayDao.getDayName()
        }

    fun getDayNamesCount(): Flow<Int> =
        nameDayDao.getDayNameCountFlow()

    suspend fun findName(searchText: String): SearchResult =
        withContext(Dispatchers.IO) {
            nameDaysRepository.getSearchResult(searchText)
        }

}
