package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.delete.DeleteViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.repository.Core
import ru.easycode.zerotoheroandroidtdd.repository.Now
import ru.easycode.zerotoheroandroidtdd.repository.Repository

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clazz: Class<T>): T
    class Base(core: Core, private val clear: ClearViewModel) : ProvideViewModel {
        private val repository = Repository.Base(core.itemsDao(), Now.Base())
        private val liveDataWrapper = ListLiveDataWrapper.Base()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
            return (when (clazz) {
                MainViewModel::class.java -> MainViewModel(repository, liveDataWrapper)
                AddViewModel::class.java -> AddViewModel(repository, liveDataWrapper, clear)
                DeleteViewModel::class.java -> DeleteViewModel(liveDataWrapper, repository, clear)
                else -> throw IllegalArgumentException("unknown class: $clazz")
            }) as T
        }

    }
}