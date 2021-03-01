package com.example.androiddevchallenge.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Trait(
    val name: String,
    val isSelected: MutableState<Boolean> = mutableStateOf(false)
)
