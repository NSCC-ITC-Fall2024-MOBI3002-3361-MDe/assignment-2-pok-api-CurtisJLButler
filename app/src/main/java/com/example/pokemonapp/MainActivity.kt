package com.example.pokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.pokemonapp.models.Pokemon
import com.example.pokemonapp.models.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonApp()
        }
    }
}

@Composable
fun PokemonApp(pokemonViewModel: PokemonViewModel = viewModel()) {
    var pokemonName by remember { mutableStateOf(TextFieldValue("")) }
    val pokemon by pokemonViewModel.pokemonState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pokémon Lookup",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = pokemonName,
            onValueChange = { pokemonName = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Button(onClick = {
            pokemonViewModel.fetchPokemon(pokemonName.text.lowercase())
        }) {
            Text("Go")
        }

        Spacer(modifier = Modifier.height(24.dp))

        pokemon?.let { nonNullPokemon ->
            PokemonDetails(nonNullPokemon)
        } ?: run {
            Text("No Pokémon found")
        }
    }
}

@Composable
fun PokemonDetails(pokemon: Pokemon) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberAsyncImagePainter(pokemon.sprites.front_default),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Text(text = "Name: ${pokemon.name.replaceFirstChar { it.uppercase() }}")
        Text(text = "Weight: ${pokemon.weight}")
        Text(text = "Height: ${pokemon.height}")
        Text(text = "Types: ${pokemon.types.joinToString { it.type.name.replaceFirstChar { it.uppercase() } }}")
    }
}
