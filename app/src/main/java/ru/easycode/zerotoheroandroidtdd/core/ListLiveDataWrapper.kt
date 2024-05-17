package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.list.ItemUi

interface ListLiveDataWrapper {

    interface Read {
        fun liveData(): LiveData<List<ItemUi>>
    }

    interface Update {
        fun update(list: List<ItemUi>)
        fun update(item: ItemUi)
    }

    interface Add {
        fun add(value: ItemUi)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface All : Read, Update, Add, Delete

    class Base : All {
        private val liveData = MutableLiveData<List<ItemUi>>()
        override fun liveData(): LiveData<List<ItemUi>> = liveData

        override fun update(list: List<ItemUi>) {
            liveData.value = list
        }

        override fun update(item: ItemUi) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.find { it.id == item.id }?.also{
                it.text = item.text
            }
            update(list)
        }

        override fun add(value: ItemUi) {
            val list = getMutableList()
            list.add(value)
            liveData.postValue(list)
        }

        override fun delete(item: ItemUi) {
            val list = getMutableList()
            list.remove(item)
            update(list)
        }

        private fun getMutableList(): MutableList<ItemUi> {
            return liveData.value?.toMutableList() ?: ArrayList()
        }
    }
}