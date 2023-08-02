package dev.shreyansh.pokemon.pokedex.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.shreyansh.pokemon.pokedex.R
import dev.shreyansh.pokemon.pokedex.databinding.LocationsHighlightListItemBinding
import dev.shreyansh.pokemon.pokedex.databinding.LocationsListItemBinding
import dev.shreyansh.pokemon.pokedex.domain.Location




class LocationsRecyclerAdapter(val onClickListener: OnClickListener, private val activity: Activity) : ListAdapter<Location, RecyclerView.ViewHolder>(DiffUtilLocationsCallBack()) {

    private var news: MutableList<Location> = mutableListOf()

    override fun submitList(list: MutableList<Location>?) {
        super.submitList(list)
        if (list != null) {
            news = list
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> 1
            5 -> 1
            10 -> 1
            15 -> 1
            26 -> 1
            30 -> 1
            35 -> 1
            40 -> 1
            45 -> 1
            60 -> 1
            70 -> 1
            80 -> 1
            90 -> 1
            100 -> 1
            105 -> 1
            110 -> 1
            115 -> 1
            126 -> 1
            130 -> 1
            135 -> 1
            140 -> 1
            145 -> 1
            160 -> 1
            else -> 2
        }
    }

    class LocationsHighlightViewHolder(val binding : LocationsHighlightListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Location) {
            binding.location = item
            binding.executePendingBindings()
        }
    }

    class LocationsViewHolder(val binding : LocationsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Location) {
            binding.location = item
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1 -> LocationsHighlightViewHolder(LocationsHighlightListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            2 -> LocationsViewHolder(LocationsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> LocationsHighlightViewHolder(LocationsHighlightListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        when(holder){
            is LocationsHighlightViewHolder ->  {
                Glide.with(activity.applicationContext).load(item.locationURL).into(holder.binding.locationIV)
                Glide.with(activity.applicationContext).load(item.pokemonImgURL).placeholder(R.drawable.def_poke)
                    .error(R.drawable.def_poke).into(holder.binding.pokeIV)
                holder.bind(item)
            }
            is LocationsViewHolder ->  {
                Glide.with(activity.applicationContext).load(item.locationURL).into(holder.binding.locationIV)
                Glide.with(activity.applicationContext).load(item.pokemonImgURL).placeholder(R.drawable.def_poke)
                    .error(R.drawable.def_poke).into(holder.binding.pokeIV)
                holder.bind(item)
            }
        }
    }

    class OnClickListener(val clickListener: (item: Location) -> Unit) {
        fun onClick(item: Location) = clickListener(item)
    }
}


class DiffUtilLocationsCallBack : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }


}