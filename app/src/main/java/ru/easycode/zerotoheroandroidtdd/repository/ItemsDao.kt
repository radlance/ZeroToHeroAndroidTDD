package ru.easycode.zerotoheroandroidtdd.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemsDao {

    @Query("SELECT * FROM cache_item")
    fun list(): List<ItemCache>

    @Insert
    fun add(item: ItemCache)

    @Query("SELECT * FROM cache_item WHERE id =:id")
    fun item(id: Long): ItemCache
    @Query("DELETE FROM cache_item WHERE id=:id ")
    fun delete(id : Long)
}