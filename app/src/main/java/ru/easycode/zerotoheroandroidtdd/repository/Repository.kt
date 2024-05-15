package ru.easycode.zerotoheroandroidtdd.repository

import ru.easycode.zerotoheroandroidtdd.list.Item

interface Repository {

    interface Read {
        fun list(): List<Item>
    }

    interface Add {
        fun add(value: String): Long
    }

    interface Delete {
        fun item(id: Long): Item
        fun delete(id: Long)
    }

    interface All : Read, Add, Delete

    class Base(private val dataSource: ItemsDao, private val now: Now) : All {
        override fun list(): List<Item> = dataSource.list().map { Item(it.id, it.text) }


        override fun add(value: String): Long {
            val nowMills = now.nowMillis()
            dataSource.add(ItemCache(nowMills, value))
            return nowMills
        }

        override fun item(id: Long): Item {
            val itemCache = dataSource.item(id)
            return Item(itemCache.id, itemCache.text)
        }


        override fun delete(id: Long) {
            dataSource.delete(id)
        }
    }
}