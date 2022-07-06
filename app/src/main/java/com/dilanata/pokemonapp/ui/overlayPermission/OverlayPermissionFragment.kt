package com.dilanata.pokemonapp.ui.overlayPermission

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.Fragment
import com.dilanata.pokemonapp.R
import com.dilanata.pokemonapp.base.BaseFragment
import com.dilanata.pokemonapp.databinding.FragmentOverlayPermissionBinding
import com.dilanata.pokemonapp.extension.navigateSafe
import com.dilanata.pokemonapp.service.ForegroundService
import com.dilanata.pokemonapp.ui.pokemons.adapter.OnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@Suppress("COMPATIBILITY_WARNING")
@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class OverlayPermissionFragment : BaseFragment<FragmentOverlayPermissionBinding>(R.layout.fragment_overlay_permission) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOverlay.setOnClickListener {
                checkOverlayPermission()
                startService()
        }
    }
    // method for starting the service
    fun startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // check if the user has already granted
            // the Draw over other apps permission
            if (Settings.canDrawOverlays(requireContext())) {
                // start the service based on the android version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    navigateSafe(R.id.action_overlayPermissionFragment_to_pokemonsFragment)
                  //  startForegroundService(requireContext(), Intent(requireContext(), ForegroundService::class.java))
                } else {
                    startService()
                }
            }
        } else {
            startService()
        }
    }
    // method to ask user to grant the Overlay permission
    fun checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(requireContext())) {
                // send user to the device settings
                val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                startActivity(myIntent)
            }
        }
    }

    // check for permission again when user grants it from
    // the device settings, and start the service
    override fun onResume() {
        super.onResume()
        startService()
    }

}