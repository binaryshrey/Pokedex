package dev.shreyansh.pokemon.pokedex.ui.pokemon

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentPokemonDetailBinding
import dev.shreyansh.pokemon.pokedex.network.response.PokemonRequest


class PokemonDetailFragment : Fragment() {

    private lateinit var selectedPokemon : PokemonRequest
    private lateinit var binding : FragmentPokemonDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        selectedPokemon = PokemonDetailFragmentArgs.fromBundle(requireArguments()).pokemon
        setTitleBarColor()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pokemon_detail, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.pokemon = selectedPokemon

        setSelectedPokemonImage()
        return binding.root
    }


    private fun setSelectedPokemonImage() {
        Glide.with(requireActivity().applicationContext).load(selectedPokemon.imageUrl).into(binding.pokemonDetailIV)
    }


    private fun setTitleBarColor() {
        when(selectedPokemon.type[0]){
            "Normal" -> requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
            "Fighting" -> requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
            "Flying" -> requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
            "Poison" -> requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
            "Ground" -> requireActivity().window.statusBarColor = Color.parseColor("#B1736C")
            "Rock" -> requireActivity().window.statusBarColor = Color.parseColor("#B1736C")
            "Bug" -> requireActivity().window.statusBarColor = Color.parseColor("#48D0B0")
            "Ghost" -> requireActivity().window.statusBarColor = Color.parseColor("#92589E")
            "Steel" -> requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
            "Fire" -> requireActivity().window.statusBarColor = Color.parseColor("#FB6C6C")
            "Water" -> requireActivity().window.statusBarColor = Color.parseColor("#5EAEF9")
            "Grass" -> requireActivity().window.statusBarColor = Color.parseColor("#48D0B0")
            "Electric" -> requireActivity().window.statusBarColor = Color.parseColor("#FFCE48")
            "Ice" -> requireActivity().window.statusBarColor = Color.parseColor("#5EAEF9")
            "Dragon" -> requireActivity().window.statusBarColor = Color.parseColor("#FB6C6C")
            "Psychic" -> requireActivity().window.statusBarColor = Color.parseColor("#92589E")
            "Dark" -> requireActivity().window.statusBarColor = Color.parseColor("#92589E")
            "Fairy" -> requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
            "Unknown" -> requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
            else -> requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")

        }


    }
}