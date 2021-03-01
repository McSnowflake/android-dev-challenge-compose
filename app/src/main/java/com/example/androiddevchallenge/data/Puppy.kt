/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import java.net.URL
import java.util.UUID

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
