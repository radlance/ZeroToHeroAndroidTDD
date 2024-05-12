package ru.easycode.zerotoheroandroidtdd

interface DataSource : ItemsDao {
    class Base : DataSource {
        override fun list(): List<ItemCache> {
            TODO("Not yet implemented")
        }

        override fun add(item: ItemCache) {
            TODO("Not yet implemented")
        }
    }
}