package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var uiState: State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.actionButton.setOnClickListener {
            val timer = object : CountDownTimer(3500, 1000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    uiState = State.Success
                    applyUiState(uiState)
                }
            }
            uiState = State.Loading
            applyUiState(uiState)
            timer.start()
        }
    }

    private fun applyUiState(uiState: State) {
        uiState.apply(binding.progressBar, binding.actionButton, binding.titleTextView)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_STATE, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY_STATE, State::class.java)!!
        } else {
            savedInstanceState.getSerializable(KEY_STATE) as State
        }
    }

    companion object {
        private const val KEY_STATE = "state"
    }
}