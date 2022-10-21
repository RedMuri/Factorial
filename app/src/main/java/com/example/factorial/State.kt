package com.example.factorial

sealed class State

object Error : State()
object Process : State()
class Result(val factorial: String) : State()
