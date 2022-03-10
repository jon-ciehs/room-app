package com.example.inventory.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item) // suspend son funciones llamadas desde corutinas

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item WHERE id = :id") // el ":" permite referenciar args de la fun
    fun getItem(id: Int): Flow<Item> // Flow, al igual que LiveData, notifica de cambios en BBDD

    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>



}