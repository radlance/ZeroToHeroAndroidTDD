package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val count = Count.Base(step = 2, max = 4, min = 0)
    private lateinit var uiState: UiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootLayout)
        binding.incrementButton.setOnClickListener {
            uiState = count.increment(binding.countTextView.text.toString())
            uiState.apply(binding.countTextView, binding.incrementButton, binding.decrementButton)
        }

        binding.decrementButton.setOnClickListener {
            uiState = count.decrement(binding.countTextView.text.toString())
            uiState.apply(binding.countTextView, binding.incrementButton, binding.decrementButton)
        }
        if (savedInstanceState == null) {
            uiState = count.initial(binding.countTextView.text.toString())
            uiState.apply(binding.countTextView, binding.incrementButton, binding.decrementButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(STATE_KEY, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(STATE_KEY, UiState::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            savedInstanceState.getSerializable(STATE_KEY) as UiState
        }
        uiState.apply(binding.countTextView, binding.incrementButton, binding.decrementButton)
    }

    companion object {
        private const val STATE_KEY = "state"
    }
}