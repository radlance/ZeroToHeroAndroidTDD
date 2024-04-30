package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val count = Count.Base(2, 4)
    private var uiState: UiState = UiState.Base("0")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.incrementButton.setOnClickListener {
            uiState = count.increment(binding.countTextView.text.toString())
            uiState.show(binding.countTextView, binding.incrementButton)
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
        uiState.show(binding.countTextView, binding.incrementButton)
    }

    companion object {
        private const val STATE_KEY = "state"
    }
}