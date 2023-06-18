package dev.shreyansh.pokemon.pokedex.ui.pokemon

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentPokemonDirectoryContainerBinding
import dev.shreyansh.pokemon.pokedex.utils.PokemonDirectoryContainerPagerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class PokemonDirectoryContainerFragment : Fragment() {

    companion object {
        private val TABS = listOf<String>("Pokemon", "Favorites")
    }
    private lateinit var binding : FragmentPokemonDirectoryContainerBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setStatusBarColor()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pokemon_directory_container, container, false)

        binding.pokemonViewPager.adapter = PokemonDirectoryContainerPagerAdapter(requireActivity())
        TabLayoutMediator(binding.pokemonTabLayout, binding.pokemonViewPager) { tab, position ->
            tab.text = TABS[position]
        }.attach()

        setupOnClickListeners()



        return binding.root
    }

    private fun setStatusBarColor() {
        when(requireContext().resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_YES -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
            Configuration.UI_MODE_NIGHT_NO -> requireActivity().window.statusBarColor = Color.parseColor("#ffffff")
            else -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
        }
    }

    private fun setupOnClickListeners() {
        binding.pokedexContainerToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


}