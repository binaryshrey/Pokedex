package dev.shreyansh.pokemon.pokedex.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.shreyansh.pokemon.pokedex.databinding.PokenewsHighlightListItemBinding
import dev.shreyansh.pokemon.pokedex.databinding.PokenewsListItemBinding
import dev.shreyansh.pokemon.pokedex.domain.PokemonNews


class PokeNewsRecyclerAdapter(val onClickListener: OnClickListener, private val activity: Activity) : ListAdapter<PokemonNews, RecyclerView.ViewHolder>(DiffUtilPokeNewsCallBack()) {

    private var news: MutableList<PokemonNews> = mutableListOf()

    override fun submitList(list: MutableList<PokemonNews>?) {
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
            else -> 2
        }
    }

    class PokeNewsHighlightViewHolder(val binding : PokenewsHighlightListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonNews) {
            binding.news = item
            binding.executePendingBindings()
        }
    }

    class PokeNewsViewHolder(val binding : PokenewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonNews) {
            binding.news = item
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1 -> PokeNewsHighlightViewHolder(PokenewsHighlightListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            2 -> PokeNewsViewHolder(PokenewsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> PokeNewsHighlightViewHolder(PokenewsHighlightListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        when(holder){
            is PokeNewsHighlightViewHolder ->  {
                Glide.with(activity.applicationContext).load(item.image).apply(RequestOptions().fitCenter()).into(holder.binding.thumbnailIV)
                holder.binding.source.text = item.tags ?: "Source • "
                holder.binding.title.text = item.title.parseAsHtml() ?: "Title"


                holder.bind(item)
            }
            is PokeNewsViewHolder ->  {
                Glide.with(activity.applicationContext).load(item.image).apply(RequestOptions().fitCenter()).into(holder.binding.thumbnailIV)
                holder.binding.source.text = item.tags ?: "Source • "
                holder.binding.title.text = item.title.parseAsHtml() ?: "Title"
                holder.bind(item)
            }
        }
    }

    class OnClickListener(val clickListener: (item: PokemonNews) -> Unit) {
        fun onClick(item: PokemonNews) = clickListener(item)
    }
}


class DiffUtilPokeNewsCallBack : DiffUtil.ItemCallback<PokemonNews>() {
    override fun areItemsTheSame(oldItem: PokemonNews, newItem: PokemonNews): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonNews, newItem: PokemonNews): Boolean {
        return oldItem == newItem
    }


}
