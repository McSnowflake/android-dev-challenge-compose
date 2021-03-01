package com.example.androiddevchallenge.ui.puppy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.ViewState
import com.example.androiddevchallenge.ui.misc.Image
import com.example.androiddevchallenge.ui.misc.LikeButton

@Composable
fun PuppyItem(puppy: Puppy, viewState: ViewState) = Card(
    elevation = 1.dp,
    modifier = Modifier
        .fillMaxWidth()
        .height(96.dp)
        .clickable { viewState.selectPuppy(puppy) }
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            puppy.imgUrls[0],
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1F, true)
                .clip(RoundedCornerShape(5.dp)),
            contentScale = ContentScale.Crop
        )
        InfoBox(Modifier.weight(1F), puppy)
    }
}

@Composable
private fun InfoBox(modifier: Modifier, puppy: Puppy) = Column(
    verticalArrangement = Arrangement.Top,
    modifier = modifier.padding(start = 24.dp, end = 4.dp, top = 4.dp)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = puppy.name,
            fontSize = 18.sp,
            fontWeight = Bold,
            modifier = Modifier.paddingFromBaseline(top = 32.dp)
        )
        LikeButton(puppy = puppy, modifier = Modifier)
    }
    Text(
        text = "${puppy.age} months old ${puppy.breed}", fontSize = 14.sp,
        modifier = Modifier.paddingFromBaseline(top = 16.dp)
    )
}
