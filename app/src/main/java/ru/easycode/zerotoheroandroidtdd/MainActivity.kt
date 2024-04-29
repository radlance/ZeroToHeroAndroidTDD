package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mustRemoved = false
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settings = applicationContext.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        binding.removeButton.setOnClickListener {
            settings.edit().putBoolean(EXIST_STATUS, true).apply()
            binding.rootLayout.removeView(binding.titleTextView)
        }

        if (settings.contains(EXIST_STATUS)) {
            mustRemoved = settings.getBoolean(EXIST_STATUS, false)
        }
        if (mustRemoved) {
            binding.rootLayout.removeView(binding.titleTextView)
        }
    }

    companion object {
        private const val STORAGE_NAME = "storage"
        private const val EXIST_STATUS = "exist_status"
    }
}