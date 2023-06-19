package dev.shreyansh.pokemon.pokedex.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.shreyansh.pokemon.pokedex.databinding.MovesListItemBinding
import dev.shreyansh.pokemon.pokedex.network.response.MovesResponse


class MovesRecyclerAdapter( val onClickListener: OnClickListener, val activity: Activity) : ListAdapter<MovesResponse, MovesRecyclerAdapter.ViewHolder>(DiffUtilItemCallBackMoves()) {


    class ViewHolder private constructor(val binding : MovesListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovesResponse) {
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
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }

//        Glide.with(activity.applicationContext).load(item.imageUrl).into(holder.binding.pokemonIV)
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (moves: MovesResponse) -> Unit) {
        fun onClick(moves: MovesResponse) = clickListener(moves)
    }

}


class DiffUtilItemCallBackMoves : DiffUtil.ItemCallback<MovesResponse>() {
    override fun areItemsTheSame(oldItem: MovesResponse, newItem: MovesResponse): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovesResponse, newItem: MovesResponse): Boolean {
        return oldItem == newItem
    }

}