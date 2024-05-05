package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle

interface BundleWrapper {
    interface Save: BundleWrapper {
        fun save(uiState: UiState)
    }
    interface Restore: BundleWrapper {
        fun restore(): UiState
    }
    interface Mutable: Save, Restore

    data class Base(private val bundle: Bundle): Mutable {
        override fun save(uiState: UiState) {
            bundle.putSerializable(KEY_STATE, uiState)
        }

        override fun restore(): UiState {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(KEY_STATE, UiState::class.java)!!
            } else {
                @Suppress("DEPRECATION")
                bundle.getSerializable(KEY_STATE) as UiState
            }
        }

    }

    companion object {
        private const val KEY_STATE = "state"
    }
}