package ru.easycode.zerotoheroandroidtdd.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class DeleteViewModel(
    private val deleteLiveDataWrapper: ListLiveDataWrapper.Delete,
    private val repository: Repository.Delete,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main

) : ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String>
        get() = _liveData

    fun init(itemId: Long) {
        viewModelScope.launch(dispatcher) {
            val item = repository.item(itemId)
            withContext(dispatcherMain) {
                _liveData.value = item.text
            }
        }
    }

    fun delete(itemId: Long) {
        viewModelScope.launch(dispatcher) {
            repository.delete(itemId)
            withContext(dispatcherMain) {
                deleteLiveDataWrapper.delete(ItemUi(itemId, liveData.value ?: ""))
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clearViewModel(DeleteViewModel::class.java)
    }
}