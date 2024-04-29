package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var visibility = View.VISIBLE
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val settings = applicationContext.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        binding.hideButton.setOnClickListener {
            settings.edit().putInt(VISIBILITY_STATUS, View.INVISIBLE).apply()
            binding.titleTextView.visibility = View.INVISIBLE
        }
        if (settings.contains(VISIBILITY_STATUS)) {
            visibility = settings.getInt(VISIBILITY_STATUS, -1)
        }
        binding.titleTextView.visibility = visibility
    }
    companion object {
        private const val STORAGE_NAME = "storage"
        private const val VISIBILITY_STATUS = "visibility_status"
    }
}