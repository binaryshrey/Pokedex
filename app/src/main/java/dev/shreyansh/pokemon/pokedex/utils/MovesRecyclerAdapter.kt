package dev.shreyansh.pokemon.pokedex.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.MovesListItemBinding
import dev.shreyansh.pokemon.pokedex.domain.Moves


class MovesRecyclerAdapter(val activity: Activity) : ListAdapter<Moves, MovesRecyclerAdapter.ViewHolder>(DiffUtilItemCallBackMoves()) {


    class ViewHolder private constructor(val binding : MovesListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Moves) {
            binding.moves = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovesListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if(!item.learnedByPokemon.isNullOrEmpty()){
            Glide.with(activity.applicationContext)
                .load(item.learnedByPokemon[0].url)
                .placeholder(R.drawable.def_poke)
                .error(R.drawable.def_poke)
                .into(holder.binding.poke1IV)

            Glide.with(activity.applicationContext)
                .load(item.learnedByPokemon[1].url)
                .placeholder(R.drawable.def_poke)
                .error(R.drawable.def_poke)
                .into(holder.binding.poke2IV)

            Glide.with(activity.applicationContext)
                .load(item.learnedByPokemon[2].url)
                .placeholder(R.drawable.def_poke)
                .error(R.drawable.def_poke)
                .into(holder.binding.poke3IV)
        }
        holder.bind(item)
    }

}


class DiffUtilItemCallBackMoves : DiffUtil.ItemCallback<Moves>() {
    override fun areItemsTheSame(oldItem: Moves, newItem: Moves): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: Moves, newItem: Moves): Boolean {
        return oldItem == newItem
    }

}