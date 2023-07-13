package dev.shreyansh.pokemon.pokedex.ui.search

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.FragmentSearchContainerBinding
import dev.shreyansh.pokemon.pokedex.utils.SearchPagerAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModelFactory


class SearchContainerFragment : Fragment() {

    private lateinit var binding : FragmentSearchContainerBinding
    private val pokedexViewModel: PokedexViewModel by activityViewModels {
        PokedexViewModelFactory(requireNotNull(this.activity).application)
    }

    companion object {
        private val TABS = listOf<String>("Pokemon", "Moves", "Abilities", "Items", "Locations", "Types", "News")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setStatusBarColor()

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search_container, container, false)
        pokedexViewModel.resetSearchFields()

        binding.searchViewPager.adapter = SearchPagerAdapter(requireActivity())
        TabLayoutMediator(binding.searchTabLayout, binding.searchViewPager) { tab, position ->
            tab.text = TABS[position]
        }.attach()


        binding.searchEditText.postDelayed(Runnable { showSoftKeyboard(binding.searchEditText)} , 50)

        binding.searchToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH && binding.searchEditText.text.toString().isNotEmpty()){
                searchDB()
                true
            } else {
                false
            }
        }


        pokedexViewModel.appTheme.observe(viewLifecycleOwner, Observer {
            when(it){
                "Light Mode" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "Dark Mode" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                "System Default" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        })





        return binding.root
    }

    private fun setStatusBarColor() {
        when(requireContext().resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_YES -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
            Configuration.UI_MODE_NIGHT_NO -> requireActivity().window.statusBarColor = Color.parseColor("#ffffff")
            else -> requireActivity().window.statusBarColor = Color.parseColor("#000000")
        }
    }

    private fun searchDB() {
        val inputMethodManager: InputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.searchEditText.clearFocus()
        inputMethodManager.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)

        pokedexViewModel.searchPokemon(binding.searchEditText.text.toString()).observe(viewLifecycleOwner, Observer {
            it?.let {
                pokedexViewModel.savedSearchPokemon.value = it
            }
        })
        pokedexViewModel.searchMoves(binding.searchEditText.text.toString()).observe(viewLifecycleOwner, Observer {
            it?.let {
                pokedexViewModel.savedSearchMoves.value = it
            }
        })
        pokedexViewModel.searchAbilities(binding.searchEditText.text.toString()).observe(viewLifecycleOwner, Observer {
            it?.let {
                pokedexViewModel.savedSearchAbilities.value = it
            }
        })
        pokedexViewModel.searchItems(binding.searchEditText.text.toString()).observe(viewLifecycleOwner, Observer {
            it?.let {
                pokedexViewModel.savedSearchItems.value = it
            }
        })
        pokedexViewModel.searchLocations(binding.searchEditText.text.toString()).observe(viewLifecycleOwner, Observer {
            it?.let {
                pokedexViewModel.savedSearchLocations.value = it
            }
        })
        pokedexViewModel.searchTypes(binding.searchEditText.text.toString()).observe(viewLifecycleOwner, Observer {
            it?.let {
                pokedexViewModel.savedSearchTypes.value = it
            }
        })
        pokedexViewModel.searchNews(binding.searchEditText.text.toString()).observe(viewLifecycleOwner, Observer {
            it?.let {
                pokedexViewModel.savedSearchNews.value = it
            }
        })

    }

    private fun showSoftKeyboard(view: View) {
        val inputMethodManager: InputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        inputMethodManager.showSoftInput(view, 0)
    }

}