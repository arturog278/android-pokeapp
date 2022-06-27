package com.arturo.pokeapp.ui.fragments.abilities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturo.pokeapp.R
import com.arturo.pokeapp.databinding.FragmentAbilitiesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AbilitiesFragment constructor(
    private val adapter: AbilitiesAdapter
) : Fragment() {
    private var pokemonName: String? = null
    private var _binding : FragmentAbilitiesBinding? = null
    private val binding get() = _binding!!
    private val abilitiesViewModel : AbilitiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonName = it.getString("POKEMON_NAME")
        }
        abilitiesViewModel.onCreate(pokemonName!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAbilitiesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        abilitiesViewModel.abilities.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        binding.pokemonAbilitiesRv.layoutManager = LinearLayoutManager(context)
        binding.pokemonAbilitiesRv.adapter = adapter
    }
}