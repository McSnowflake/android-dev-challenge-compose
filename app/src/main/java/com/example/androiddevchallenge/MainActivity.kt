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
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.ViewState
import com.example.androiddevchallenge.ui.misc.WelcomeScreen
import com.example.androiddevchallenge.ui.puppy.PuppyDetail
import com.example.androiddevchallenge.ui.puppy.PuppyList
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.io.File
import java.io.InputStream

const val PUPPY_ID_KEY = "puppyId"

class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MyTheme {
                MyApp(assets.open("puppies.json"))
            }
        }
    }

}


// Start building your app here!
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MyApp(puppyData: InputStream) {

    val navController = rememberNavController()
    val viewState = remember { ViewState(puppyData, navController) }

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
        composable("puppyList") { PuppyList(viewState) }
        composable("puppyDetail") {
            PuppyDetail(viewState, modifier = Modifier.fillMaxSize())
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(File("app/src/main/assets/puppies.json").inputStream())
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(File("app/src/main/assets/puppies.json").inputStream())
    }
}
