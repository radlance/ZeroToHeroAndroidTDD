package ru.easycode.zerotoheroandroidtdd.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextViewBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewVH>() {
    private val listTextView = mutableListOf<ItemUi>()

    fun update(updatedList: List<ItemUi>) {
        val diffCallback = RecyclerViewDiffUtil(listTextView, updatedList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listTextView.clear()
        listTextView.addAll(updatedList)
        diffResult.dispatchUpdatesTo(this)
    }

    var onItemClickListener: ((ItemUi) -> Unit)? = null

    class RecyclerViewVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTextViewBinding.bind(itemView)

        fun bind(itemUi: ItemUi) {
            binding.elementTextView.text = itemUi.text
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
        val itemUi = listTextView[position]
        holder.binding.elementTextView.setOnClickListener {
            onItemClickListener?.invoke(itemUi)
        }
        holder.bind(itemUi)
    }
}