package com.example.androiddevchallenge.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.beust.klaxon.Klaxon
import java.io.InputStream

class ViewState(puppiesData: InputStream, private val navController: NavHostController) :
    ViewModel() {
    val puppies = Klaxon().converter(urlConverter).parseArray<Puppy>(puppiesData)!!
    val selectedTraits: SnapshotStateList<Trait> = puppies
        .flatMap { it.traits }
        .distinct()
        .map { Trait(it) }
        .toMutableStateList()
    val showOnlyFavorites: MutableState<Boolean> = mutableStateOf(false)
    val showFilters: MutableState<Boolean> = mutableStateOf(false)

    fun toggleShowFavoritesOnly(state: Boolean) {
        showOnlyFavorites.value = state
    }

    fun toggleShowFilters() {
        showFilters.value = !showFilters.value
    }

    private val selectedPuppy = mutableStateOf<Puppy?>(null)

    fun getSelectedPuppy() = selectedPuppy.value!!
    fun selectPuppy(puppy: Puppy) {
        selectedPuppy.value = puppy
        navController.navigate("puppyDetail")
    }

    fun deselectPuppy() {
        selectedPuppy.value = null
        navController.popBackStack()
    }
}