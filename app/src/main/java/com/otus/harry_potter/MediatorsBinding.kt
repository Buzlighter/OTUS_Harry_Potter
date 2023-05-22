package com.otus.harry_potter

import com.otus.characters_screen.navigation.CharactersMediatorImpl
import com.otus.core_api.navigation.CharactersMediator
import com.otus.core_api.navigation.PotionsMediator
import com.otus.core_api.navigation.TreeMediator
import com.otus.potions_screen.navigation.PotionsMediatorImpl
import com.otus.tree_screen.navigation.TreeMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MediatorsBinding {

    @Binds
    @Reusable
    fun bindCharactersMediator(charactersMediatorImpl: CharactersMediatorImpl): CharactersMediator

    @Binds
    @Reusable
    fun bindTreeMediator(treeMediatorImpl: TreeMediatorImpl): TreeMediator

    @Binds
    @Reusable
    fun bindPotionsMediator(potionsMediatorImpl: PotionsMediatorImpl): PotionsMediator
}