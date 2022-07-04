package com.dilanata.pokemonapp.repository

import com.dilanata.pokemonapp.api.PokemonApi
import com.dilanata.pokemonapp.api.model.Pokemon
import com.dilanata.pokemonapp.data.Resource
import com.github.ajalt.timberkt.i
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    fun getPokemons(): Flow<Resource<Pokemon>> {
        return flow{
            emit(Resource.loading(null))
            val pokemon = pokemonApi.getPokemons()
            emit(Resource.success(pokemon))
        }.retryWhen { cause, attempt ->
            i { "attempt count -> $attempt" }
            i { "cause -> $cause" }
            (cause is Exception).also { if (it) delay(10_000) }
        }.catch {
            emit(Resource.error(it.localizedMessage, null, it))
        }.flowOn(Dispatchers.IO)
    }
}