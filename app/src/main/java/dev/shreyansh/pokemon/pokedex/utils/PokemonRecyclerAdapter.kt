package dev.shreyansh.pokemon.pokedex.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.shreyansh.pokemon.pokedex.databinding.PokemonListViewBinding
import dev.shreyansh.pokemon.pokedex.domain.Pokemon
import dev.shreyansh.pokemon.pokedex.network.response.PokemonResponse

class PokemonRecyclerAdapter( val onClickListener: OnClickListener, val activity: Activity) : ListAdapter<Pokemon, PokemonRecyclerAdapter.ViewHolder>(DiffUtilItemCallBackPokemons()) {


    class ViewHolder private constructor(val binding : PokemonListViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pokemon) {
            binding.pokemon = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PokemonListViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }

        Glide.with(activity.applicationContext).load(item.imageUrl).into(holder.binding.pokemonIV)
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (pokemon: Pokemon) -> Unit) {
        fun onClick(pokemon: Pokemon) = clickListener(pokemon)
    }

}


class DiffUtilItemCallBackPokemons : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

}
