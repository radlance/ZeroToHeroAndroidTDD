package ru.easycode.zerotoheroandroidtdd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemsDao {

    @Query("SELECT * FROM itemCache")
    fun list(): List<ItemCache>

    @Insert
    fun add(item: ItemCache)
}