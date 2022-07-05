package com.dilanata.pokemonapp.ui.pokemons.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dilanata.pokemonapp.R
import com.dilanata.pokemonapp.api.model.Result
import com.dilanata.pokemonapp.databinding.ItemPokemonBinding

class PokemonAdapter(
    private val context: Context,
    private var pokemonList: List<Result>,
    private val onClickListener: OnClickListener
): RecyclerView.Adapter<PokemonAdapter.PokemonHolder>()  {
   inner class PokemonHolder(val itemPokemonBinding: ItemPokemonBinding) : RecyclerView.ViewHolder(
       itemPokemonBinding.root
   ){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder =
        PokemonHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_pokemon,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.itemPokemonBinding.pokemon = pokemonList[position]

        holder.itemPokemonBinding.cvPokemon.setOnClickListener {
            onClickListener.onClickForDetail(pokemonList[position])
        }
    }

    override fun getItemCount(): Int = pokemonList.size
}
interface OnClickListener {
    fun onClickForDetail(pokemonResult: Result)
}