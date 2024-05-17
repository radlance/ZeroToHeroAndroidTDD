package ru.easycode.zerotoheroandroidtdd.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.list.ItemUi
import ru.easycode.zerotoheroandroidtdd.repository.Repository

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun add(value: String) {
        viewModelScope.launch(dispatcher) {
            val item = ItemUi(repository.add(value), value)
            withContext(dispatcherMain) {
                liveDataWrapper.add(item)
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clearViewModel(AddViewModel::class.java)
    }
}