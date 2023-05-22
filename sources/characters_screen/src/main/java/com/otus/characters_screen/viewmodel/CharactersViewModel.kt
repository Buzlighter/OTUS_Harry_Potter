package com.otus.characters_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otus.core_api.remote.HpApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import com.otus.core_api.dto.Character

class CharactersViewModel constructor(
    private val hpApi: HpApi,
    private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private var _charactersLiveData: MutableLiveData<List<Character>> = MutableLiveData()
    val charactersLiveData: LiveData<List<Character>> = _charactersLiveData

    fun getCharacters() {
        viewModelScope.launch(dispatcherIO) {
            hpApi
                .getAllCharacters()
                .filter { char ->
                    char.image.isNotEmpty()
                }
                .also { _charactersLiveData.postValue(it) }
        }
    }
}