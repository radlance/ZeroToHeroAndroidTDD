package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var textList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            val textInput = binding.inputEditText.text.toString()
            if (textInput.isNotBlank()) {
                val textView = TextView(this).apply {
                    textList.add(textInput)
                    text = textInput
                }
                binding.contentLayout.addView(textView)
            }
            binding.inputEditText.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(KEY_ARRAY, textList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val list = savedInstanceState.getStringArrayList(KEY_ARRAY)
        textList.addAll(list ?: emptyList())
        for (textInput in textList) {
            binding.contentLayout.addView(TextView(this).apply {
                text = textInput
            })
        }
    }

    companion object {
        private const val KEY_ARRAY = "array"
    }
}