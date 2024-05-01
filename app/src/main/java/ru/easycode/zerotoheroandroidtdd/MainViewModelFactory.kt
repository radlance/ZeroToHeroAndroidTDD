package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory : ViewModelProvider.Factory {
    private val liveDataWrapper = LiveDataWrapper.Base()
    private val repositoryBase = Repository.Base()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(liveDataWrapper, repositoryBase) as T
    }
}