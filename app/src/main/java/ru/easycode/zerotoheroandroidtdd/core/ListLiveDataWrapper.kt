package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.list.ItemUi

interface ListLiveDataWrapper {
    interface Update {
        fun update(value: List<ItemUi>)
    }

    interface Observe {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Add {
        fun add(value: ItemUi)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface All : Update, Observe, Add, Delete

    class Base : All {
        private val liveData = MutableLiveData<List<ItemUi>>()
        override fun update(value: List<ItemUi>) {
            liveData.postValue(value)
        }

        override fun liveData(): LiveData<List<ItemUi>> = liveData

        override fun add(value: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.add(value)
            liveData.postValue(list)
        }

        override fun delete(item: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.remove(item)
            liveData.postValue(list)
        }

    }
}