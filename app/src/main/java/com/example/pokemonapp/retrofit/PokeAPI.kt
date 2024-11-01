package com.example.pokemonapp.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("api/v2/pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon

    companion object {
        private const val BASE_URL = "https://pokeapi.co/"

        fun create(): PokeApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PokeApiService::class.java)
        }
    }
}
