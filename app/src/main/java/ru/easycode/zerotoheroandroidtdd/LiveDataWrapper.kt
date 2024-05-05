package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {
    interface Observe {
        fun liveData(): LiveData<UiState>
    }

    interface Update {
        fun update(value: UiState)
    }

    interface Save {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Mutable: Observe, Update, Save, LiveDataWrapper
    data class Base(private val liveData: MutableLiveData<UiState> = MutableLiveData()) :
        Mutable {
        override fun liveData(): LiveData<UiState> = liveData

        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

    }

}