package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModel(MainViewModel::class.java)
        val adapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.liveData().observe(this) {
            adapter.update(it)
        }

        binding.addButton.setOnClickListener {
            AddBottomSheetFragment().show(supportFragmentManager, "CreateBottomSheetFragment")
        }

        viewModel.init()
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        return (application as ProvideViewModel).viewModel(clazz)
    }
}