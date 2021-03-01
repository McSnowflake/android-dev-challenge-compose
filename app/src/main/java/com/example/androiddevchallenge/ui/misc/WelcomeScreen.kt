package com.example.androiddevchallenge.ui.misc

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalAnimationApi
@Composable
fun WelcomeScreen(onSwipe: () -> Unit) = AnimatedVisibility(
    visible = true,
    exit = slideOutHorizontally(targetOffsetX = { -40 }) + fadeOut()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8F)
            ) {
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        imgUrl = "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Save the Puppy!",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5F)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "This is my submission to the 2021 Jetpack Compose Android Dev Challenge",
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Pictures are from www.pexels.com",
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Author: Franziskus Wild",
                    modifier = Modifier.fillMaxWidth()
                )
            }



            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3F),
                onClick = { onSwipe() }) {
                Text(text = "Let's see what you got.")
            }
        }
    }
}