package pl.mobilespot.futuremirror.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mobilespot.futuremirror.namedays.NameDay

@Database(entities = [NameDay::class], version = 1)
abstract class FMDatabase : RoomDatabase() {
    abstract fun nameDayDao(): NameDayDao
}