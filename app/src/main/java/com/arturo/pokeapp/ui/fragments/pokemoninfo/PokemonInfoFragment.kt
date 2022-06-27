package com.arturo.pokeapp.ui.fragments.pokemoninfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arturo.pokeapp.R
import com.arturo.pokeapp.databinding.FragmentPokemonInfoBinding
import com.arturo.pokeapp.ui.fragments.abilities.AbilitiesFragment
import com.arturo.pokeapp.ui.fragments.evolutionchain.EvolutionChainFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PokemonInfoFragment : Fragment() {
    private var pokemonName: String? = null
    private var _binding : FragmentPokemonInfoBinding? = null
    private val binding get() = _binding!!
    private val pokemonInfoViewModel : PokemonInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonName = it.getString("POKEMON_NAME")
        }
        pokemonInfoViewModel.onCreate(pokemonName!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTv.text = pokemonName?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
        pokemonInfoViewModel.pokemonInfo.observe(viewLifecycleOwner) { pokemonInfo ->
            if (pokemonInfo != null) {
                binding.happinessTvEditable.text = pokemonInfo.baseHappiness.toString()
                binding.captureRateTvEditable.text = pokemonInfo.captureRate.toString()
                binding.colorTvEditable.text = pokemonInfo.color.name
                var eggGroupsNames = ""
                pokemonInfo.eggGroups.forEach { eggGroup ->
                    eggGroupsNames = "${eggGroup.name}, $eggGroupsNames"
                }
                binding.eggGroupsTvEditable.text = eggGroupsNames

                binding.abilitiesBtn.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("POKEMON_NAME", pokemonName)
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, AbilitiesFragment::class.java, bundle)
                        ?.addToBackStack(null)
                        ?.commit()
                }

                binding.evolutionBtn.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("URL", pokemonInfo.evolutionChain.url)
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, EvolutionChainFragment::class.java, bundle)
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
        }
    }
}