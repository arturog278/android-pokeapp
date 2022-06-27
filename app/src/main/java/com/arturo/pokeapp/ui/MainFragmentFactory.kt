package com.arturo.pokeapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.arturo.pokeapp.ui.fragments.abilities.AbilitiesAdapter
import com.arturo.pokeapp.ui.fragments.abilities.AbilitiesFragment
import com.arturo.pokeapp.ui.fragments.evolutionchain.EvolutionChainAdapter
import com.arturo.pokeapp.ui.fragments.evolutionchain.EvolutionChainFragment
import com.arturo.pokeapp.ui.fragments.pokemoninfo.PokemonInfoFragment
import com.arturo.pokeapp.ui.fragments.pokemonlist.PokemonListAdapter
import com.arturo.pokeapp.ui.fragments.pokemonlist.PokemonListFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val pokemonListAdapter: PokemonListAdapter,
    private val abilitiesAdapter: AbilitiesAdapter,
    private val evolutionChainAdapter: EvolutionChainAdapter,
    private val favoriteResponse: Boolean
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            PokemonListFragment::class.java.name -> {
                PokemonListFragment(pokemonListAdapter)
            }

            PokemonInfoFragment::class.java.name -> {
                PokemonInfoFragment()
            }

            AbilitiesFragment::class.java.name -> {
                AbilitiesFragment(abilitiesAdapter)
            }

            EvolutionChainFragment::class.java.name -> {
                EvolutionChainFragment(evolutionChainAdapter, favoriteResponse)
            }

            else -> super.instantiate(classLoader, className)
        }
    }
}