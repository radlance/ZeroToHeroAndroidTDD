package ru.easycode.zerotoheroandroidtdd.list

data class ItemUi(
    val id: Long,
    var text: String
) {

    fun areItemsSame(item: ItemUi): Boolean {
        return id == item.id
    }

    fun areContentsTheSame(item: ItemUi) = id != item.id && text == item.text
}