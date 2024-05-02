package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle

interface BundleWrapper {

    interface Save {
        fun save(uiState: UiState)
    }


    interface Restore {
        fun restore(): UiState
    }


    interface Mutable : Save, Restore

    data class Base(private val bundle: Bundle) : Mutable {
        override fun save(uiState: UiState) {
            bundle.putSerializable(KEY_STATE, uiState)
        }

        override fun restore(): UiState {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                return bundle.getSerializable(KEY_STATE, UiState::class.java)!!
            } else {
                @Suppress("DEPRECATION")
                return bundle.getSerializable(KEY_STATE) as UiState
            }
        }

    }

    companion object {
        private const val KEY_STATE = "uiState"
    }
}