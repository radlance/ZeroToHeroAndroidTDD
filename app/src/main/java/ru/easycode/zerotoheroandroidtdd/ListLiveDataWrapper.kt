package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface ListLiveDataWrapper {
    interface Update {
        fun update(value: List<String>)
    }

    interface Observe {
        fun liveData(): LiveData<List<String>>
    }

    interface Add {
        fun add(value: String)
    }

    interface Mutable : Update, Observe
    interface All : Mutable, Add

    class Base : All {
        private val liveData = MutableLiveData<List<String>>()
        override fun update(value: List<String>) {
            liveData.postValue(value)
        }

        override fun liveData(): LiveData<List<String>> = liveData
        override fun add(value: String) {
            val list = liveData.value?.toMutableList() ?: ArrayList()
            list.add(value)
            liveData.postValue(list)
        }

    }
}