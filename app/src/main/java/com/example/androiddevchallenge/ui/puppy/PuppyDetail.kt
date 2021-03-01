package com.example.androiddevchallenge.ui.puppy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.ViewState
import com.example.androiddevchallenge.ui.misc.Image
import com.example.androiddevchallenge.ui.misc.LikeButton

@ExperimentalAnimationApi
@Composable
fun PuppyDetail(
    viewState: ViewState,
    modifier: Modifier
) =
    AnimatedVisibility(
        visible = true,
        enter = expandVertically(
            expandFrom = Alignment.CenterVertically,
            animationSpec = tween(),
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.CenterVertically
        ),
        initiallyVisible = false
    ) {
        Column(modifier = modifier.fillMaxSize()) {

            TopAppBar(
                title = { Text(text = viewState.getSelectedPuppy().name) },
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = {
                    IconButton(onClick = viewState::deselectPuppy) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Close puppy.value Detail View"
                        )
                    }
                },
                actions = { LikeButton(puppy = viewState.getSelectedPuppy()) })
            Surface(
                color = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(3F)
                                .horizontalScroll(rememberScrollState()),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                8.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {
                            viewState.getSelectedPuppy().imgUrls.toList().map {
                                Image(
                                    it,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(5.dp)),
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(5F)
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${viewState.getSelectedPuppy().name} is a ${viewState.getSelectedPuppy().age} months old ${viewState.getSelectedPuppy().breed}",
                                fontSize = 18.sp,
                                modifier = Modifier.paddingFromBaseline(top = 8.dp),
                                fontWeight = Bold
                            )
                            Text(
                                text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                                fontSize = 14.sp,
                                modifier = Modifier.paddingFromBaseline(top = 20.dp)
                            )
                            Text(
                                text = "Traits: ${viewState.getSelectedPuppy().traits.joinToString(", ")}",
                                fontSize = 14.sp,
                                modifier = Modifier.paddingFromBaseline(top = 20.dp)
                            )
                        }
                    }


                }


            }
        }
    }