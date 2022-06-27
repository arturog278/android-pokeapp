package com.arturo.pokeapp.ui.fragments.pokemonlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturo.pokeapp.R
import com.arturo.pokeapp.databinding.FragmentPokemonListBinding
import com.arturo.pokeapp.ui.MainFragmentFactory
import com.arturo.pokeapp.ui.fragments.pokemoninfo.PokemonInfoFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment constructor(
    private val adapter: PokemonListAdapter
) : Fragment() {

    private var _binding : FragmentPokemonListBinding? = null
    private val binding get() = _binding!!
    private val pokemonListViewModel : PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonListViewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonListViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressCircular.isVisible = it
        }
        pokemonListViewModel.pokemonList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        adapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putString("POKEMON_NAME", it.name)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, PokemonInfoFragment::class.java, bundle)
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.pokemonListRv.layoutManager = LinearLayoutManager(context)
        binding.pokemonListRv.adapter = adapter
    }
}