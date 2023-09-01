package dev.shreyansh.pokemon.pokedex.ui.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentPokemonDirectoryBinding
import dev.shreyansh.pokemon.pokedex.databinding.FragmentPokemonFiltersBinding
import dev.shreyansh.pokemon.pokedex.utils.PokemonRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory

@AndroidEntryPoint
class PokemonFiltersFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentPokemonFiltersBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_pokemon_filters, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setupOnClickListeners()


        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.genAllCV.setOnClickListener {
            Toast.makeText(context,"All Pokemons",Toast.LENGTH_SHORT).show()
            pokedexViewModel.updatePokemonFilter("all")
            dismiss()
        }
        binding.gen1CV.setOnClickListener {
            Toast.makeText(context,"Generation-I Pokemons",Toast.LENGTH_SHORT).show()
            pokedexViewModel.updatePokemonFilter("one")
            dismiss()
        }
        binding.gen2CV.setOnClickListener {
            Toast.makeText(context,"Generation-II Pokemons",Toast.LENGTH_SHORT).show()
            pokedexViewModel.updatePokemonFilter("two")
            dismiss()
        }
        binding.gen3CV.setOnClickListener {
            Toast.makeText(context,"Generation-III Pokemons",Toast.LENGTH_SHORT).show()
            pokedexViewModel.updatePokemonFilter("three")
            dismiss()
        }
        binding.gen4CV.setOnClickListener {
            Toast.makeText(context,"Generation-IV Pokemons",Toast.LENGTH_SHORT).show()
            pokedexViewModel.updatePokemonFilter("four")
            dismiss()
        }
        binding.gen5CV.setOnClickListener {
            Toast.makeText(context,"Generation-V Pokemons",Toast.LENGTH_SHORT).show()
            pokedexViewModel.updatePokemonFilter("five")
            dismiss()
        }
        binding.gen6CV.setOnClickListener {
            Toast.makeText(context,"Generation-VI Pokemons",Toast.LENGTH_SHORT).show()
            pokedexViewModel.updatePokemonFilter("six")
            dismiss()
        }
        binding.gen7CV.setOnClickListener {
            Toast.makeText(context,"Generation-VII Pokemon",Toast.LENGTH_SHORT).show()
            pokedexViewModel.updatePokemonFilter("seven")
            dismiss()
        }
        binding.gen8CV.setOnClickListener {
            Toast.makeText(context,"Generation-VIII Pokemon",Toast.LENGTH_SHORT).show()
            pokedexViewModel.updatePokemonFilter("eight")
            dismiss()
        }



    }


}