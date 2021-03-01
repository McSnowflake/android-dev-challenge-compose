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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = teal800,
    primaryVariant = tealLight,
    onPrimary = Color.White,
    secondary = brown700,
    secondaryVariant = brownLight,
    onSecondary = Color.White,
    background = Color.Black,
    onBackground = Color.White,
    surface = brownVeryDark,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = teal800,
    primaryVariant = tealDark,
    onPrimary = Color.Black,
    secondary = brown700,
    secondaryVariant = brownDark,
    onSecondary = Color.White,
    background = brown50,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = brownVeryDark

    /* Other default colors to override
surface = Color.White,

onBackground = Color.Black,
onSurface = Color.Black,
*/
)

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
