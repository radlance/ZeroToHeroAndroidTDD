package ru.easycode.zerotoheroandroidtdd

import androidx.recyclerview.widget.DiffUtil

class RecyclerViewDiffUtil(
    private val oldList: List<CharSequence>,
    private val newList: List<CharSequence>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }
}