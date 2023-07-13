package dev.shreyansh.pokemon.pokedex.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentNewsBinding
import dev.shreyansh.pokemon.pokedex.databinding.FragmentSearchNewsBinding
import dev.shreyansh.pokemon.pokedex.domain.PokemonNews
import dev.shreyansh.pokemon.pokedex.utils.PokeNewsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class SearchNewsFragment : Fragment() {

    private lateinit var binding : FragmentSearchNewsBinding
    private lateinit var pokeNewsRecyclerAdapter : PokeNewsRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search_news, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        pokedexViewModel.savedSearchNews.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty()){
                binding.emptyNewsResultIV.visibility = View.VISIBLE
            } else {
                binding.emptyNewsResultIV.visibility = View.GONE
                pokeNewsRecyclerAdapter.submitList(it.toMutableList())
            }
        })
    }

    private fun setupRecyclerView() {
        pokeNewsRecyclerAdapter = PokeNewsRecyclerAdapter(PokeNewsRecyclerAdapter.OnClickListener{
            navigateToNewsItem(it)
        }, requireActivity())
        binding.searchNewsRecyclerView.adapter = pokeNewsRecyclerAdapter
    }

    private fun navigateToNewsItem(news: PokemonNews) {
        val navigateIntent = Intent(Intent.ACTION_VIEW)
        navigateIntent.data = Uri.parse("https://api.pokemon.com${news.url}")
        startActivity(navigateIntent)
    }


}