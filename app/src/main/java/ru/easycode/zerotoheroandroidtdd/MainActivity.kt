package ru.easycode.zerotoheroandroidtdd

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listTextView = ArrayList<String>()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = adapter
        binding.actionButton.setOnClickListener {
            val inputText = binding.inputEditText.text.toString()
            if (inputText.isNotBlank()) {
                adapter.addItem(inputText)
                listTextView.add(inputText)
            }
            binding.inputEditText.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(KEY_LIST, listTextView)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedList = savedInstanceState.getStringArrayList(KEY_LIST)
        listTextView.addAll(savedList ?: emptyList())

        for (textInput in listTextView) {
            adapter.addItem(textInput)
        }
    }

    companion object {
        private const val KEY_LIST = "arrayList"
    }
}