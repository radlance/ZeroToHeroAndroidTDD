package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val listLiveDataWrapper: ListLiveDataWrapper) : ViewModel() {
    fun liveData(): LiveData<List<CharSequence>> = listLiveDataWrapper.liveData()

    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        val restoredList = bundle.restore()
        listLiveDataWrapper.update(restoredList)
    }
}