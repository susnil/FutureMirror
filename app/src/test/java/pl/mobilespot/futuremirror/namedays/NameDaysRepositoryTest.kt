package pl.mobilespot.futuremirror.namedays

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

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
}
