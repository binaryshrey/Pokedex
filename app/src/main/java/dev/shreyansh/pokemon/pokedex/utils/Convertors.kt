package dev.shreyansh.pokemon.pokedex.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): MutableList<String>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<MutableList<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: MutableList<String>?): String? {
        return gson.toJson(value)
    }
}