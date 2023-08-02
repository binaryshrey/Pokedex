package dev.shreyansh.pokemon.pokedex.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.shreyansh.pokemon.pokedex.ui.search.*

class SearchPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchPokemonFragment()
            1 -> SearchMovesFragment()
            2 -> SearchAbilitiesFragment()
            3 -> SearchItemsFragment()
            4 -> SearchLocationsFragment()
            5 -> SearchTypesFragment()
            6 -> SearchNewsFragment()
            else -> SearchPokemonFragment()
        }
    }
}