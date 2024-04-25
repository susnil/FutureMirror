package pl.mobilespot.futuremirror.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.futuremirror.namedays.NameDay

@Dao
interface NameDayDao {

    @Insert
    fun insert(log: NameDay)

    @Query("SELECT * FROM NameDay ORDER BY id DESC")
    suspend fun getDayName(): List<NameDay>

    @Query("SELECT count() FROM NameDay")
    fun getDayNameCountFlow(): Flow<Int>

    @Query("SELECT count() FROM NameDay")
    fun getDayNameCount(): Int

    @Query("SELECT * FROM NameDay WHERE name like :searchText||'%' ORDER BY id DESC")
    fun findNames(searchText: String): List<NameDay>
}

