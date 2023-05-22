package com.otus.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigation.NavigationBarView
import com.otus.core_api.navigation.CharactersMediator
import com.otus.core_api.navigation.PotionsMediator
import com.otus.core_api.navigation.TreeMediator
import com.otus.core_api.providers.ExternalAppHub
import com.otus.main.databinding.ActivityMainBinding
import com.otus.main.di.DaggerMainComponent
import com.otus.main.di.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var charactersMediator: CharactersMediator

    @Inject
    lateinit var treeMediator: TreeMediator

    @Inject
    lateinit var potionsMediator: PotionsMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainComponent
            .create((application as ExternalAppHub).getExternalProvider())
            .inject(this)

        charactersMediator.openCharactersScreen(binding.mainContainer.id, supportFragmentManager)

        binding.bottomBar.setOnItemSelectedListener(onNavigationItemListener)
        binding.bottomBar.setOnItemReselectedListener {  }
    }


    private val onNavigationItemListener = NavigationBarView.OnItemSelectedListener { item ->
        when(item.itemId) {
            R.id.menu_characters -> {
                charactersMediator.openCharactersScreen(binding.mainContainer.id, supportFragmentManager)
                true
            }
            R.id.menu_tree -> {
                treeMediator.openTreeScreen(binding.mainContainer.id, supportFragmentManager)
                true
            }
            else -> {
                potionsMediator.openPotionsScreen(binding.mainContainer.id, supportFragmentManager)
                true
            }
        }
    }
}