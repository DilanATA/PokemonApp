package com.dilanata.pokemonapp.api

import com.dilanata.pokemonapp.api.model.Pokemon
import retrofit2.http.GET

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(): Pokemon
}