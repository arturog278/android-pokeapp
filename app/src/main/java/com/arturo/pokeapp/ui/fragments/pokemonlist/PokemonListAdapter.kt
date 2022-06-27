package com.arturo.pokeapp.ui.fragments.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturo.pokeapp.R
import com.arturo.pokeapp.databinding.RecyclerItemBinding
import com.arturo.pokeapp.model.Pokemon
import java.util.*


class PokemonListAdapter() : RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    private val mPokemonList = mutableListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonListViewHolder(layoutInflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = mPokemonList[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = mPokemonList.size

    fun setData(pokemonList : List<Pokemon>) {
        mPokemonList.clear()
        mPokemonList.addAll(pokemonList)
        notifyDataSetChanged()
    }

    inner class PokemonListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecyclerItemBinding.bind(view)

        fun bind(pokemon: Pokemon) {
            binding.itemTv.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
            binding.root.setOnClickListener {
                onItemClickListener?.let { it(pokemon) }
            }
        }
    }

    private var onItemClickListener : ((Pokemon) -> Unit)? = null
    fun setOnItemClickListener(listener: (Pokemon) -> Unit) {
        onItemClickListener = listener
    }
}
