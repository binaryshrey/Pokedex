package dev.shreyansh.pokemon.pokedex.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Pokemon(
    val id: String,
    val name: String,
    val height: String,
    val category : String,
    val weight : String,
    val weaknesses: MutableList<String>,
    val evolutions : MutableList<String>,
    val abilities : MutableList<String>,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed : Int,
    val total : Int,
    val cycles: String,
    val reason : String,
    val imageUrl: String,
    val baseExp : String,
    val eggGroups: String,
    val evolvedFrom: String,
    val description: String,
    val type: MutableList<String>,
    val specialAttack: Int,
    val specialDefense: Int,
    val male: String,
    val female: String,
) : Parcelable