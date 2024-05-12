package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clazz: Class<T>): T

    class Base(core: Core, private val clear: ClearViewModel) : ProvideViewModel {
        private val repository = Repository.Base(core.itemsDao(), Now.Base())
        private val liveDataWrapper = ListLiveDataWrapper.Base()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
            return (if (clazz == MainViewModel::class.java) {
                MainViewModel(repository, liveDataWrapper)
            } else {
                AddViewModel(repository, liveDataWrapper, clear)
            }) as T
        }
    }
}