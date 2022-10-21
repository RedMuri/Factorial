package com.example.factorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state


    fun calculate(value: String?) {
        _state.value = Process
        if (value.isNullOrBlank()) {
            _state.value = Error
            return
        }
        viewModelScope.launch {
            val number = factorial(value.toLong())
            _state.value = Result(number)
        }
    }

    private suspend fun factorial(num: Long): String {
        return withContext(Dispatchers.Default) {
            if (num == 0L)
                return@withContext "0"
            var result = BigInteger.ONE
            for (i in 1..num) {
                result *= BigInteger.valueOf(i)
            }
            result.toString()
        }
    }
}