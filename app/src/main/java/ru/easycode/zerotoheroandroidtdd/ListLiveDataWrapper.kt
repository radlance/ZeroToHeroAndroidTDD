package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map

interface ListLiveDataWrapper {
    fun liveData(): LiveData<List<CharSequence>>
    fun save(bundle: BundleWrapper.Save)
    fun add(new: CharSequence)
    fun update(list: List<CharSequence>)

    data class Base(private val liveData: MutableLiveData<ArrayList<CharSequence>> = MutableLiveData()) :
        ListLiveDataWrapper {
        override fun liveData(): LiveData<List<CharSequence>> = liveData.map { it.toList() }
        override fun save(bundle: BundleWrapper.Save) {
            val liveDataValue = liveData.value
            bundle.save(liveData.value ?: ArrayList())
        }

        override fun add(new: CharSequence) {
            val list = liveData.value ?: ArrayList()
            list.add(new)
            update(list)
        }

        override fun update(list: List<CharSequence>) {
            liveData.value = ArrayList(list)
        }
    }
}