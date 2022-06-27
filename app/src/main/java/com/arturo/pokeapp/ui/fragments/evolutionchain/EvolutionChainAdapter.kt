package com.arturo.pokeapp.ui.fragments.evolutionchain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturo.pokeapp.R
import com.arturo.pokeapp.databinding.RecyclerItemBinding
import com.arturo.pokeapp.model.network.response.Species
import java.util.*

class EvolutionChainAdapter : RecyclerView.Adapter<EvolutionChainAdapter.EvolutionChainViewHolder>() {
    private val mEvolutions = mutableListOf<Species>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionChainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EvolutionChainViewHolder(layoutInflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: EvolutionChainViewHolder, position: Int) {
        val species = mEvolutions[position]
        holder.bind(species)
    }

    override fun getItemCount(): Int = mEvolutions.size

    fun setData(evolutions: List<Species>) {
        mEvolutions.clear()
        mEvolutions.addAll(evolutions)
        notifyDataSetChanged()
    }

    inner class EvolutionChainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecyclerItemBinding.bind(view)

        fun bind(species: Species) {
            binding.itemTv.text = species.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }

            binding.root.setOnClickListener {
                onItemClickListener?.let { it(species.name) }
            }
        }
    }

    private var onItemClickListener : ((String) -> Unit)? = null
    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}