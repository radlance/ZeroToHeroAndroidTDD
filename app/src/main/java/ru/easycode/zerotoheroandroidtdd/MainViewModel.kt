package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
) : ViewModel() {

    fun liveData(): LiveData<UiState> {
        return liveDataWrapper.liveData()
    }
    fun load() {
        viewModelScope.launch {
            liveDataWrapper.update(UiState.ShowProgress)
            repository.load()
            liveDataWrapper.update(UiState.ShowData)
        }
    }
}