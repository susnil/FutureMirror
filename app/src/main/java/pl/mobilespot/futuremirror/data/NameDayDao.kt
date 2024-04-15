package pl.mobilespot.futuremirror.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.futuremirror.namedays.NameDay

@Dao
interface NameDayDao {

    @Insert
    fun insert(log: NameDay)

    @Query("SELECT * FROM NameDay ORDER BY id DESC")
    fun getDayName(): List<NameDay>
}

