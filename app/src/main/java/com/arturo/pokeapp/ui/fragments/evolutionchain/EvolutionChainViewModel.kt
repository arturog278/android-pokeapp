package com.arturo.pokeapp.ui.fragments.evolutionchain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturo.pokeapp.model.network.PokemonService
import com.arturo.pokeapp.model.network.response.Chain
import com.arturo.pokeapp.model.network.response.Species
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EvolutionChainViewModel @Inject constructor(
    private val api: PokemonService
) : ViewModel() {
    val evolutionChain = MutableLiveData<List<Species>>()
    private val speciesList = mutableListOf<Species>()

    fun onCreate(url: String) {
        viewModelScope.launch {
            val result = api.getEvolutionChain(url)
            speciesList.clear()
            result?.let { response ->
                speciesList.add(response.chain.pokemon)
                response.chain.evolvesTo.forEach { chain ->
                    getSpeciesFromChain(chain)
                }
            }
            evolutionChain.postValue(speciesList)
        }
    }

    private fun getSpeciesFromChain(chain: Chain) {
        speciesList.add(chain.pokemon)
        chain.evolvesTo.forEach { chain ->
            getSpeciesFromChain(chain)
        }
    }
}