package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {
    fun show(textView: TextView, button: Button)
    data class Base(val text: String) : UiState {
        override fun show(textView: TextView, button: Button) {
            textView.text = text
        }
    }

    data class Max(val text: String) : UiState {
        override fun show(textView: TextView, button: Button) {
            textView.text = text
            button.isEnabled = false
        }
    }
}