package dev.shreyansh.pokemon.pokedex.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.TypesListItemBinding
import dev.shreyansh.pokemon.pokedex.domain.Type



class TypesRecyclerAdapter(val activity: Activity) : ListAdapter<Type, TypesRecyclerAdapter.ViewHolder>(DiffUtilItemCallBackTypes()) {


    class ViewHolder private constructor(val binding : TypesListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(type: Type) {
            binding.type = type
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TypesListItemBinding.inflate(layoutInflater, parent, false)
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
            .load(item.pokemonImgURL)
            .placeholder(R.drawable.def_poke)
            .error(R.drawable.def_poke)
            .into(holder.binding.typeIV)

        holder.bind(item)
    }

}


class DiffUtilItemCallBackTypes : DiffUtil.ItemCallback<Type>() {
    override fun areItemsTheSame(oldItem: Type, newItem: Type): Boolean {
        return oldItem.id  == newItem.id
    }

    override fun areContentsTheSame(oldItem: Type, newItem: Type): Boolean {
        return oldItem == newItem
    }

}