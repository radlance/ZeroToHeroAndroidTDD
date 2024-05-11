package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ProvideViewModel, ClearViewModel {
    override fun clear(viewModelClass: Class<out ViewModel>)
    data class Base(private val provideViewModel: ProvideViewModel) : ViewModelFactory {

        private val map = mutableMapOf<Class<out ViewModel>, ViewModel>()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return if (map.containsKey(viewModelClass)) {
                map[viewModelClass] as T
            } else {
                val viewModel = provideViewModel.viewModel(viewModelClass)
                map[viewModelClass] = viewModel
                viewModel
            }
        }

        override fun clear(viewModelClass: Class<out ViewModel>) {
            map.remove(viewModelClass)
        }
    }
}