package ru.easycode.zerotoheroandroidtdd

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.Serializable

interface State : Serializable {
    fun apply(progressBar: ProgressBar, button: Button, textView: TextView)
    object Initial : State {
        private fun readResolve(): Any = Initial
        override fun apply(progressBar: ProgressBar, button: Button, textView: TextView) = Unit
    }

    object Loading : State {
        private fun readResolve(): Any = Loading
        override fun apply(progressBar: ProgressBar, button: Button, textView: TextView) {
            progressBar.visibility = View.VISIBLE
            textView.visibility = View.INVISIBLE
            button.isEnabled = false
        }

    }

    object Success : State {
        private fun readResolve(): Any = Success
        override fun apply(progressBar: ProgressBar, button: Button, textView: TextView) {
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
            button.isEnabled = true
        }

    }
}