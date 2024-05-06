package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextViewBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewVH>() {
    private val listTextView = mutableListOf<String>()
    fun addItem(text: String) {
        listTextView.add(text)
        notifyItemInserted(listTextView.size - 1)
    }

    class RecyclerViewVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTextViewBinding.bind(itemView)

        fun bind(itemTextView: String) {
            binding.elementTextView.text = itemTextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewVH {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_text_view, parent, false)
        return RecyclerViewVH(view)
    }

    override fun getItemCount(): Int {
        return listTextView.size
    }

    override fun onBindViewHolder(holder: RecyclerViewVH, position: Int) {
        holder.bind(listTextView[position])
    }
}