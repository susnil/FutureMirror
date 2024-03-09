package pl.mobilespot.futuremirror.namedays

import android.icu.util.Calendar
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameDaysRepository @Inject constructor(private val localDataSource: LocalDataSource) :
    Repository {

    override fun getNamesForDay(): String {
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        Timber.d("Month: $month, day: $day")
        return localDataSource.getNamesForDay(month, day)
    }
}

interface Repository {
    fun getNamesForDay(): String
}
