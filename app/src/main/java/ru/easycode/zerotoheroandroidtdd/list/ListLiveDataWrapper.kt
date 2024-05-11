package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface ListLiveDataWrapper {

    interface Observe : LiveDataWrapper.Observe<List<CharSequence>>
    interface Update : LiveDataWrapper.Update<List<CharSequence>>

    interface Add {
        fun add(source: CharSequence)
    }

    interface Mutable: Observe, Update {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface All : Add, Mutable


    class Base: LiveDataWrapper.Abstract<List<CharSequence>>(), All {


        override fun add(source: CharSequence) {
            val value = liveData.value ?: ArrayList()
            val updatedList = ArrayList<CharSequence>()
            updatedList.addAll(value)
            updatedList.add(source)
            liveData.value = updatedList
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(ArrayList(it)) }
        }
    }
}