package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    data class Base(val step: Int, val max: Int, val min: Int) : Count {

        init {
            if(step <= 0) {
                throw IllegalStateException("step should be positive, but was $step")
            }
            if (max <= 0) {
                throw IllegalStateException("max should be positive, but was $max")
            }
            if (max <= step) {
                throw IllegalStateException("max should be more than step")
            }
            if (max <= min) {
                throw IllegalStateException("max should be more than min")
            }
        }
        override fun initial(number: String): UiState {
            val intNumber = number.toInt()
            return when (intNumber) {
                max -> UiState.Max(number)
                min -> UiState.Min(number)
                else -> UiState.Base(number)
            }
        }


        override fun increment(number: String): UiState {
            val incrementedNumber = number.toInt() + step
            if (incrementedNumber > max) {
                return UiState.Max(number)
            }
            if (incrementedNumber + step > max) {
                return UiState.Max(incrementedNumber.toString())
            }
            return UiState.Base(incrementedNumber.toString())
        }

        override fun decrement(number: String): UiState {
            val decrementedNumber = number.toInt() - step
            if (decrementedNumber < min) {
                return UiState.Min(number)
            }
            if (decrementedNumber - step < min) {
                return UiState.Min(decrementedNumber.toString())
            }
            return UiState.Base(decrementedNumber.toString())
        }

    }
}