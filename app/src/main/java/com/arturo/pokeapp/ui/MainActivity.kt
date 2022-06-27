package com.arturo.pokeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arturo.pokeapp.R
import com.arturo.pokeapp.ui.fragments.pokemonlist.PokemonListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var fragmentFactory: MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.fragmentFactory = fragmentFactory
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PokemonListFragment::class.java, null)
            .commit()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count <= 1) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}