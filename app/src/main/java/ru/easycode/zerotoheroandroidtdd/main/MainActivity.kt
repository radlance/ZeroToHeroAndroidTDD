package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.add.AddBottomSheetFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.delete.DeleteBottomSheetFragment
import ru.easycode.zerotoheroandroidtdd.list.RecyclerViewAdapter

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = adapter

        viewModel = viewModel(MainViewModel::class.java)
        binding.addButton.setOnClickListener {
            AddBottomSheetFragment().show(supportFragmentManager, "CreateBottomSheetFragment")
        }

        viewModel.liveData().observe(this) {
            adapter.update(it)
        }

        adapter.onItemClickListener = {
            DeleteBottomSheetFragment.newInstance(it.id)
                .show(supportFragmentManager, "CreateBottomSheetFragment")
        }

        viewModel.init()
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        return (application as ProvideViewModel).viewModel(clazz)
    }
}