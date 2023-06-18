package dev.shreyansh.pokemon.pokedex.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.shreyansh.pokemon.pokedex.ui.pokemon.details.AboutPokemonFragment
import dev.shreyansh.pokemon.pokedex.ui.pokemon.details.BaseStatsFragment
import dev.shreyansh.pokemon.pokedex.ui.pokemon.details.EvolutionFragment


class PokemonDetailsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AboutPokemonFragment()
            1 -> BaseStatsFragment()
            2 -> EvolutionFragment()
            else -> AboutPokemonFragment()
        }
    }
}