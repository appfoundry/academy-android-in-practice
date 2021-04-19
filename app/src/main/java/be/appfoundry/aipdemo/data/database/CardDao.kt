package be.appfoundry.aipdemo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.appfoundry.aipdemo.data.model.Card

@Dao
interface CardDao {

    @Query("SELECT * FROM card")
    suspend fun getCards(): List<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(card: List<Card>)
}