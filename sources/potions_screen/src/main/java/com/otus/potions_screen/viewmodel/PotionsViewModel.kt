package com.otus.potions_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otus.core_api.dto.Elixir
import com.otus.core_api.dto.Ingredient
import com.otus.core_api.remote.HerokuApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlin.random.Random

class PotionsViewModel(
    private val herokuApi: HerokuApi,
    private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _elixirLiveData = MutableLiveData<List<Elixir>>()
    var elixirLiveData: LiveData<List<Elixir>> = _elixirLiveData

    fun getElixirs() {
        viewModelScope.launch(dispatcherIO) {
            herokuApi
                .getElixirs()
                .also {
                    _elixirLiveData.postValue(it)
                }
        }
    }

    fun getElixirListWithLimitedAmount(elixirList: List<Elixir>): List<Elixir> {
        return elixirList
            .filter { it.ingredients.size in 1..5 }
    }

    fun getRandomElixir(elixirList: List<Elixir>): Elixir {
        return elixirList
            .random()
    }

    fun getTableIngredientsList(elixirList: List<Elixir>, selectedIngredientList: List<Ingredient>): List<Ingredient> {
        val randomElixirList = elixirList
            .flatMap { it.ingredients }
            .minus(selectedIngredientList.toSet())
            .shuffled()
            .take(Random.nextInt(1, 4))

        return randomElixirList + selectedIngredientList
    }

    fun isCookingResult(resultIngredients: List<Ingredient>, realIngredients: List<Ingredient> ): Boolean {
        return resultIngredients == realIngredients
    }
}