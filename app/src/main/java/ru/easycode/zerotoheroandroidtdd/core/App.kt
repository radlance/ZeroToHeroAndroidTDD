package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.repository.Core

class App : Application(), ProvideViewModel {
    private lateinit var factory: ProvideViewModel
    private val store = HashMap<Class<out ViewModel>, ViewModel?>()

    private val clear = object : ClearViewModel {
        override fun clearViewModel(clasz: Class<out ViewModel>) {
            store[clasz] = null
        }
    }

    override fun onCreate() {
        super.onCreate()
        val core = Core(this)
        factory = ProvideViewModel.Base(core, clear)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        if (store[clazz] == null) {
            store[clazz] = factory.viewModel(clazz)
        }
        return store[clazz] as T
    }
}