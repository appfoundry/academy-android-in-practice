package be.appfoundry.aipdemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import be.appfoundry.aipdemo.data.model.Card

private const val DATABASE_VERSION = 1

@Database(entities = [Card::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao

    companion object {
        const val DATABASE_NAME = "app-database"
    }
}