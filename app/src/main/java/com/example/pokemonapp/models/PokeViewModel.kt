package com.example.pokemonapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PokemonViewModel : ViewModel() {
    private val _pokemonState = MutableStateFlow<Pokemon?>(null)
    val pokemonState: StateFlow<Pokemon?> get() = _pokemonState

    private val pokeApiService = PokeApiService.create()

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                val pokemon = pokeApiService.getPokemon(name)
                _pokemonState.value = pokemon
            } catch (e: Exception) {
                _pokemonState.value = null
                e.printStackTrace()
            }
        }
    }
}
