package com.dilanata.pokemonapp.ui.pokemonDetail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.DialogFragment
import com.dilanata.pokemonapp.R
import com.dilanata.pokemonapp.base.BaseFragment
import com.dilanata.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.dilanata.pokemonapp.databinding.FragmentPokemonsBinding
import com.dilanata.pokemonapp.service.ForegroundService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Suppress("COMPATIBILITY_WARNING")
@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment<FragmentPokemonDetailBinding>(R.layout.fragment_pokemon_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnShowOverlay.setOnClickListener {
            startForegroundService(requireContext(), Intent(requireContext(), ForegroundService::class.java))
        }
    }

}