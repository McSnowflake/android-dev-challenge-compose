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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.ViewState
import com.example.androiddevchallenge.ui.misc.WelcomeScreen
import com.example.androiddevchallenge.ui.puppy.PuppyDetail
import com.example.androiddevchallenge.ui.puppy.PuppyList
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.util.UUID

const val PUPPY_ID_KEY = "puppyId"

class MainActivity : AppCompatActivity() {

    private val viewState by viewModels<ViewState>()

    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewState.loadPuppies(assets.open("puppies.json"))

        setContent {
            MyTheme {
                MyApp(viewState)
            }
        }
    }
}

// Start building your app here!
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MyApp(viewState: ViewState = viewModel()) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "welcomeScreen") {
        composable("welcomeScreen") {
            WelcomeScreen {
                navController.navigate(
                    "puppyList"
                ) {
                    popUpTo("welcomeScreen") { inclusive = true }
                }
            }
        }
        composable("puppyList") {
            PuppyList(viewState) { navController.navigate("puppyDetail/$it") }
        }
        composable("puppyDetail/{$PUPPY_ID_KEY}") { backStackEntry ->
            val puppyId = backStackEntry.arguments?.getString(PUPPY_ID_KEY)
            if (puppyId == null)
                navController.popBackStack()
            else {
                viewState.selectedPuppy.value =
                    viewState.puppies.find { it.id == UUID.fromString(puppyId) }
                PuppyDetail(viewState) { navController.popBackStack() }
            }
        }
    }
}

// @ExperimentalFoundationApi
// @ExperimentalAnimationApi
// @Preview("Light Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun LightPreview() {
//    MyTheme {
//        MyApp()
//    }
// }
//
// // File("app/src/main/assets/puppies.json").inputStream()
// @ExperimentalFoundationApi
// @ExperimentalAnimationApi
// @Preview("Dark Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
// }
