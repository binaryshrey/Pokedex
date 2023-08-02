package dev.shreyansh.pokemon.pokedex.ui.news

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentNewsBinding
import dev.shreyansh.pokemon.pokedex.domain.PokemonNews
import dev.shreyansh.pokemon.pokedex.utils.PokeNewsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class NewsFragment : Fragment() {


    private lateinit var binding : FragmentNewsBinding
    private lateinit var pokeNewsRecyclerAdapter : PokeNewsRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        setStatusBarColor()

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_news, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setupPokeNewsRecyclerView()
        setupObservers()
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
        binding.moreToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun setupPokeNewsRecyclerView() {
        pokeNewsRecyclerAdapter = PokeNewsRecyclerAdapter(PokeNewsRecyclerAdapter.OnClickListener{
            navigateToNewsItem(it)
        }, requireActivity())
        binding.pokeNewsRV.adapter = pokeNewsRecyclerAdapter
    }


    private fun setupObservers() {
        pokedexViewModel.pokeNewsResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokeNewsRecyclerAdapter.submitList(it.toMutableList())
            }
        })
    }

    private fun navigateToNewsItem(news: PokemonNews) {
        val navigateIntent = Intent(Intent.ACTION_VIEW)
        navigateIntent.data = Uri.parse("https://api.pokemon.com${news.url}")
        startActivity(navigateIntent)
    }
}