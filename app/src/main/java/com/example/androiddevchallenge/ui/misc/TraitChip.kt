package com.example.androiddevchallenge.ui.misc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Trait

@Composable
fun TraitChip(trait: Trait) = Card(
    modifier = Modifier
        .padding(4.dp)
        .clickable { trait.isSelected.value = !trait.isSelected.value },
    elevation = if (trait.isSelected.value) 4.dp else 0.dp,
    backgroundColor = if (trait.isSelected.value) MaterialTheme.colors.primary
    else Color.Gray
) {
    Text(trait.name, modifier = Modifier.padding(4.dp), textAlign = TextAlign.Center)
}