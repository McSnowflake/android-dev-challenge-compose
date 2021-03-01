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

import com.beust.klaxon.Klaxon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.io.InputStream

@ExperimentalCoroutinesApi
object PuppyService {

    private val puppyBroadcast = BroadcastChannel<List<Puppy>>(1)

    @FlowPreview
    val puppies: Flow<List<Puppy>>
        get() = puppyBroadcast.asFlow()

    suspend fun load(puppyList: InputStream) =
        puppyBroadcast.send(Klaxon().converter(urlConverter).parseArray(puppyList)!!)
}
