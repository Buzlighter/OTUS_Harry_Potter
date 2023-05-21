package com.otus.potions_screen

import com.google.gson.Gson
import com.otus.core_api.dto.Elixir
import com.otus.core_api.remote.HerokuApi
import com.otus.potions_screen.viewmodel.PotionsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import java.io.File
import java.io.InputStreamReader

@OptIn(ExperimentalCoroutinesApi::class)
class PotionsViewModelTest {

    private val herokuApi: HerokuApi = mock()

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var potionsViewModel: PotionsViewModel

    @Before
    fun setUp() {
        potionsViewModel = PotionsViewModel(herokuApi, testDispatcher)
    }

    @Test
    fun `table ingredients does not contains repeats`() {
        val elixirListMock = getResponseFromFile()
        val ingredientList = elixirListMock.random().ingredients

        val result = potionsViewModel.getTableIngredientsList(elixirListMock, ingredientList)

        val expected = result.toSet().toList()
        val actual = result

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `table ingredients list size always is less than 8`() {
        val elixirListMock = getResponseFromFile()
        val ingredientList = elixirListMock.random().ingredients

        val sizeOfTableList = potionsViewModel.getTableIngredientsList(elixirListMock, ingredientList).size

        val expected = true
        val actual = sizeOfTableList <= 8

        Assert.assertEquals(expected, actual)
    }

    private fun getResponseFromFile(): List<Elixir> {
        val path = this.javaClass.classLoader?.getResource("elixir_response.json")?.file

        if (path.isNullOrEmpty().not()) {
            return Gson().fromJson(File(path!!).readText(), Array<Elixir>::class.java).toList()
        }
        return emptyList()
    }
}