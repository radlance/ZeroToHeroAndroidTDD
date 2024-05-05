package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val liveDataWrapper = LiveDataWrapper.Base()
        val repository = Repository.Base(ApiClient.service(SimpleService::class.java), URL)
        return MainViewModel(liveDataWrapper, repository) as T
    }

    companion object {
        private const val URL =
            "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }
}