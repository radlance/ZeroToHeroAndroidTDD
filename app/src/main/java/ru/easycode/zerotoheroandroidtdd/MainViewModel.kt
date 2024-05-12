package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.Repository

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), ListLiveDataWrapper.Observe {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun init() {
        viewModelScope.launch(dispatcher) {
            val value = repository.list()
            withContext(dispatcherMain) {
                liveDataWrapper.update(value)
            }
        }
    }

    override fun liveData(): LiveData<List<String>> = liveDataWrapper.liveData()
}