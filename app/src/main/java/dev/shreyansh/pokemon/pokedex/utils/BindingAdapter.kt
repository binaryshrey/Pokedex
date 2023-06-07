package dev.shreyansh.pokemon.pokedex.utils

import android.view.View
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import dev.shreyansh.pokemon.pokedex.viewModel.PokedexViewModel


@BindingAdapter("newsProgress")
fun setNewsProgress(linearLayout: LinearLayout, status: PokedexViewModel.PokeNewsAPIStatus?) {
    linearLayout.visibility = View.GONE
    status?.let {
        when (status) {
            PokedexViewModel.PokeNewsAPIStatus.LOADING -> linearLayout.visibility = View.VISIBLE
            PokedexViewModel.PokeNewsAPIStatus.ERROR -> linearLayout.visibility = View.GONE
            PokedexViewModel.PokeNewsAPIStatus.DONE -> linearLayout.visibility = View.GONE
        }
    }
}