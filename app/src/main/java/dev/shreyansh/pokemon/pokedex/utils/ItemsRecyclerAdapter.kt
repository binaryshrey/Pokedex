package dev.shreyansh.pokemon.pokedex.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.ItemsListItemBinding
import dev.shreyansh.pokemon.pokedex.network.response.ItemsResponse


class ItemsRecyclerAdapter(val activity: Activity) : ListAdapter<ItemsResponse, ItemsRecyclerAdapter.ViewHolder>(DiffUtilItemCallBackItems()) {


    class ViewHolder private constructor(val binding : ItemsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemsResponse) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemsListItemBinding.inflate(layoutInflater, parent, false)
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
            .load(item.imgURL)
            .placeholder(R.drawable.def_poke)
            .error(R.drawable.def_poke)
            .into(holder.binding.itemIV)

        holder.bind(item)
    }

}


class DiffUtilItemCallBackItems : DiffUtil.ItemCallback<ItemsResponse>() {
    override fun areItemsTheSame(oldItem: ItemsResponse, newItem: ItemsResponse): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemsResponse, newItem: ItemsResponse): Boolean {
        return oldItem == newItem
    }

}