package dev.shreyansh.pokemon.pokedex.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.AbilitiesListItemBinding
import dev.shreyansh.pokemon.pokedex.network.response.AbilitiesResponse




class AbilitiesRecyclerAdapter(val activity: Activity) : ListAdapter<AbilitiesResponse, AbilitiesRecyclerAdapter.ViewHolder>(DiffUtilItemCallBackAbilities()) {


    class ViewHolder private constructor(val binding : AbilitiesListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AbilitiesResponse) {
            binding.ability = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AbilitiesListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        Glide.with(activity.applicationContext)
            .load(item.pokemonURL)
            .placeholder(R.drawable.def_poke)
            .error(R.drawable.def_poke)
            .into(holder.binding.pokemonIV)

        holder.bind(item)
    }

}


class DiffUtilItemCallBackAbilities : DiffUtil.ItemCallback<AbilitiesResponse>() {
    override fun areItemsTheSame(oldItem: AbilitiesResponse, newItem: AbilitiesResponse): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: AbilitiesResponse, newItem: AbilitiesResponse): Boolean {
        return oldItem == newItem
    }

}