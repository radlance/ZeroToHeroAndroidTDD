package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle

interface BundleWrapper {
    interface Save : BundleWrapper {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore : BundleWrapper {
        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore

    data class Base(private val bundle: Bundle) : Mutable {
        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY_CHAR_SEQUENCE, list)
        }

        override fun restore(): List<CharSequence> {
            val arrayList = bundle.getCharSequenceArrayList(KEY_CHAR_SEQUENCE)
            return arrayList ?: ArrayList()
        }

    }

    companion object {
        private const val KEY_CHAR_SEQUENCE = "charSequence"
    }
}