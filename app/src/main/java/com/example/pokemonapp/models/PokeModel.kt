package com.example.pokemonapp.models

data class Pokemon(
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: Sprites,
    val types: List<TypeInfo>
)

data class Sprites(
    val front_default: String
)

data class TypeInfo(
    val type: Type
)

data class Type(
    val name: String
)
