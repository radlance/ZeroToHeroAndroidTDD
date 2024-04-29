package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val settings = applicationContext.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        binding.changeButton.setOnClickListener {
            binding.titleTextView.text = "I am an Android Developer!"
            settings.edit().putString(TITLE_NAME, binding.titleTextView.text.toString()).apply()
        }
        if (settings.contains(TITLE_NAME)) {
            binding.titleTextView.text = settings.getString(TITLE_NAME, "")
        }
    }

    companion object {
        private const val STORAGE_NAME = "storage"
        private const val TITLE_NAME = "titleText"
    }
}