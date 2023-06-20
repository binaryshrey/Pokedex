package dev.shreyansh.pokemon.pokedex.ui.pokemon

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentPokemonDetailBinding
import dev.shreyansh.pokemon.pokedex.network.response.PokemonRequest
import dev.shreyansh.pokemon.pokedex.utils.PokemonDetailsPagerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class PokemonDetailFragment : Fragment() {

    companion object {
        private val TABS = listOf<String>("About", "Base Stats","Evolution")
    }
    private lateinit var selectedPokemon : PokemonRequest
    private lateinit var binding : FragmentPokemonDetailBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        selectedPokemon = PokemonDetailFragmentArgs.fromBundle(requireArguments()).pokemon
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pokemon_detail, container, false)
        setTitleBarColorAndPokeCircle()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.pokemon = selectedPokemon

        binding.pokemonDetailsViewPager.adapter = PokemonDetailsPagerAdapter(requireActivity(), selectedPokemon)
        TabLayoutMediator(binding.pokemonDetailsTabLayout, binding.pokemonDetailsViewPager) { tab, position ->
            tab.text = TABS[position]
        }.attach()

        setSelectedPokemonImage()
        setupOnClickListeners()
        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.fav.setOnClickListener {
            binding.favIV.setImageResource(R.drawable.fav_saved)
            Toast.makeText(context,"Added to Favorites",Toast.LENGTH_SHORT).show()
        }
        binding.goBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun setSelectedPokemonImage() {
        Glide.with(requireActivity().applicationContext).load(selectedPokemon.imageUrl).into(binding.pokemonDetailIV)
        val rotateAnimation = ObjectAnimator.ofFloat(binding.pokeCircleIV, "rotation", 0f, 360f).apply {
            duration = 2000 // Adjust the duration as needed
            repeatCount = ObjectAnimator.INFINITE
            interpolator = LinearInterpolator()
        }
        rotateAnimation.start()
    }


    private fun setTitleBarColorAndPokeCircle() {
        when(selectedPokemon.type[0]){
            "Normal" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
                binding.pokeCircleIV.setImageResource(R.drawable.normal_circle)
            }
            "Fighting" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
                binding.pokeCircleIV.setImageResource(R.drawable.normal_circle)
            }
            "Flying" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
                binding.pokeCircleIV.setImageResource(R.drawable.normal_circle)
            }
            "Poison" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
                binding.pokeCircleIV.setImageResource(R.drawable.normal_circle)
            }
            "Ground" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1736C")
                binding.pokeCircleIV.setImageResource(R.drawable.ground_circle)
            }
            "Rock" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1736C")
                binding.pokeCircleIV.setImageResource(R.drawable.ground_circle)
            }
            "Bug" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#48D0B0")
                binding.pokeCircleIV.setImageResource(R.drawable.grass_circle)
            }
            "Ghost" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#92589E")
                binding.pokeCircleIV.setImageResource(R.drawable.psy_circle)
            }
            "Steel" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
                binding.pokeCircleIV.setImageResource(R.drawable.normal_circle)
            }
            "Fire" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#FB6C6C")
                binding.pokeCircleIV.setImageResource(R.drawable.fire_circle)
            }
            "Water" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#5EAEF9")
                binding.pokeCircleIV.setImageResource(R.drawable.water_circle)
            }
            "Grass" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#48D0B0")
                binding.pokeCircleIV.setImageResource(R.drawable.grass_circle)
            }
            "Electric" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#FFCE48")
                binding.pokeCircleIV.setImageResource(R.drawable.electric_circle)
            }
            "Ice" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#5EAEF9")
                binding.pokeCircleIV.setImageResource(R.drawable.water_circle)
            }
            "Dragon" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#FB6C6C")
                binding.pokeCircleIV.setImageResource(R.drawable.fire_circle)
            }
            "Psychic" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#92589E")
                binding.pokeCircleIV.setImageResource(R.drawable.psy_circle)
            }
            "Dark" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#92589E")
                binding.pokeCircleIV.setImageResource(R.drawable.psy_circle)
            }
            "Fairy" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
                binding.pokeCircleIV.setImageResource(R.drawable.normal_circle)
            }
            "Unknown" -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
                binding.pokeCircleIV.setImageResource(R.drawable.normal_circle)
            }
            else -> {
                requireActivity().window.statusBarColor = Color.parseColor("#B1B1B1")
                binding.pokeCircleIV.setImageResource(R.drawable.normal_circle)
            }

        }


    }
}