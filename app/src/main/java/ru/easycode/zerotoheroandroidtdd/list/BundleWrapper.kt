package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle

interface BundleWrapper {
    interface Save : BundleWrapper {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore : BundleWrapper {
        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore

    class Base(private val bundle: Bundle) : Mutable {
        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY_ARRAY, list)
        }

        override fun restore(): List<CharSequence> {
            val arrayList = bundle.getCharSequenceArrayList(KEY_ARRAY)
            return arrayList ?: ArrayList()
        }

    }

    companion object {
        private const val KEY_ARRAY = "arrayList"
    }
}