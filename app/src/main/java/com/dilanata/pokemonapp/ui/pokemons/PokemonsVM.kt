package com.dilanata.pokemonapp.ui.pokemons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilanata.pokemonapp.api.model.Pokemon
import com.dilanata.pokemonapp.data.Resource
import com.dilanata.pokemonapp.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsVM @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {
    private val _pokemon = MutableLiveData<Resource<Pokemon>>()
    val pokemon: LiveData<Resource<Pokemon>>
    get() = _pokemon
    fun getPokemons() {
        viewModelScope.launch {
            pokemonRepository.getPokemons().collect {
                _pokemon.postValue(it)
            }
        }
    }
}