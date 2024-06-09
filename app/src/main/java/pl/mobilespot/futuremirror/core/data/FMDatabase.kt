package pl.mobilespot.futuremirror.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mobilespot.futuremirror.namedays.local.NameDayDao
import pl.mobilespot.futuremirror.namedays.local.data.NameDay

@Database(entities = [NameDay::class], version = 2)
abstract class FMDatabase : RoomDatabase() {
    abstract fun nameDayDao(): NameDayDao
}
