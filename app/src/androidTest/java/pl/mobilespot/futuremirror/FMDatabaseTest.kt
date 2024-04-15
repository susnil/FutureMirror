package pl.mobilespot.futuremirror

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pl.mobilespot.futuremirror.data.FMDatabase
import pl.mobilespot.futuremirror.data.NameDayDao
import pl.mobilespot.futuremirror.namedays.NameDay

@RunWith(AndroidJUnit4::class)
class FMDatabaseTest {
    private lateinit var fmDatabase: FMDatabase
    private lateinit var dao: NameDayDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        fmDatabase = Room.inMemoryDatabaseBuilder(context, fmDatabase::class.java).build()
        dao = fmDatabase.nameDayDao()
    }

    @After
    fun tearDown() {
        fmDatabase.close()
    }

    @Test
    fun testInitialDataInsertion() {

        val initialData = NameDay("Bogdan")
        dao.insert(initialData)

        val loadedData = dao.getDayName()

        assertEquals(1, loadedData.size)
        assertEquals(initialData.name, loadedData[0].name)
    }
}