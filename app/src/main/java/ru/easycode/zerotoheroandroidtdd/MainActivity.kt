package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        var mustRemoved = false
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val settings = applicationContext.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        binding.removeButton.setOnClickListener {
            settings.edit().putBoolean(EXIST_STATUS, true).apply()
            binding.rootLayout.removeView(binding.titleTextView)
            binding.removeButton.isEnabled = false
        }

        if(settings.contains(EXIST_STATUS)) {
            mustRemoved = true
        }

        if(mustRemoved) {
            binding.rootLayout.removeView(binding.titleTextView)
            binding.removeButton.isEnabled = false
        }
    }

    companion object {
        private const val STORAGE_NAME = "storage"
        private const val EXIST_STATUS = "exist_status"
    }
}