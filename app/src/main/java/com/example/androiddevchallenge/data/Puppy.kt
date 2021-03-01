package com.example.androiddevchallenge.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import java.net.URL
import java.util.*

data class Puppy(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val breed: String,
    val age: Int,
    val imgUrls: List<String>,
    val description: String = "",
    val traits: List<String> = emptyList(),
    val favorite: MutableState<Boolean> = mutableStateOf(false)
)

val urlConverter = object : Converter {
    override fun canConvert(cls: Class<*>) = cls == URL::class.java

    override fun toJson(value: Any) = value.toString()

    override fun fromJson(jv: JsonValue) = URL(jv.string)

}
