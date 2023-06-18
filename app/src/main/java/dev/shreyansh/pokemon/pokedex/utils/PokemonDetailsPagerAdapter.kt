package dev.shreyansh.pokemon.pokedex.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.shreyansh.pokemon.pokedex.network.response.PokemonRequest
import dev.shreyansh.pokemon.pokedex.ui.pokemon.details.AboutPokemonFragment
import dev.shreyansh.pokemon.pokedex.ui.pokemon.details.BaseStatsFragment
import dev.shreyansh.pokemon.pokedex.ui.pokemon.details.EvolutionFragment


class PokemonDetailsPagerAdapter(fragmentActivity: FragmentActivity, private val selectedPokemon : PokemonRequest) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AboutPokemonFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("selectedPokemon", selectedPokemon)
                    }
                }
            }
            1 -> {
                BaseStatsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("selectedPokemon", selectedPokemon)
                    }
                }
            }
            2 -> {
                EvolutionFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("selectedPokemon", selectedPokemon)
                    }
                }
            }
            else -> {
                AboutPokemonFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("selectedPokemon", selectedPokemon)
                    }
                }
            }
        }
    }
}