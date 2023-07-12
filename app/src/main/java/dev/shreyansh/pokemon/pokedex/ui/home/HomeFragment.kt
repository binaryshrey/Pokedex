package dev.shreyansh.pokemon.pokedex.ui.home

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
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentHomeBinding
import dev.shreyansh.pokemon.pokedex.domain.PokemonNews
import dev.shreyansh.pokemon.pokedex.utils.PokeNewsRecyclerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class HomeFragment : Fragment() {

    private var firstName : String = ""
    private lateinit var binding : FragmentHomeBinding
    private lateinit var pokeNewsRecyclerAdapter : PokeNewsRecyclerAdapter
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        setStatusBarColor()

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.viewModel = pokedexViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initUser()
        getPokemonNews()
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
        binding.moreNewsTV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsFragment())
        }
        binding.pokemonCV.setOnClickListener {
            pokedexViewModel.updatePokemonFilter("all")
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPokemonDirectoryContainerFragment())
        }
        binding.movesCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovesFragment())
        }
        binding.abilityCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAbilitiesFragment())
        }
        binding.itemsCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToItemsFragment())
        }
        binding.locationCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLocationsFragment())
        }
        binding.typesCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTypesFragment())
        }
        binding.quizCV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQuizIntroFragment())
        }

    }


    private fun getPokemonNews() {
        pokedexViewModel.getPokeNews()
    }


    private fun setupPokeNewsRecyclerView() {
        pokeNewsRecyclerAdapter = PokeNewsRecyclerAdapter(PokeNewsRecyclerAdapter.OnClickListener{
            navigateToNewsItem(it)
        }, requireActivity())
        binding.pokemonNewsRV.adapter = pokeNewsRecyclerAdapter
    }


    private fun setupObservers() {
        pokedexViewModel.pokeNewsResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokeNewsRecyclerAdapter.submitList(it.take(25).toMutableList())
            }
        })
    }

    private fun initUser() {
        firstName = FirebaseAuth.getInstance().currentUser?.displayName?.split("\\s".toRegex())?.toTypedArray()?.get(0).toString()
        binding.userName.text = "Hey, ${firstName}! 👋"
        Glide.with(requireContext())
            .load(FirebaseAuth.getInstance().currentUser?.photoUrl)
            .placeholder(R.drawable.profile)
            .error(R.drawable.profile)
            .into(binding.userProfilePic);
    }

    private fun navigateToNewsItem(news: PokemonNews) {
        val navigateIntent = Intent(Intent.ACTION_VIEW)
        navigateIntent.data = Uri.parse("https://api.pokemon.com${news.url}")
        startActivity(navigateIntent)
    }



}