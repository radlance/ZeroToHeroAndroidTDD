package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.RecyclerViewAdapter
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment : AbstractFragment<FragmentListBinding>() {
    private lateinit var viewModel: ListViewModel
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentListBinding {
        return FragmentListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(ListViewModel::class.java)

        binding.addButton.setOnClickListener {
            viewModel.create()
        }
        val adapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }
        savedInstanceState?.let {
            viewModel.restore(BundleWrapper.Base(it))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }
}