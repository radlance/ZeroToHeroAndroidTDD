package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun increment(number: String): UiState

    data class Base(private val step: Int, private val max: Int) : Count {
        init {
            if (max <= 0) {
                throw java.lang.IllegalStateException("max should be positive, but was $max")
            }

            if (step <= 0) {
                throw IllegalStateException("step should be positive, but was $step")
            }
            if (step > max) {
                throw IllegalStateException("max should be more than step")
            }

        }

        override fun increment(number: String): UiState {
            val incrementedNumber = number.toInt() + step
            if (incrementedNumber > max) {
                return UiState.Max(number)
            }
            if ((incrementedNumber + step) > max) {
                return UiState.Max(incrementedNumber.toString())
            }
            return UiState.Base(incrementedNumber.toString())
        }

    }
}