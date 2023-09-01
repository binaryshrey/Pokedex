package dev.shreyansh.pokemon.pokedex.ui.pokemon.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentAboutPokemonBinding
import dev.shreyansh.pokemon.pokedex.domain.Pokemon

@AndroidEntryPoint
class AboutPokemonFragment : Fragment() {

    private lateinit var selectedPokemon : Pokemon
    private lateinit var binding : FragmentAboutPokemonBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        selectedPokemon = requireArguments().getParcelable("selectedPokemon")!!
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_about_pokemon, container, false)
        binding.pokemon = selectedPokemon
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


}