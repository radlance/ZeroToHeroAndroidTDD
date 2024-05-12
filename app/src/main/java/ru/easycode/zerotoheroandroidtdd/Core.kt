package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.room.Room

class Core(private val context: Context) {
    private val database by lazy {
        Room.databaseBuilder(
            context,
            ItemsDataBase::class.java,
            "items_db"
        ).build()
    }

    fun itemsDao() = database.itemsDao()
}