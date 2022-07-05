package com.dilanata.pokemonapp.ui.pokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.dilanata.pokemonapp.R
import com.dilanata.pokemonapp.api.model.Pokemon
import com.dilanata.pokemonapp.api.model.Result
import com.dilanata.pokemonapp.base.BaseFragment
import com.dilanata.pokemonapp.data.Status
import com.dilanata.pokemonapp.databinding.FragmentPokemonsBinding
import com.dilanata.pokemonapp.extension.hide
import com.dilanata.pokemonapp.extension.makeToast
import com.dilanata.pokemonapp.extension.show
import com.dilanata.pokemonapp.ui.pokemons.adapter.OnClickListener
import com.dilanata.pokemonapp.ui.pokemons.adapter.PokemonAdapter
import com.github.ajalt.timberkt.i
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@Suppress("COMPATIBILITY_WARNING")
@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class PokemonsFragment : BaseFragment<FragmentPokemonsBinding>(R.layout.fragment_pokemons),
OnClickListener{

    var pokemonList : List<Result>? = arrayListOf()
    var pokemons: Pokemon? = null
    lateinit var pokemonAdapter: PokemonAdapter
    var bundle = Bundle()

    private val pokemonsVM: PokemonsVM by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPokemons()
    }
    private fun getPokemons() {
        binding.progressBar.show()
        observePokemon()
    }

    private fun observePokemon() {
        scope.launch{
            pokemonsVM.getPokemons()
            pokemonsVM.pokemon.observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        pokemons = it.data!!
                        pokemonList = pokemons!!.results
                        if (pokemonList.isNullOrEmpty()) {
                            makeToast("No data found!")
                        } else {
                            pokemonAdapter = PokemonAdapter(
                                requireContext(),
                                pokemonList!!,
                                this@PokemonsFragment
                            )
                            pokemonAdapter.notifyDataSetChanged()
                            binding.rvPokemons.adapter = pokemonAdapter
                            binding.progressBar.hide()
                        }
                    }
                    Status.ERROR -> i { "error ${it.throwable}" }
                    Status.LOADING -> i { "Loading" }
                }
            }
        }
    }
    override fun onClickForDetail(pokemonResult: Result) {
        makeToast(pokemonResult.name!!)
    }

}