package pl.mobilespot.futuremirror.namedays

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test
import pl.mobilespot.futuremirror.namedays.local.LocalDataSource
import pl.mobilespot.futuremirror.namedays.local.NameDaysDataSource
import pl.mobilespot.futuremirror.namedays.local.data.DayMonth
import pl.mobilespot.futuremirror.namedays.repository.NameDaysRepository

class NameDaysRepositoryTest {
    @Test
    fun getNamesForBirthday() {
        val localDataStore = mockk<LocalDataSource>()

        val repository = NameDaysRepository(localDataStore)
        val currentDate = DayMonth(1, 1)

        val expectedNames =
            listOf("Lucjana", "Marka", "Jana")
        every { localDataStore.getNamesForDay(currentDate) } returns expectedNames

        val actualNames = repository.getNamesForDay(currentDate)

        assertEquals(expectedNames, actualNames)
    }

    @Test
    fun getMonthDaysForName() {
        val localDataStore = NameDaysDataSource()

        val repository = NameDaysRepository(localDataStore)
        val expectedDayMonths = listOf(DayMonth(1, 2), DayMonth(3, 4))
        val name = "Jana"

        val actualDayMonths = repository.getDaysForName(name)

        assertEquals(expectedDayMonths, actualDayMonths)
    }
}
