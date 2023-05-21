package com.otus.characters_screen

import com.otus.characters_screen.viewmodel.CharactersViewModel
import com.otus.core_api.remote.HpApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {
    private lateinit var hpApi: HpApi
    private lateinit var mockServer: MockWebServer

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var charactersViewModel: CharactersViewModel

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        hpApi = Retrofit.Builder()
            .baseUrl(mockServer.url("https://hp-api.onrender.com/api/characters/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HpApi::class.java)

        charactersViewModel = CharactersViewModel(hpApi, testDispatcher)
    }


    @Test
    fun `check size of charactersList`() = runTest {
        val mockResponse = MockResponse()
        mockServer.enqueue(mockResponse)

        val expected = 402
        val actual = hpApi.getAllCharacters().size

        Assert.assertEquals(expected, actual)
    }

    @After
    fun shutDownServer() {
        mockServer.shutdown()
    }

}