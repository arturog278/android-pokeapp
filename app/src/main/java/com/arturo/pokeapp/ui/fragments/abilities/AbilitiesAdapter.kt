package com.arturo.pokeapp.ui.fragments.abilities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturo.pokeapp.R
import com.arturo.pokeapp.databinding.RecyclerItemBinding
import com.arturo.pokeapp.model.network.response.Ability
import java.util.*

class AbilitiesAdapter : RecyclerView.Adapter<AbilitiesAdapter.AbilitiesViewHolder>() {

    private val mAbilitiesList = mutableListOf<Ability>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AbilitiesViewHolder(layoutInflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        val ability = mAbilitiesList[position]
        holder.bind(ability)
    }

    override fun getItemCount(): Int = mAbilitiesList.size

    fun setData(abilities: List<Ability>) {
        mAbilitiesList.clear()
        mAbilitiesList.addAll(abilities)
        notifyDataSetChanged()
    }

    class AbilitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RecyclerItemBinding.bind(view)

        fun bind(ability: Ability) {
            binding.itemTv.text = ability.ability.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
        }
    }
}