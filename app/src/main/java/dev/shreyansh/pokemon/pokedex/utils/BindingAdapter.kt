package dev.shreyansh.pokemon.pokedex.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.CircularProgressIndicator
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


@BindingAdapter("pokemonDesc")
fun setPokemonDesc(textView: TextView, description:String?){
    description?.let {
        textView.text = description.toString()
    }
}

@BindingAdapter("pokemonId")
fun setPokeMonId(textView: TextView, id:String?){
    id?.let {
        textView.text = id.toString()
    }
}


@BindingAdapter("intText")
fun setIntText(textView: TextView, text:Int?){
    text?.let {
        textView.text = "${text}"
    }
}


@BindingAdapter("text")
fun setText(textView: TextView, text:String?){
    text?.let {
        textView.text = text.capitalize().toString()
    }
}


@BindingAdapter("powerPercentage")
fun setPowerPercentage(circularProgressIndicator: CircularProgressIndicator, power: Int?) {
    power?.let {
        circularProgressIndicator.progress = power
    }
}


@BindingAdapter("movesProgress")
fun setMovesProgress(progressBar: ProgressBar, status: PokedexViewModel.MovesStatus?) {
    progressBar.visibility = View.GONE
    status?.let {
        when (status) {
            PokedexViewModel.MovesStatus.LOADING -> progressBar.visibility = View.VISIBLE
            PokedexViewModel.MovesStatus.ERROR -> progressBar.visibility = View.GONE
            PokedexViewModel.MovesStatus.DONE -> progressBar.visibility = View.GONE
        }
    }
}


@BindingAdapter("abilitiesProgress")
fun setAbilitiesProgress(progressBar: ProgressBar, status: PokedexViewModel.AbilitiesStatus?) {
    progressBar.visibility = View.GONE
    status?.let {
        when (status) {
            PokedexViewModel.AbilitiesStatus.LOADING -> progressBar.visibility = View.VISIBLE
            PokedexViewModel.AbilitiesStatus.ERROR -> progressBar.visibility = View.GONE
            PokedexViewModel.AbilitiesStatus.DONE -> progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("itemsProgress")
fun setItemsProgress(progressBar: ProgressBar, status: PokedexViewModel.ItemsStatus?) {
    progressBar.visibility = View.GONE
    status?.let {
        when (status) {
            PokedexViewModel.ItemsStatus.LOADING -> progressBar.visibility = View.VISIBLE
            PokedexViewModel.ItemsStatus.ERROR -> progressBar.visibility = View.GONE
            PokedexViewModel.ItemsStatus.DONE -> progressBar.visibility = View.GONE
        }
    }
}


@BindingAdapter("pokemonHeight")
fun setPokemonHeight(textView: TextView, height:String?){
    height?.let {
        textView.text = "$height (${convertHeightToCm(height)} cm)"
    }
}


@BindingAdapter("male")
fun setMalePercentage(textView: TextView, male:String?){
    male?.let {
        textView.text = "⚦ $male"
    }
}

@BindingAdapter("female")
fun setFemalePercentage(textView: TextView, female:String?){
    female?.let {
        textView.text = "⚧ $female"
    }
}

@BindingAdapter("pokemonWeight")
fun setPokemonWeight(textView: TextView, weight:String?){
    weight?.let {
        textView.text = "$weight (${convertToKilograms(weight)} kg)"
    }
}


@BindingAdapter("pokemonTypeTV")
fun setPokemonTypeTV(textView: TextView, type:List<String>?){
    type?.let {
        textView.text = type[0].toString()
    }
}


@BindingAdapter("pokemonTypeName")
fun setPokemonTypeName(textView: TextView, name:String?){
    name?.let {
        textView.text = "$name Pokemon"
    }
}

@BindingAdapter("progressValue")
fun setProgressValue(textView: TextView, progress:Int?){
    progress?.let {
        textView.text = "$progress"
    }
}

@BindingAdapter("weakness")
fun setWeakness(textView: TextView, weakness: List<String>?){
    weakness?.let {
        textView.text = "${weakness.joinToString(", ")} - Type Pokemons"
    }
}

@BindingAdapter("statsProgress")
fun setStatsProgress(progressBar: ProgressBar, stats:Int?){
    stats?.let {
        progressBar.progress = stats
        if(stats >= 40){
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#8fce00")))
        }else{
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#f44336")))
        }
    }
}


@BindingAdapter("totalStatsProgress")
fun setTotalStatsProgress(progressBar: ProgressBar, stats:Int?){
    stats?.let {
        progressBar.progress = stats
        if(stats >= 240){
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#8fce00")))
        }else{
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#f44336")))
        }
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
            "Normal" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C7C7C7"))
            "Fighting" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C7C7C7"))
            "Flying" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C7C7C7"))
            "Poison" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C7C7C7"))
            "Ground" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C78C85"))
            "Rock" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C78C85"))
            "Bug" -> materialCardView.setCardBackgroundColor(Color.parseColor("#5DDFC6"))
            "Ghost" -> materialCardView.setCardBackgroundColor(Color.parseColor("#AF6FB6"))
            "Steel" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C7C7C7"))
            "Fire" -> materialCardView.setCardBackgroundColor(Color.parseColor("#FD8585"))
            "Water" -> materialCardView.setCardBackgroundColor(Color.parseColor("#76C4FB"))
            "Grass" -> materialCardView.setCardBackgroundColor(Color.parseColor("#5DDFC6"))
            "Electric" -> materialCardView.setCardBackgroundColor(Color.parseColor("#FFDE5D"))
            "Ice" -> materialCardView.setCardBackgroundColor(Color.parseColor("#76C4FB"))
            "Dragon" -> materialCardView.setCardBackgroundColor(Color.parseColor("#FD8585"))
            "Psychic" -> materialCardView.setCardBackgroundColor(Color.parseColor("#AF6FB6"))
            "Dark" -> materialCardView.setCardBackgroundColor(Color.parseColor("#AF6FB6"))
            "Fairy" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C7C7C7"))
            "Unknown" -> materialCardView.setCardBackgroundColor(Color.parseColor("#C7C7C7"))
            else -> materialCardView.setCardBackgroundColor(Color.parseColor("#C7C7C7"))
        }
    }
}


@BindingAdapter("pokemonTypeDetailBg")
fun setPokeMonTypeDetailBg(imageView: ImageView, type:List<String>?){
    type?.let {
        when(type[0]){
            "Normal" -> imageView.setImageResource(R.drawable.normal_detail)
            "Fighting" -> imageView.setImageResource(R.drawable.normal_detail)
            "Flying" -> imageView.setImageResource(R.drawable.normal_detail)
            "Poison" -> imageView.setImageResource(R.drawable.normal_detail)
            "Ground" -> imageView.setImageResource(R.drawable.ground_detail)
            "Rock" -> imageView.setImageResource(R.drawable.ground_detail)
            "Bug" -> imageView.setImageResource(R.drawable.grass_detail)
            "Ghost" -> imageView.setImageResource(R.drawable.psychic_detail)
            "Steel" -> imageView.setImageResource(R.drawable.normal_detail)
            "Fire" -> imageView.setImageResource(R.drawable.fire_detail)
            "Water" -> imageView.setImageResource(R.drawable.water_detail)
            "Grass" -> imageView.setImageResource(R.drawable.grass_detail)
            "Electric" -> imageView.setImageResource(R.drawable.electric_detail)
            "Ice" -> imageView.setImageResource(R.drawable.water_detail)
            "Dragon" -> imageView.setImageResource(R.drawable.fire_detail)
            "Psychic" -> imageView.setImageResource(R.drawable.psychic_detail)
            "Dark" -> imageView.setImageResource(R.drawable.psychic_detail)
            "Fairy" -> imageView.setImageResource(R.drawable.normal_detail)
            "Unknown" -> imageView.setImageResource(R.drawable.normal_detail)
            else -> imageView.setImageResource(R.drawable.normal_detail)
        }
    }
}


@BindingAdapter("detailPokemonFavBg")
fun setDetailPokemonFavBg(materialCardView: MaterialCardView, type:List<String>?){
    type?.let {
        when(type[0]){
            "Normal" -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1B1B1"))
            "Fighting" -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1B1B1"))
            "Flying" -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1B1B1"))
            "Poison" -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1B1B1"))
            "Ground" -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1736C"))
            "Rock" -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1736C"))
            "Bug" -> materialCardView.setCardBackgroundColor(Color.parseColor("#48D0B0"))
            "Ghost" -> materialCardView.setCardBackgroundColor(Color.parseColor("#92589E"))
            "Steel" -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1B1B1"))
            "Fire" -> materialCardView.setCardBackgroundColor(Color.parseColor("#FB6C6C"))
            "Water" -> materialCardView.setCardBackgroundColor(Color.parseColor("#5EAEF9"))
            "Grass" -> materialCardView.setCardBackgroundColor(Color.parseColor("#48D0B0"))
            "Electric" -> materialCardView.setCardBackgroundColor(Color.parseColor("#FFCE48"))
            "Ice" -> materialCardView.setCardBackgroundColor(Color.parseColor("#5EAEF9"))
            "Dragon" -> materialCardView.setCardBackgroundColor(Color.parseColor("#FB6C6C"))
            "Psychic" -> materialCardView.setCardBackgroundColor(Color.parseColor("#92589E"))
            "Dark" -> materialCardView.setCardBackgroundColor(Color.parseColor("#92589E"))
            "Fairy" -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1B1B1"))
            "Unknown" -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1B1B1"))
            else -> materialCardView.setCardBackgroundColor(Color.parseColor("#B1B1B1"))
        }
    }
}