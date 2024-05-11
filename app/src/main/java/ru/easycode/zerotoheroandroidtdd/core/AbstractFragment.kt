package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class AbstractFragment<B : ViewBinding> : Fragment() {
    private var _binding: B? = null
    protected val binding: B
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bind(inflater, container)
        return binding.root
    }

    protected abstract fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): B

    protected fun hideKeyboard() {
        val imn = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}