package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val settings = applicationContext.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        binding.incrementButton.setOnClickListener {
            val count = Count.Base(step = 2)
            binding.countTextView.text = count.increment(binding.countTextView.text.toString())
            settings.edit().putString(NUMBER_KEY, binding.countTextView.text.toString()).apply()
        }

        if (settings.contains(NUMBER_KEY)) {
            binding.countTextView.text = settings.getString(NUMBER_KEY, "")
        }
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        if (savedInstanceState?.containsKey(NUMBER_KEY) == true) {
            binding.countTextView.text = savedInstanceState.getString(NUMBER_KEY)
        }
    }

    companion object {
        private const val STORAGE_KEY = "storage"
        private const val NUMBER_KEY = "number"
    }
}