package com.example.inventory.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDB: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        private const val DB_NAME = "item_database"
        // patrón SINGLETON
        @Volatile
        private var INSTANCE: ItemRoomDB? = null

        fun getDatabase(context: Context): ItemRoomDB {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDB::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}