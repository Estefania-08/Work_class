package data.dao

import androidx.room.Dao
import androidx.room.Query
import data.model.AccountEntity

@Dao
interface AccountDao {
    @Query("SELECT * FROM accountentity")
     fun getAll(): List<AccountEntity>
}