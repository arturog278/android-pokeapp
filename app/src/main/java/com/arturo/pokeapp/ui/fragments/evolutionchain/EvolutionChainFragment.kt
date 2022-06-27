package com.arturo.pokeapp.ui.fragments.evolutionchain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturo.pokeapp.R
import com.arturo.pokeapp.databinding.FragmentEvolutionChainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EvolutionChainFragment constructor(
    private val adapter: EvolutionChainAdapter,
    private val favoriteResponse: Boolean
) : Fragment() {
    private var url: String? = null
    private var _binding : FragmentEvolutionChainBinding? = null
    private val binding get() = _binding!!
    private val evolutionChainViewModel : EvolutionChainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString("URL")
        }
        evolutionChainViewModel.onCreate(url!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEvolutionChainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        evolutionChainViewModel.evolutionChain.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        adapter.setOnItemClickListener {
            val response = if (favoriteResponse) {
                "Favorite - $it"
            } else {
                "Error - $it"
            }
            Toast.makeText(context, response, Toast.LENGTH_LONG).show()
            activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        binding.evolutionChainRv.layoutManager = LinearLayoutManager(context)
        binding.evolutionChainRv.adapter = adapter
    }
}