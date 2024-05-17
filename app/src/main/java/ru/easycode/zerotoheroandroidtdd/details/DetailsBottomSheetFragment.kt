package ru.easycode.zerotoheroandroidtdd.details

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel

class DetailsBottomSheetFragment : BottomSheetDialogFragment(R.layout.fragment_details) {
    private lateinit var viewModel: DetailsViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(DetailsViewModel::class.java)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemUiId = requireArguments().getLong(KEY_ID)
        val itemInputEditText = view.findViewById<TextInputEditText>(R.id.itemInputEditText)
        val textView = view.findViewById<TextView>(R.id.itemTextView)
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.deleteButton).setOnClickListener {
            viewModel.delete(itemUiId)
            dismiss()
        }

        view.findViewById<View>(R.id.updateButton).setOnClickListener {
            viewModel.update(itemUiId, itemInputEditText.text.toString())
            dismiss()
        }

        viewModel.liveData.observe(viewLifecycleOwner) {
            textView.text = it
            itemInputEditText.setText(it)
        }

        viewModel.init(itemUiId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }

    companion object {
        private const val KEY_ID = "id"
        fun newInstance(itemUiId: Long): DetailsBottomSheetFragment {
            return DetailsBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putLong(KEY_ID, itemUiId)
                }
            }
        }
    }
}