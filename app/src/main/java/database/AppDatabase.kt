package database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.dao.AccountDao
import data.model.AccountEntity

@Database(entities = [AccountEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}
