package ru.easycode.zerotoheroandroidtdd.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache_item")
data class ItemCache(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "text")
    val text: String
)