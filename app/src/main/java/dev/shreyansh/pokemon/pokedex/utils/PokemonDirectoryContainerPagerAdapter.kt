package dev.shreyansh.pokemon.pokedex.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.shreyansh.pokemon.pokedex.ui.pokemon.FavPokemonFragment
import dev.shreyansh.pokemon.pokedex.ui.pokemon.PokemonDirectoryFragment


class PokemonDirectoryContainerPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PokemonDirectoryFragment()
            1 -> FavPokemonFragment()
            else -> PokemonDirectoryFragment()
        }
    }
}