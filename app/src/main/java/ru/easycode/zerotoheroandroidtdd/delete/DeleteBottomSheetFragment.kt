package ru.easycode.zerotoheroandroidtdd.delete

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel

class DeleteBottomSheetFragment : BottomSheetDialogFragment(R.layout.fragment_delete) {
    private lateinit var viewModel: DeleteViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(DeleteViewModel::class.java)
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
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.deleteButton).setOnClickListener {
            viewModel.delete(itemUiId)
            dismiss()
        }

        val textView = view.findViewById<TextView>(R.id.itemTitleTextView)
        viewModel.liveData.observe(viewLifecycleOwner) {
            textView.text = it
        }

        viewModel.init(itemUiId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }

    companion object {
        private const val KEY_ID = "id"
        fun newInstance(itemUiId: Long): DeleteBottomSheetFragment {
            return DeleteBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putLong(KEY_ID, itemUiId)
                }
            }
        }
    }
}