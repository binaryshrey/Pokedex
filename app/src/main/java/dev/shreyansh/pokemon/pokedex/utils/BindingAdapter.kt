package dev.shreyansh.pokemon.pokedex.utils

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import dev.shreyansh.pokemon.pokedex.R
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


@BindingAdapter("pokemonProgress")
fun setPokemonProgress(progressBar: ProgressBar, status: PokedexViewModel.PokeMonAPIStatus?) {
    progressBar.visibility = View.GONE
    status?.let {
        when (status) {
            PokedexViewModel.PokeMonAPIStatus.LOADING -> progressBar.visibility = View.VISIBLE
            PokedexViewModel.PokeMonAPIStatus.ERROR -> progressBar.visibility = View.GONE
            PokedexViewModel.PokeMonAPIStatus.DONE -> progressBar.visibility = View.GONE
        }
    }
}


@BindingAdapter("pokemonName")
fun setPokeMonName(textView: TextView, name:String?){
    name?.let {
        textView.text = name.toString()
    }
}

@BindingAdapter("pokemonId")
fun setPokeMonId(textView: TextView, id:String?){
    id?.let {
        textView.text = id.toString()
    }
}


@BindingAdapter("pokemonTypeTV")
fun setPokemonTypeTV(textView: TextView, type:List<String>?){
    type?.let {
        textView.text = type[0].toString()
    }
}



@BindingAdapter("pokemonType")
fun setPokeMonType(imageView: ImageView, type:List<String>?){
    type?.let {
        when(type[0]){
            "Normal" -> imageView.setImageResource(R.drawable.normal_bg)
            "Fighting" -> imageView.setImageResource(R.drawable.normal_bg)
            "Flying" -> imageView.setImageResource(R.drawable.normal_bg)
            "Poison" -> imageView.setImageResource(R.drawable.normal_bg)
            "Ground" -> imageView.setImageResource(R.drawable.ground_bg)
            "Rock" -> imageView.setImageResource(R.drawable.ground_bg)
            "Bug" -> imageView.setImageResource(R.drawable.grass_bg)
            "Ghost" -> imageView.setImageResource(R.drawable.psykic_bg)
            "Steel" -> imageView.setImageResource(R.drawable.normal_bg)
            "Fire" -> imageView.setImageResource(R.drawable.fire_bg)
            "Water" -> imageView.setImageResource(R.drawable.water_bg)
            "Grass" -> imageView.setImageResource(R.drawable.grass_bg)
            "Electric" -> imageView.setImageResource(R.drawable.electric_bg)
            "Ice" -> imageView.setImageResource(R.drawable.water_bg)
            "Dragon" -> imageView.setImageResource(R.drawable.fire_bg)
            "Psychic" -> imageView.setImageResource(R.drawable.psykic_bg)
            "Dark" -> imageView.setImageResource(R.drawable.psykic_bg)
            "Fairy" -> imageView.setImageResource(R.drawable.normal_bg)
            "Unknown" -> imageView.setImageResource(R.drawable.normal_bg)

        }
    }
}

@BindingAdapter("pokemonTypeBg")
fun setPokeMonTypeBg(materialCardView: MaterialCardView, type:List<String>?){
    type?.let {
        when(type[0]){
            "Normal" -> materialCardView.setCardBackgroundColor(Color.parseColor("#BEBEBE"))
            "Fighting" -> materialCardView.setCardBackgroundColor(Color.parseColor("#BEBEBE"))
            "Flying" -> materialCardView.setCardBackgroundColor(Color.parseColor("#BEBEBE"))
            "Poison" -> materialCardView.setCardBackgroundColor(Color.parseColor("#BEBEBE"))
            "Ground" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C48B84"))
            "Rock" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C48B84"))
            "Bug" -> materialCardView.setCardBackgroundColor(Color.parseColor("#58DCC0"))
            "Ghost" -> materialCardView.setCardBackgroundColor(Color.parseColor("#9569A4"))
            "Steel" -> materialCardView.setCardBackgroundColor(Color.parseColor("#BEBEBE"))
            "Fire" -> materialCardView.setCardBackgroundColor(Color.parseColor("#FB7B6A"))
            "Water" -> materialCardView.setCardBackgroundColor(Color.parseColor("#56AAF2"))
            "Grass" -> materialCardView.setCardBackgroundColor(Color.parseColor("#58DCC0"))
            "Electric" -> materialCardView.setCardBackgroundColor(Color.parseColor("#F9D557"))
            "Ice" -> materialCardView.setCardBackgroundColor(Color.parseColor("#56AAF2"))
            "Dragon" -> materialCardView.setCardBackgroundColor(Color.parseColor("#FB7B6A"))
            "Psychic" -> materialCardView.setCardBackgroundColor(Color.parseColor("#9569A4"))
            "Dark" -> materialCardView.setCardBackgroundColor(Color.parseColor("#9569A4"))
            "Fairy" -> materialCardView.setCardBackgroundColor(Color.parseColor("#BEBEBE"))
            "Unknown" -> materialCardView.setCardBackgroundColor(Color.parseColor("#BEBEBE"))
            else -> materialCardView.setCardBackgroundColor(Color.parseColor("#BEBEBE"))
        }
    }
}